package vehicle_builder.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VehicleBuilderClient implements Runnable{
    private ServiceHandler handler;
    public VehicleBuilderClient(String serverIp,int serverPort) {
        handler = new ServiceHandler(serverIp,serverPort);
    }
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java Client <server_ip> <server_port>");
            System.exit(1);
        }
        // init client
        int port = 8080;
        try {
            port = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.err.println(e);
        }

        VehicleBuilderClient client = new VehicleBuilderClient(args[0],port );
        new Thread(client).start();

    }

    public void run() {
        System.out.println("start running client...");
        handler.openSession();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String buffer = null;
        while (true) {
            try {
                buffer = br.readLine();
            } catch (IOException e) {
                System.out.println("failed to read the command");
            }

            // handle command
            try {
                handler.handleSession(buffer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
