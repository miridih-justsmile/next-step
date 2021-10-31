package webserver.response.result;

import webserver.response.HTTPStatus;
import webserver.web.ContentType;

import java.nio.charset.Charset;

public abstract class ResponseDefault implements ResponseResult {
    protected ContentType contentType = new ContentType.Builder()
            .setContentType("text/html")
            .setCharset(Charset.defaultCharset())
            .build();

    protected HTTPStatus httpStatus = HTTPStatus.OK_200;

    protected void setContentType(final ContentType contentType) {
        this.contentType = contentType;
    }

    public abstract byte[] getByte();

    @Override
    public ContentType getContentType() {
        return contentType;
    }

    @Override
    public HTTPStatus getHttpStatus() {
        return httpStatus;
    }
}
