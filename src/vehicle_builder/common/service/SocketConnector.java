package vehicle_builder.common.service;

public interface SocketConnector {
    // define server and client  socket connector interface need to follow
    void openSession();

    void handleSession(String input) throws Exception;

    void closeSession();
}
