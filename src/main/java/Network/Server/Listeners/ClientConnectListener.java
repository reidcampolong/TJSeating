package main.java.Network.Server.Listeners;

import main.java.Network.Connection.NetworkConnection;
import main.java.Network.Server.TJServer;

import java.io.IOException;
import java.net.Socket;

/**
 * Thread that listens for new connections from clients
 */
public class ClientConnectListener extends Thread {

    /**
     * TJ's main server
     **/
    private TJServer server;


    /**
     * Constructs a new client connect
     **/
    public ClientConnectListener(TJServer server) {
        this.server = server;
    }


    /**
     * Listens for new clients to connect
     */
    @Override
    public void run() {
        try {
            Socket socket;
            while (true) {
                socket = server.getServerConn().accept();
                NetworkConnection userConnection = new NetworkConnection(socket);
                userConnection.startListener(new ClientInputListener(server, userConnection));
                server.getNetworkHandler().addConnection(userConnection);
                /*PlaceRequest loginRequest = userConnection.getInput();
                if (placeServer.getNetworkServer().handleLoginRequest(userConnection, loginRequest)) {
                    placeServer.writeToFile(userConnection.getSocket().toString() + " connected");
                    userConnection.writeOut(new PlaceRequest<>(PlaceRequest.RequestType.LOGIN_SUCCESS, "You have logged in!"));
                    userConnection.writeOut(new PlaceRequest<>(PlaceRequest.RequestType.BOARD, placeServer.getBoard()));

                    // Listen for tile changes now that they are logged in
                    userConnection.startListener(new ClientInput(placeServer, userConnection));
                    System.out.println("New client connected " + userConnection.getSocket().getInetAddress());
                } else {
                    placeServer.writeToFile(userConnection.getSocket().getInetAddress() + " could not login");
                    userConnection.writeOut(new PlaceRequest<>(PlaceRequest.RequestType.LOGIN, "Username taken/highly popular!"));
                    userConnection.close();
                    socket.close();
                }*/
            }

        } catch (IOException e) {
            System.out.println("Invalid input received. Ending listening");
        }
    }
}
