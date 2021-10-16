package webserver.request;

import org.jetbrains.annotations.Nullable;
import webserver.request.head.HttpHeader;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class RequestHeader {
    private final Map<Title, String> enumMap = new EnumMap<>(Title.class);

    public void setHeader(final Title headerTitle, final String str) {
        enumMap.put(headerTitle, str);
    }

    public HttpHeader getHttpHeader() {
        return new HttpHeader(enumMap.get(Title.HTTP));
    }

    public enum Title {
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
        ;

        private final String key;

        Title(final String key) {
            this.key = key;
        }

        @Nullable
        public static Title getTitle(final String str) {
            return Arrays.stream(values()).filter(title -> title.key.equals(str)).findFirst().orElse(NOT_FOUND);
        }
    }
}
