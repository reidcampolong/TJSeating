package me.reid.Network.Connection;


import me.reid.Network.Packet.TJRequest;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * A middleman to handle input/output between client and server
 *
 * @author Reid
 */
public class NetworkConnection {

    /**
     * Connection socket
     **/
    private Socket socket;

    /**
     * Thread to listen to input from either client or server
     **/
    private Thread inputThread;

    /**
     * Input stream from connection
     **/
    private ObjectInputStream objectInputStream;
    /**
     * Output stream from connection
     **/
    private ObjectOutputStream objectOutputStream;

    /**
     * Establishes input and output streams
     *
     * @param socket
     */
    public NetworkConnection(Socket socket) {
        try {
            // TODO verify that connection is still valid
            this.socket = socket;
            this.objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            this.objectInputStream = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            System.out.println("Cannot connect!");
            close();
        }
    }

    /**
     * Starts thread to listen to client input
     *
     * @param thread
     */
    public void startListener(Thread thread) {
        this.inputThread = thread;
        this.inputThread.start();
    }

    /**
     * Reads input from stream
     *
     * @return
     */
    public TJRequest<?> getInput() {
        try {
            TJRequest<?> request = (TJRequest<?>) objectInputStream.readUnshared();
            return request;
        } catch (Exception e) {
            e.printStackTrace();
            close();
        }
        return null;
    }

    /**
     * Writes PlaceRequest to stream
     *
     * @param request
     */
    public void writeOut(TJRequest request) {
        try {
            objectOutputStream.writeUnshared(request);
            objectOutputStream.flush();
        } catch (IOException e) {
            System.out.println("Error writing! Disconnecting");
            close();
        }
    }

    /**
     * Closes connections and thread
     */
    public void close() {
        try {
            this.objectOutputStream.close();
            this.objectInputStream.close();
            if (inputThread != null)
                this.inputThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns socket
     *
     * @return Socket
     */
    public Socket getSocket() {
        return socket;
    }

    /**
     * Check if socket is connected
     *
     * @return true if connected
     */
    public boolean isActive() {
        return socket.isConnected();
    }

}
