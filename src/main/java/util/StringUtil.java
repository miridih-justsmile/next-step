package util;

public class StringUtil {
    private StringUtil(){};

    public static boolean isNullOrEmpty(final String str) {
        return str == null || str.length() == 0;
    }

    public static boolean isNotEmpty(final String str) {
        return !isNullOrEmpty(str);
    }

    public static String defaultStr(final String str, final String defaultStr) {
        return isNotEmpty(str) ? str : defaultStr;
    }
}
