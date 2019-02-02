package me.reid.Section.Admin;

import me.reid.Section.Seat.Seat;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles group select features of the program.
 * This class is partially static to be utilized from anywhere in the project.
 */
public class GroupClickHandler {

    private static GroupClickHandler instance;
    private static boolean isEnabled = false;
    private static List<Seat> groupSeatList;

    private GroupButton groupButton;

    public GroupClickHandler(GroupButton button) {
        instance = this;
        groupSeatList = new ArrayList<>();
        this.groupButton = button;

    }

    public static boolean isGroupSelectEnabled() {
        return instance.groupButton.isSelected();
    }

    public static void addToList(Seat seat) {
        if(!groupSeatList.contains(seat))
            groupSeatList.add(seat);
    }

    public static List<Seat> getGroupSeatList() {
        return groupSeatList;
    }

    private static GroupClickHandler i() {
        return instance;
    }
}
