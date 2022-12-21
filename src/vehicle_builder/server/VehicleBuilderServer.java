package vehicle_builder.server;

import vehicle_builder.common.service.SocketConnector;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class VehicleBuilderServer implements Runnable{
    private SocketConnector  connector;
    public VehicleBuilderServer(String host,int serverPort) {
        connector = new SocketConnectorImpl(host,serverPort);
    }
    public static void main(String[] args) {
        String host = null;
        int port = 8080;
        if (args.length < 1) {
            System.out.println("usage: java server <port>");
        }
        port = Integer.parseInt(args[0]);
        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(1);
        }

        VehicleBuilderServer server = new VehicleBuilderServer(host,port);
        new Thread(server).start();

    }
    public void run() {
        connector.openSession();
        while (true) {
            try {
                connector.handleSession(null);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }
}
