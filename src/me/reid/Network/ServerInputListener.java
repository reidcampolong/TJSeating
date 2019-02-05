package me.reid.Network;

import me.reid.Network.Connection.NetworkConnection;
import me.reid.Network.Packet.NetworkSeat;
import me.reid.Network.Packet.TJRequest;
import me.reid.Section.Seat.SeatHandler;
import me.reid.Section.Seat.Status;

/**
 * Thread that listens to input from the server
 */
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
            System.out.println("Received event");
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
        NetworkSeat networkSeat = (NetworkSeat) request.getData();
        System.out.println("Seat change event, the seat is " + networkSeat.getX() + " " + networkSeat.getY());
        SeatHandler.updateSeat(SeatHandler.getFromNetworkSeat(networkSeat), Status.valueOf(networkSeat.getSeatStatus()), networkSeat.getSeatHolder());
    }

}