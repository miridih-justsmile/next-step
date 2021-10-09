package webserver.request;

public class RequestWrapper {
    private final RequestHeader requestHeader;

    public RequestWrapper(final RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

    public RequestHeader getRequestHead() {
        return requestHeader;
    }
}
