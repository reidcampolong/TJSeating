package main.java.Utilities;

/**
 * Translates seat names back and forth
 */
public class SeatTranslator {

    /** The order of seats **/
    public static final String[] seatAlphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "AA", "BB", "CC", "DD", "EE"};

    /**
     * Gets a seat's display name in a section
     *
     * @param sectionNumber
     * @param maxCols
     * @param y
     * @param x
     * @return
     */
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
            if ((title.startsWith("A") && !title.startsWith("AA")) || (title.startsWith("B") && (!title.startsWith("BB"))))
                seatNumberMod -= 4;
        }


        return title + (x + seatNumberMod);
    }

}
