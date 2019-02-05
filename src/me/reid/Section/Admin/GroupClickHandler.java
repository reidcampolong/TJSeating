package me.reid.Section.Admin;

import me.reid.Section.Admin.Buttons.GroupButton;
import me.reid.Section.Seat.Seat;

import java.util.ArrayList;
import java.util.List;

/**
 * This class handles group select features of the program.
 * This class is partially static to be utilized from anywhere in the project.
 */
public class GroupClickHandler {

    private static GroupClickHandler instance;
    private List<Seat> groupSeatList;
    private GroupButton groupButton;

    public GroupClickHandler(GroupButton button) {
        instance = this;
        groupSeatList = new ArrayList<>();
        this.groupButton = button;

    }

    /**
     * Turns group select on/off
     */
    public void toggleGroupSelect(boolean toggleOn) {
        if(toggleOn) {
            groupSeatList.clear();
        } else {
            //SeatHandler.
        }
    }

    /**
     * Checks status of group select
     * @return
     */
    public boolean isGroupSelectEnabled() {
        return instance.groupButton.isSelected();
    }

    /**
     * Adds a seat to group select
     * @param seat
     */
    public void addToList(Seat seat) {
        if(!groupSeatList.contains(seat))
            groupSeatList.add(seat);
    }

    public void remFromList(Seat seat) {
        groupSeatList.remove(seat);
    }

    public List<Seat> getGroupSeatList() {
        return groupSeatList;
    }

    public static GroupClickHandler i() {
        return instance;
    }
}
