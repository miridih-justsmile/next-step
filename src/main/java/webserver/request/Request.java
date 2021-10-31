package webserver.request;

import lombok.ToString;
import util.HttpRequestUtils;
import util.IOUtils;
import util.IntegerUtil;
import webserver.request.http.HttpHeader;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

@ToString
public class Request {
    private final Map<HeaderTitle, String> headerMap = new EnumMap<>(HeaderTitle.class);
    private final String body;

    public Request(final BufferedReader reader) throws IOException {
        initHeader(reader);
        this.body = initBody(reader);
    }

    private void initHeader(final BufferedReader reader) throws IOException {
        String line = "firstLine";
        while (!"".equals(line)) {
            if ("firstLine".equals(line)) {
                line = reader.readLine();
                headerMap.put(Request.HeaderTitle.HTTP, line);
            } else {
                line = reader.readLine();
                if (!line.isEmpty()) {
                    final HttpRequestUtils.Pair parseHeader = HttpRequestUtils.parseHeader(line);
                    headerMap.put(Request.HeaderTitle.getTitle(parseHeader.getKey()), parseHeader.getValue());
                }
            }
        }
    }

    private String initBody(final BufferedReader reader) throws IOException {
        return IOUtils.readData(reader, IntegerUtil.parseInt(headerMap.get(HeaderTitle.CONTENT_LENGTH), 0));
    }

    public HttpHeader getHttpHeader() {
        return new HttpHeader(headerMap.get(HeaderTitle.HTTP));
    }

    public String getBody() {
        return this.body;
    }

    public enum HeaderTitle {
        HTTP("GET, POST, PUT, DELETE"),
        HOST("Host"),
        CONNECTION("Connection"),
        PRAGMA("Pragma"),
        CACHE_CONTROL("Cache-Control"),
        UPGRADE_INSECURE_REQUEST("Upgrade-Insecure-Requests"),
        USER_AGENT("User-Agent"),
        ACCEPT("Accept"),
        SEC_FETCH_SITE("Sec-Fetch-Site"),
        SEC_FETCH_MODE("Sec-Fetch-Mode"),
        SEC_FETCH_USER("Sec-Fetch-User"),
        SEC_FETCH_DEST("Sec-Fetch-Dest"),
        ACCEPT_ENCODING("Accept-Encoding"),
        ACCEPT_LANGUAGE("Accept-Language"),
        COOKIE("Cookie"),
        NOT_FOUND("NOT_FOUND"),
        CONTENT_LENGTH("Content-Length")
        ;

        private final String key;

        HeaderTitle(final String key) {
            this.key = key;
        }

        public static HeaderTitle getTitle(final String str) {
            return Arrays.stream(values()).filter(headerTitle -> headerTitle.key.equals(str)).findFirst().orElse(NOT_FOUND);
        }
    }
}
