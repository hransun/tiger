package vehicle_builder.server;

import vehicle_builder.common.service.SocketConnector;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketConnectorImpl implements SocketConnector {
    private ServerSocket socket;
    private String host;
    private int port;
    public SocketConnectorImpl(String host,int port) {
        this.host = host;
        this.port = port;
        socket = null;
    }
    @Override
    public void openSession() {
        try {
            socket = new ServerSocket(port);
            //writer = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.err.println(e);
            System.exit(1);
        }
        System.out.println(String.format("Server start listening on port %d",port));

    }

    @Override
    public void handleSession(String input) throws Exception {
        // accept conn client
        if (socket == null) {
            throw new Exception("Server socket is not established");
        }
        Socket clientSocket = socket.accept();
        System.out.println(String.format("Accept Connection from client: %s",clientSocket.getPort()));
        ServiceHandler handler = new ServiceHandler(clientSocket);
        new Thread(handler).start();
    }



    @Override
    public void closeSession() {

    }
}
