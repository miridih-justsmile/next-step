package webserver.response.resolver;

import com.oracle.webservices.internal.api.message.ContentType;

import java.nio.charset.Charset;

public interface ViewResolver {

    byte[] getBodyByte();

    ContentType responseContentType();

    Charset getCharset();
}
