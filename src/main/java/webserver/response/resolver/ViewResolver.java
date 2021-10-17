package webserver.response.resolver;

import webserver.web.ContentType;

import java.nio.charset.Charset;

public interface ViewResolver {

    byte[] getBodyByte();

    ContentType responseContentType();

    Charset getCharset();
}
