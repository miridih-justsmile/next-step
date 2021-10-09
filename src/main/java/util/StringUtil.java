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

    /**
     * 처음 String index 부터 찾아 RegExp를 통해 마지막 이전 String 을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @param addPos +시 오른쪽으로 범위 이동 -시 왼쪽으로 범위 이동
     * @return 최종 잘린 String
     */
    public static String subStringToBefore(final String str, final String regExp, final int addPos) {
        try {
            final int startRegExpIdx = str.indexOf(regExp) + addPos;
            return str.substring(0, startRegExpIdx);
        } catch (StringIndexOutOfBoundsException e) {
            return str;
        }
    }

    /**
     * 처음 String index 부터 찾아 RegExp를 통해 마지막 이전 String 을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @return 최종 잘린 String
     */
    public static String subStringToBefore(final String str, final String regExp) {
        return subStringToBefore(str, regExp, 0);
    }

    /**
     * 처음 String index 부터 찾아 RegExp를 통해 마지막 이후 String 을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @param addPos +시 오른쪽으로 범위 이동 -시 왼쪽으로 범위 이동
     * @return 최종 잘린 String
     */
    public static String subStringToAfter(final String str, final String regExp, final int addPos) {
        try {
            final int startRegExpIdx = str.indexOf(regExp) + addPos;
            return str.substring(startRegExpIdx);
        } catch (StringIndexOutOfBoundsException e) {
            return str;
        }
    }

    /**
     * 처음 String index 부터 찾아 RegExp를 통해 마지막 이후 String 을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @return 최종 잘린 String
     */
    public static String subStringToAfter(final String str, final String regExp) {
        return subStringToAfter(str, regExp, 1);
    }

    /**
     * 마지막 String Index 부터 찾아, 특정 RegExp를 통해 마지막부터 이전 String을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @param addPos +시 오른쪽으로 범위 이동 -시 왼쪽으로 범위 이동
     * @return 최종 잘린 String
     */
    public static String lastSubStringToBefore(final String str, final String regExp, final int addPos) {
        try {
            final int lastRegExpIdx = str.lastIndexOf(regExp) + addPos;
            return str.substring(0, lastRegExpIdx);
        } catch (StringIndexOutOfBoundsException e) {
            return str;
        }
    }

    /**
     * 마지막 String Index 부터 찾아, 특정 RegExp를 통해 마지막부터 이전 String을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @return 최종 잘린 String
     */
    public static String lastSubStringToBefore(final String str, final String regExp) {
        return lastSubStringToBefore(str, regExp, 0);
    }

    /**
     * 마지막 String Index 부터 찾아, 특정 RegExp를 통해 마지막부터 이후 String을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @param addPos +시 오른쪽으로 범위 이동 -시 왼쪽으로 범위 이동
     * @return 최종 잘린 String
     */
    public static String lastSubStringToAfter(final String str, final String regExp, final int addPos) {
        try {
            final int regIdx = str.lastIndexOf(regExp) + addPos;
            return str.substring(regIdx);
        } catch (StringIndexOutOfBoundsException e) {
            return str;
        }
    }

    /**
     * 마지막 String Index 부터 찾아, 특정 RegExp를 통해 마지막부터 이후 String을 추출한다.
     * @param str 원본 String
     * @param regExp 추출할 Key
     * @return 최종 잘린 String
     */
    public static String lastSubStringToAfter(final String str, final String regExp) {
        return lastSubStringToAfter(str, regExp, 1);
    }
}
