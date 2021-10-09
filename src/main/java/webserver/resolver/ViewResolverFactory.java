package webserver.resolver;

import webserver.request.RequestHead;

public class ViewResolverFactory {

    public static ViewResolver create(final RequestHead requestHead) {
        final String[] url = requestHead.getHttpHead().getUrl().split("/");
        if (url[0] != null) {
            if (url[1].equals("api")) {
                return new ServletResolver(requestHead);
            }
            else {
                return new FileResolver(requestHead);
            }
        }
        return ViewResolverDefault.EMPTY_RESOLVER;
    }
}
