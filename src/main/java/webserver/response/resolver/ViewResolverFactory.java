package webserver.response.resolver;

import webserver.request.RequestHeader;
import webserver.servlet.Config;

public class ViewResolverFactory {

    public static ViewResolver create(final RequestHeader requestHeader) {
        try {
            final String[] url = requestHeader.getHttpHeader().getUrl().split("/");
            if (url.length > 1 && Config.DEFAULT_SERVLET_PATH.equals(url[1])) {
                return new ServletResolver(requestHeader);
            } else {
                return new FileResolver(requestHeader);
            }
        } catch (final Exception e) {
            return ViewResolverDefault.EMPTY_RESOLVER;
        }
    }
}
