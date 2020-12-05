package webserver.request.head;

public class HttpHead {
    private final String method;
    private final String url;
    private final String http;

    public HttpHead(final String str) {
        final String[] strings = str.split(" ", 4);
        this.method = strings[0];
        this.url = strings[1];
        this.http = strings[2];
    }

    public String getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getHttp() {
        return http;
    }

    @Override
    public String toString() {
        return "HttpHead{" +
                "method='" + method + '\'' +
                ", url='" + url + '\'' +
                ", http='" + http + '\'' +
                '}';
    }
}
