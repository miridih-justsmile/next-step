package webserver.response.result;

import com.oracle.webservices.internal.api.message.ContentType;

import java.nio.charset.Charset;

public abstract class AbstractResult implements ResponseResult {
    protected final Object obj;
    protected ContentType contentType = new ContentType.Builder()
            .contentType("text/html")
            .charset(Charset.defaultCharset().name())
            .build();

    protected AbstractResult(Object obj) {
        this.obj = obj;
    }

    protected void setContentType(final ContentType contentType) {
        this.contentType = contentType;
    }

    @Override
    public byte[] getByte() {
        return obj.toString().getBytes();
    }

    @Override
    public Charset getCharset() {
        return Charset.defaultCharset();
    }

    @Override
    public ContentType getContentType() {
        return contentType;
    }
}
