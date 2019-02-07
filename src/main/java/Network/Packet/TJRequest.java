package main.java.Network.Packet;

import java.io.Serializable;

/**
 * A packet that is sent between clients and the server
 * @param <E>
 */
public class TJRequest<E extends Serializable> implements Serializable {
    public enum RequestType {
        /** Request a single seat to be changed **/
        PURCHASE_SEAT_REQUEST,
        /** A single seat is changed **/
        SEAT_CHANGE_EVENT,
        /** Sent to show an entire section **/
        SECTION
    }

    /**
     * The request type
     */
    private RequestType type;
    /**
     * The data associated with the request
     */
    private E data;

    /**
     * Create a new request.
     *
     * @param type request type
     * @param data the data
     */
    public TJRequest(RequestType type, E data) {
        this.type = type;
        this.data = data;
    }

    /**
     * Get the type of request.
     *
     * @return request type
     */
    public RequestType getType() {
        return type;
    }

    /**
     * Get the data associated with the request.
     *
     * @return the data
     */
    public E getData() {
        return data;
    }

    /**
     * Utility method for debugging only.
     *
     * @return the tile as a string
     */
    @Override
    public String toString() {
        return "TJRequest{" +
                "type=" + type +
                ", data=" + data +
                '}';
    }
}