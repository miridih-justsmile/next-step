package util;

public class ArraysUtil {

    private ArraysUtil(){};

    public static boolean isNullOrEmpty(final Object[] arrays) {
        return arrays == null || arrays.length == 0;
    }

    public static boolean isNullOrEmpty(final Iterable<Object> iterable) {
        return iterable == null || !iterable.iterator().hasNext();
    }

    public static boolean isNotEmpty(final Object[] arrays) {
        return !isNullOrEmpty(arrays);
    }

    public static boolean isNotEmpty(final Iterable<Object> iterable) {
        return !isNullOrEmpty(iterable);
    }
}
