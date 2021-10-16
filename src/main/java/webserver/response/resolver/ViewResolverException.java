package webserver.response.resolver;

public class ViewResolverException extends Exception {
    public ViewResolverException(final String message, final Exception e) {
        super(message, e);
    }
}
