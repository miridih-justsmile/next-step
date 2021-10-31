package webserver.response.resolver;

import webserver.request.Request;
import webserver.request.RequestWrapper;
import webserver.servlet.Config;

public class ViewResolverFactory {

    public static ViewResolver create(final Request request) {
        try {
            final String[] url = request.getHttpHeader().getUrl().split("/");
            if (url.length > 1 && Config.DEFAULT_SERVLET_PATH.equals(url[1])) {
                return new ServletResolver(request);
            } else {
                return new FileResolver(request);
            }
        } catch (final Exception e) {
            return ViewResolverDefault.EMPTY_RESOLVER;
        }
    }
}
