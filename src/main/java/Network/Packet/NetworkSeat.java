package main.java.Network.Packet;


import java.io.Serializable;

/**
 * A serializable seat that is sent over the network
 */
public class NetworkSeat implements Serializable {

    private int sectionNumber;
    private String seatStatus;
    private String seatHolder;

    private int x, y;

    public NetworkSeat(int sectionNumber, String status, String holder, int x, int y) {
        this.sectionNumber = sectionNumber;
        this.seatStatus = status;
        this.seatHolder = holder;
        this.x = x;
        this.y = y;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public String getSeatHolder() {
        return seatHolder;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
