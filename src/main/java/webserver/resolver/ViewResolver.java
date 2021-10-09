package webserver.resolver;

public interface ViewResolver {

    byte[] getBodyByte();

    String responseContentType();
}
