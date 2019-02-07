package main.java.Network.Server;

import main.java.Network.Connection.NetworkConnection;
import main.java.Network.Packet.TJRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles network connections
 */
public class NetworkHandler {

    private List<NetworkConnection> connectionList;

    public NetworkHandler() {
        this.connectionList = new ArrayList<>();
    }

    public void addConnection(NetworkConnection connection) {
        getConnectionList().add(connection);
    }

    public void removeConnection(NetworkConnection connection) {
        getConnectionList().remove(connection);
    }

    public synchronized void sendPacketToAll(TJRequest packet) {
        System.out.println("Outputting to all connections " + getConnectionList().size());
        for(NetworkConnection c : getConnectionList()) {
            c.writeOut(packet);
        }
    }

    public synchronized List<NetworkConnection> getConnectionList() {
        return connectionList;
    }
}
