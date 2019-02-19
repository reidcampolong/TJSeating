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

    public static String getName(int sectionNumber, int maxCols, int y, int x) {
        String title = seatAlphabet[(maxCols - y - 1)];
        int seatNumberMod = 1;

        /**
         * These are hardcoded values specific to TJHS
         */
        if (sectionNumber == 1) {
            if (title.startsWith("M"))
                seatNumberMod -= 2;
        } else if (sectionNumber == 2) {
            if ( (title.startsWith("A") && !title.startsWith("AA")) || (title.startsWith("B") && (!title.startsWith("BB"))))
                seatNumberMod -= 4;
        }


        return title + (x + seatNumberMod);
    }

    /**
     * String title = Trans.numToAlpha(x) + (y + 1);
     * if (region == Region.B) {
     * if (x < 13 && title.startsWith("M")) {
     * String[] splitter = title.split("(?<=\\D)(?=\\d)");
     * title = splitter[0] + (Integer.valueOf(splitter[1]) - 2);
     * }
     * } else if (x < 2 && region == Region.C) {
     * if ((title.startsWith("A") || (title.startsWith("B")))) {
     * String[] splitter = title.split("(?<=\\D)(?=\\d)");
     * title = splitter[0] + (Integer.valueOf(splitter[1]) - 4);
     * }
     * }
     */


    public static String[] seatAlphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "BB", "CC", "DD", "EE"};
}
