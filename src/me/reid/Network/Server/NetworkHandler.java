package me.reid.Network.Server;

import me.reid.Network.Connection.NetworkConnection;
import me.reid.Network.Packet.TJRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reid on 2/4/19.
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
