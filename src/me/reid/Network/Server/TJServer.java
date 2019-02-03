package me.reid.Network.Server;


import me.reid.Network.Server.Listeners.ClientConnectListener;

import java.io.IOException;
import java.net.ServerSocket;

public class TJServer {

    private ServerSocket server;

    private ClientConnectListener clientConnectListener;

    public TJServer(int port) {
        createServer(port);

        startConnectListener();
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

    public static void main(String[] args) {
        new TJServer(1069);
    }
}
