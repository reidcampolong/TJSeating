package main.java.Section.Admin;

import main.java.Section.Admin.Buttons.GroupButton;
import main.java.Section.Seat.Seat;
import main.java.Section.Seat.SeatHandler;

import java.util.ArrayList;
import java.util.Iterator;
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
        if (toggleOn) {
            groupSeatList.clear();
        } else {
            if (groupSeatList.size() > 0)
                SeatHandler.handleInputForGroupSelect(groupSeatList);
            removeAll();
            groupButton.setSelected(false);
        }
    }

    /**
     * Checks status of group select
     *
     * @return
     */
    public boolean isGroupSelectEnabled() {
        return instance.groupButton.isSelected();
    }

    /**
     * Adds a seat to group select
     *
     * @param seat
     */
    public void addToList(Seat seat) {
        if (!groupSeatList.contains(seat)) {
            seat.updateSelected(true);
            groupSeatList.add(seat);
        }
    }

    public void remFromList(Seat seat) {
        if (groupSeatList.contains(seat)) {
            seat.updateSelected(false);
            groupSeatList.remove(seat);
        }
    }

    public void removeAll() {
        Iterator<Seat> it = groupSeatList.iterator();
        while(it.hasNext()) {
            it.next().updateSelected(false);
            it.remove();
        }
    }

    public List<Seat> getGroupSeatList() {
        return groupSeatList;
    }

    public static GroupClickHandler i() {
        return instance;
    }
}
