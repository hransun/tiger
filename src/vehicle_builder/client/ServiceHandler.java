package vehicle_builder.client;

import vehicle_builder.client.exception.InternalException;
import vehicle_builder.common.service.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServiceHandler implements SocketConnector, VehicleBuilderService {
    private Socket socket;
    private String serverIp;
    private int serverPort;
    private ObjectInputStream reader;
    private ObjectOutputStream writer;

    public ServiceHandler(String serverIp, int serverPort) {
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }

    @Override
    public void openSession() {
        try {
            socket = new Socket(serverIp,serverPort);
            writer = new ObjectOutputStream(socket.getOutputStream());
            System.out.println(String.format("Successfully connected to server %s: %d",serverIp,serverPort));
        } catch (IOException e) {
            System.err.println(e);
        }

    }



    @Override
    public void handleSession(String input) throws Exception {
        if (input == null) {
            throw new InternalException();
        }
        String[] args = input.split("\\s+");
        if  (args.length ==0) {
            System.out.println("Invalid: empty command");
        }
        switch (args[0]) {
            case "create":
                CreateVehicleRequest request = new CreateVehicleRequest(args[1],Integer.parseInt(args[2]));
                createVehicle(request);
                break;
            case "quit":
                closeSession();
                System.out.println("Bye.");
                System.exit(0);
            case "calc":
                System.out.println("1 + 1 = 2");
            default:
                System.err.println(String.format("command not supported: %s",args
                [0]));
        }

    }

    @Override
    public void closeSession() {
        CloseSessionRequest request = new CloseSessionRequest();
        Object  response = null;
        try {
            writer.writeObject(request);
            response = reader.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response instanceof CloseSessionResponse) {
            if  (reader != null) {
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
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public void createVehicle(CreateVehicleRequest request) throws Exception {
        if (request == null ||  writer==null) {
            throw new Exception("Error: fail to create vehicle");
        }

        writer.writeObject(request);
        System.out.println("Successfully sent create request to server.");

        if (reader == null) {
            reader = new ObjectInputStream(socket.getInputStream());
        }
        CreateVehicleResponse response = (CreateVehicleResponse) reader.readObject();
        System.out.println(response.getVehicle());

    }


}
