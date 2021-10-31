package webserver.request;

import org.jetbrains.annotations.Nullable;
import util.HttpRequestUtils;
import util.StringUtil;
import webserver.request.http.HttpHeader;

import java.util.HashMap;
import java.util.Map;

public class RequestWrapper {
    private final HttpHeader httpHeader;
    private final String requestBody;
    private final Map<String, String> parameterMap;

    public RequestWrapper(final Request request) {
        this.httpHeader = request.getHttpHeader();
        this.requestBody = request.getBody();
        this.parameterMap = initParameterMap();
    }

    private Map<String, String> initParameterMap() {
        switch (this.httpHeader.getMethod()) {
            case GET:
                return this.getHttpHeader().getUri().getQueryMap();
            case PUT:
            case POST:
            case DELETE:
                return HttpRequestUtils.parseQueryString(getRequestBody());
            default:
                return new HashMap<>();
        }
    }

    public HttpHeader getHttpHeader() {
        return httpHeader;
    }

    public String getRequestBody() {
        return this.requestBody;
    }

    @Nullable
    public String getParameter(final String key) {
        return getParameter(key, null);
    }

    public String getParameter(final String key, final String defaultString) {
        return StringUtil.defaultStr(this.parameterMap.get(key), defaultString);
    }
}
