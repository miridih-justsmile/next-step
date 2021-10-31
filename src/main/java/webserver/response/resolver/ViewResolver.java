package webserver.response.resolver;

public interface ViewResolver {
    byte[] getBodyByte();
    byte[] getHeaderByte();
}
