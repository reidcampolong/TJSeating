package main.java.Utilities;

/**
 * Utilities class for string functions
 */
public class StringUtils {

    public static String properCase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }
}
