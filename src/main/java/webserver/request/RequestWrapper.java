package webserver.request;

public class RequestWrapper {
    private final RequestHead requestHead;

    public RequestWrapper(final RequestHead requestHead) {
        this.requestHead = requestHead;
    }

    public RequestHead getRequestHead() {
        return requestHead;
    }
}
