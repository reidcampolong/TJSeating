package me.reid.Section.Seat;

public enum Status {
    OCCUPIED(0), AVAILABLE(1), BLACK(2), HANDICAP(3);

    private int id;

    Status(int id) {
        this.id = id;
    }
}
