package webserver.response.resolver;

import webserver.request.Request;
import webserver.response.HTTPStatus;
import webserver.response.result.ResponseResult;
import webserver.web.ContentType;

abstract class ViewResolverDefault implements ViewResolver {
    protected final Request request;
    protected final ResponseResult result;

    protected ViewResolverDefault(final Request request) {
        this.request = request;
        this.result = initResult();
    }

    @Override
    public final byte[] getHeaderByte() {
        final StringBuilder builder = new StringBuilder();
        final HTTPStatus httpStatus = this.getStatus();
        final ContentType contentType = this.getContentType();

        builder.append(String.format("HTTP/1.1 %s %s \r\n", httpStatus.getStatus(), httpStatus.getDesc()))
                .append(String.format("Content-Type: %s;charset=%s\r\n", contentType.getContentType(), contentType.getCharset().name()))
                .append(String.format("Content-Length: %s\r\n", this.result.getByte().length))
                .append("\r\n");
        return builder.toString().getBytes(contentType.getCharset());
    }

    @Override
    public final byte[] getBodyByte() {
        return this.result.getByte();
    }

    protected ContentType getContentType() {
        return result.getContentType();
    }

    protected abstract ResponseResult initResult();
    protected HTTPStatus getStatus() {
        return result.getHttpStatus();
    };
}
