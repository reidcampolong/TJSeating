package me.reid.Network.Server.Listeners;

import me.reid.Network.Connection.NetworkConnection;
import me.reid.Network.Packet.NetworkSeat;
import me.reid.Network.Packet.TJRequest;
import me.reid.Network.Server.TJServer;

public class ClientInputListener extends Thread {

    /**
     * The place game server
     **/
    private TJServer server;

    /**
     * Connection between server and client
     **/
    private NetworkConnection conn;

    /**
     * Constructs new ClientInput
     *
     * @param server
     * @param conn
     */
    public ClientInputListener(TJServer server, NetworkConnection conn) {
        this.server = server;
        this.conn = conn;
    }

    /**
     * Thread that listens for new input from client
     */
    public void run() {
        while (conn.isActive()) {
            System.out.println("sor input");
            TJRequest<?> request = conn.getInput();
            System.out.println("Input val " + request);
            if (request == null) break;
            System.out.println("Received " + request);
            switch (request.getType()) {
                case PURCHASE_SEAT_REQUEST:
                    purchaseSeatRequest(request);
                    break;
            }
        }
    }

    public void purchaseSeatRequest(TJRequest request) {
        // RUN DB INPUT TODO
        NetworkSeat seat = (NetworkSeat) request.getData();
        System.out.println(seat.getX() + " " + seat.getY() + " " + seat.getSeatHolder());
        // TODO verify the input
        TJRequest seatPacket = new TJRequest(TJRequest.RequestType.SEAT_CHANGE_EVENT, seat);
        server.getNetworkHandler().sendPacketToAll(seatPacket);
    }

}