package webserver.request.head;

import webserver.web.URI;

public class HttpHeader {
    private final String method;
    private final URI uri;
    private final String http;

    public HttpHeader(final String str) {
        final String[] strings = str.split("\\s", 3);
        this.method = strings[0];
        this.uri = new URI.Default(strings[1]);
        this.http = strings[2];
    }

    public String getMethod() {
        return method;
    }

    public URI getUri() {
        return uri;
    }

    public String getUrl() {
        return uri.getUrlString();
    }

    public String getHttp() {
        return http;
    }

    @Override
    public String toString() {
        return "HttpHead{" +
                "method='" + method + '\'' +
                ", uri='" + uri.getUriString() + '\'' +
                ", http='" + http + '\'' +
                '}';
    }
}
