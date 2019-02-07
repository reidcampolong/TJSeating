package main.java.Network.Server;


import main.java.Network.Server.Listeners.ClientConnectListener;
import main.java.Utilities.Log;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * The Server Application Class
 */
public class TJServer {

    private NetworkHandler networkHandler;
    private ServerSocket server;

    private ClientConnectListener clientConnectListener;

    public TJServer(int port) {
        createServer(port);
        Log.i("Server started.");
        this.networkHandler = new NetworkHandler();
        startConnectListener();
        Log.i("Listening for new connections.");
    }


    /**
     * Begins input listener for new clients
     */
    public void startConnectListener() {
        this.clientConnectListener = new ClientConnectListener(this);
        this.clientConnectListener.start();
    }

    private void createServer(int port) {
        try {
            server = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ServerSocket getServerConn() {
        return server;
    }

    public NetworkHandler getNetworkHandler() {
        return networkHandler;
    }

    public static void main(String[] args) {
        new TJServer(1069);
    }
}
