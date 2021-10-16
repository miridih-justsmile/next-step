package webserver.response.result;

public class StringResult extends AbstractResult {
    private StringResult(final Object obj) {
        super(obj);
    }

    public static StringResult init(final Object obj) {
        return new StringResult(obj);
    }

    @Override
    public byte[] getByte() {
        return obj.toString().getBytes();
    }

}
