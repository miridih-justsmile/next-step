package webserver.request;

import org.jetbrains.annotations.Nullable;
import util.StringUtil;
import webserver.request.head.HttpHeader;

public class RequestWrapper {
    private final HttpHeader httpHeader;

    public RequestWrapper(final RequestHeader requestHeader) {
        this.httpHeader = requestHeader.getHttpHeader();
    }

    public HttpHeader getHttpHeader() {
        return httpHeader;
    }

    @Nullable
    public String getParameter(final String key) {
        return getParameter(key, null);
    }

    public String getParameter(final String key, final String defaultString) {
        return StringUtil.defaultStr(this.getHttpHeader().getUri().getQueryMap().get(key), defaultString);
    }
}
