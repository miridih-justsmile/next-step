package webserver.request.http;

import java.util.Arrays;

public enum HttpMethod {
    GET,
    POST,
    PUT,
    DELETE,
    OPTIONS;

    public boolean equals(final String str) {
        return this.name().equalsIgnoreCase(str);
    }

    public static HttpMethod find(final String str) {
        return Arrays.stream(values())
                .filter(httpMethod -> httpMethod.equals(str))
                .findFirst()
                .orElse(null);
    }
}
