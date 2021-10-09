package webserver.resolver;

import webserver.request.RequestHead;

abstract class ViewResolverDefault implements ViewResolver {

    final static ViewResolver EMPTY_RESOLVER = new ViewResolverDefault() {};
    protected RequestHead requestHead;

    private ViewResolverDefault() {}

    ViewResolverDefault(RequestHead requestHead) {
        this.requestHead = requestHead;
    }

    @Override
    public byte[] getBodyByte() {
        return new byte[0];
    }

    @Override
    public String responseContentType() {
        return "text/html";
    }
}
