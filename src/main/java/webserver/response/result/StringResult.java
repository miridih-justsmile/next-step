package webserver.response.result;

public class StringResult extends ResponseDefault {
    private final String result;
    private StringResult(final String obj) {
        this.result = obj;
    }

    public static StringResult init(final Object obj) {
        return new StringResult(obj.toString());
    }

    @Override
    public byte[] getByte() {
        return result.getBytes();
    }

}
