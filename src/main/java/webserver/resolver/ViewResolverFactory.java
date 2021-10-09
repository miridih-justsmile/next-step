package webserver.resolver;

import webserver.request.RequestHead;
import webserver.servlet.Config;

public class ViewResolverFactory {

    public static ViewResolver create(final RequestHead requestHead) {
        final String[] url = requestHead.getHttpHead().getUrl().split("/");
        if (url[0] != null) {
            if (Config.DEFAULT_SERVLET_PATH.equals(url[1])) {
                return new ServletResolver(requestHead);
            }
            else {
                return new FileResolver(requestHead);
            }
        }
        return ViewResolverDefault.EMPTY_RESOLVER;
    }
}
