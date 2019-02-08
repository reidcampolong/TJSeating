package main.java.Utilities;

public class SeatTranslator {

    public static int[] getPosition(String seatName) {
        if (seatName.length() == 2) {
            // A1
            int index = ((int) seatName.charAt(0)) - 65;
            return new int[]{index, Integer.valueOf(seatName.charAt(1))};
        }
        return null;
    }

    public static String getName(int maxCols, int y, int x) {
        return seatAlphabet[(maxCols - y - 1)] + (x + 1);
    }


    public static String[] seatAlphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "BB", "CC", "DD", "EE"};
}
