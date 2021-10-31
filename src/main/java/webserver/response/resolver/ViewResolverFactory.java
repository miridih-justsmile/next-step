package webserver.response.resolver;

import webserver.request.Request;
import webserver.servlet.Config;

public class ViewResolverFactory {

    public static ViewResolver create(final Request request) {
        final String[] url = request.getHttpHeader().getUrl().split("/");
        if (url.length > 1 && Config.DEFAULT_SERVLET_PATH.equals(url[1])) {
            return new ServletResolver(request);
        } else {
            return new FileResolver(request);
        }
    }
}
