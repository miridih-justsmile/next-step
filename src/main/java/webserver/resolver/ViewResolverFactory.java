package webserver.resolver;

import webserver.request.RequestHeader;
import webserver.servlet.Config;

public class ViewResolverFactory {

    public static ViewResolver create(final RequestHeader requestHeader) {
        final String[] url = requestHeader.getHttpHeader().getUrl().split("/");
        if (url[0] != null) {
            if (Config.DEFAULT_SERVLET_PATH.equals(url[1])) {
                return new ServletResolver(requestHeader);
            }
            else {
                return new FileResolver(requestHeader);
            }
        }
        return ViewResolverDefault.EMPTY_RESOLVER;
    }
}
