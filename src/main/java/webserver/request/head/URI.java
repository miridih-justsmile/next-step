package webserver.request.head;

import util.HttpRequestUtils;
import util.StringUtil;

import java.util.Map;

public class URI {

    private final String uriString;

    public URI(final String uriString) {
        this.uriString = uriString;
    }

    public String getUriString() {
        return this.uriString;
    }

    public String getUrlString() {
        return StringUtil.subStringToBefore(this.uriString, "?");
    }

    public Map<String, String> getQueryMap() {
        return HttpRequestUtils.parseQueryString(StringUtil.subStringToAfter(uriString, "?"));
    }
}
