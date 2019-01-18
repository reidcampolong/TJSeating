package me.reid.Utilities;

public class SeatTranslator {

    public static int[] getPosition(String seatName) {
        if (seatName.length() == 2) {
            // A1
            int index = ((int) seatName.charAt(0)) - 65;
            return new int[]{index, Integer.valueOf(seatName.charAt(1))};
        }
        return null;
    }

    public static String getName(int col, int row) {
        return ((char) (col + 65))+ "" + row;
    }
}
