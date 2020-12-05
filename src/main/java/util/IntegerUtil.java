package util;

import com.sun.istack.internal.Nullable;

public class IntegerUtil {
    private IntegerUtil() {};

    @Nullable
    public static Integer parseInt(final String str) {
        return parseInt(str, null);
    }

    public static Integer parseInt(final String str, final Integer def) {
        try {
            return Integer.parseInt(str);
        } catch (final Exception e) {
            return def;
        }
    }
}
