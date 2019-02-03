package me.reid.Network;

import me.reid.Network.Connection.NetworkConnection;
import me.reid.Network.Packet.TJRequest;

public class ServerInputListener extends Thread {

    /**
     * Connection between server and client
     **/
    private NetworkConnection conn;

    /**
     * Constructs new ServerInput Thread
     *
     * @param conn
     */
    public ServerInputListener(NetworkConnection conn) {
        this.conn = conn;
    }

    /**
     * Thread that listens for new input from client
     */
    public void run() {
        while (conn.isActive()) {
            TJRequest<?> request = conn.getInput();
            if (request == null) break;
            switch (request.getType()) {
                case SEAT_CHANGE_EVENT:
                    seatChangeEvent(request);
                    break;
            }
        }
    }

    public void seatChangeEvent(TJRequest request) {
        // RUN DB INPUT TODO
        System.out.println("Seat change event " + request);

    }

}