package webserver.resolver;

import webserver.request.RequestHeader;

abstract class ViewResolverDefault implements ViewResolver {

    final static ViewResolver EMPTY_RESOLVER = new ViewResolverDefault() {};
    protected RequestHeader requestHeader;

    private ViewResolverDefault() {}

    ViewResolverDefault(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
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
