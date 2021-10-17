package webserver.web;

import util.HttpRequestUtils;
import util.StringUtil;

import java.util.Map;

public interface URI {

    String getUriString();
    String getUrlString();
    Map<String, String> getQueryMap();

    class Default implements URI {
        private final String uriString;

        public Default(final String uriString) {
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

}
