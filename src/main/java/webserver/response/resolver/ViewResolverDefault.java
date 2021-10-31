package webserver.response.resolver;

import webserver.request.Request;
import webserver.web.ContentType;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

abstract class ViewResolverDefault implements ViewResolver {

    final static ViewResolver EMPTY_RESOLVER = new ViewResolverDefault(){};
    protected final Request request;

    private ViewResolverDefault() {
        this.request = new Request();
    }

    protected ViewResolverDefault(final Request request) {
        this.request = request;
    }

    @Override
    public byte[] getBodyByte() {
        return new byte[0];
    }

    @Override
    public ContentType responseContentType() {
        return new ContentType.Builder()
                .setContentType("text/html")
                .setCharset(StandardCharsets.UTF_8)
                .build();
    }

    @Override
    public Charset getCharset() {
        return Charset.defaultCharset();
    }
}
