package vehicle_builder.server;

import vehicle_builder.common.model.Vehicle;
import vehicle_builder.common.service.*;
import vehicle_builder.server.adapter.VehicleBuilder;
import vehicle_builder.server.adapter.VehicleBuilderImpl;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceHandler implements Runnable, VehicleBuilderService {
    private Socket clientSocket;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;
    public ServiceHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    @Override
    public void run() {
        System.out.println(String.format("Handler started for client %s",clientSocket.getInetAddress().getHostName()));
        while (true) {
            try {
                if (clientSocket == null) {
                    throw new Exception("Client socket is not established.");
                }
                Object input = null;
                // wait for client request
                if (reader==null) {
                    reader = new ObjectInputStream(clientSocket.getInputStream());
                }
                input = reader.readObject();
                VehicleBuilderResponse response = null;
                if (input instanceof CreateVehicleRequest) {
                    createVehicle((CreateVehicleRequest) input);
                } else if (input instanceof CloseSessionRequest) {
                    closeSession();
                    Thread.interrupted();
                    break;
                } else {
                    System.out.println("Request not supported");
                }

            }
            catch (Exception e) {
                System.err.println(e);
            }


        }


    }


    @Override
    public void createVehicle(CreateVehicleRequest request) throws Exception {
        VehicleBuilder builder = new VehicleBuilderImpl();
        Vehicle newVehicle = builder.createVehicle(request.getOwner(), request.getPrice());
        CreateVehicleResponse response = new CreateVehicleResponse();
        response.setVehicle(newVehicle);
        if (writer == null) {
            writer = new ObjectOutputStream(clientSocket.getOutputStream());
        }
        writer.writeObject(response);
        //writer.flush();

    }

    private void closeSession() {
        // write response
        try {
            if (writer == null) {
                writer = new ObjectOutputStream(clientSocket.getOutputStream());
            }
            writer.writeObject(new CloseSessionResponse());
        } catch (IOException e) {
            System.err.println(e);
        }

        // close session
        if (reader != null) {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (writer != null) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
