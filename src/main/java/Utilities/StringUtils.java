package main.java.Utilities;

/**
 * Created by Reid on 1/31/19.
 */
public class StringUtils {

    public static String properCase(String string) {
        return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
    }
}
