package webserver.response.resolver;

import com.oracle.webservices.internal.api.message.ContentType;
import webserver.request.RequestHeader;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

abstract class ViewResolverDefault implements ViewResolver {

    final static ViewResolver EMPTY_RESOLVER = new ViewResolverDefault(){};
    protected final RequestHeader requestHeader;

    private ViewResolverDefault() {
        this.requestHeader = new RequestHeader();
    }

    protected ViewResolverDefault(final RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    @Override
    public byte[] getBodyByte() {
        return new byte[0];
    }

    @Override
    public ContentType responseContentType() {
        return new ContentType.Builder()
                .contentType("text/html")
                .charset(StandardCharsets.UTF_8.name())
                .build();
    }

    @Override
    public Charset getCharset() {
        return Charset.defaultCharset();
    }
}
