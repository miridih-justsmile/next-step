package webserver.request.http;

import lombok.Getter;
import webserver.web.URI;

@Getter
public class HttpHeader {
    private final HttpMethod method;
    private final URI uri;
    private final String http;

    public HttpHeader(final String str) {
        final String[] strings = str.split("\\s", 3);
        this.method = HttpMethod.find(strings[0]);
        this.uri = new URI.Default(strings[1]);
        this.http = strings[2];
    }

    public String getUrl() {
        return uri.getUrlString();
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
