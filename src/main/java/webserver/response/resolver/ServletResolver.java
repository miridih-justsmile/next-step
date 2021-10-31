package webserver.response.resolver;

import webserver.request.Request;
import webserver.request.RequestWrapper;
import webserver.response.result.ResponseResult;
import webserver.response.result.StringResult;
import webserver.servlet.ReflectionsFactory;

import java.lang.reflect.Method;


class ServletResolver extends ViewResolverDefault{
    ServletResolver(final Request request) {
        super(request);
    }

    @Override
    protected ResponseResult initResult() {
        try {
            final Method method = ReflectionsFactory.findApiMethod(request.getHttpHeader().getMethod(), request.getHttpHeader().getUrl());
            return ResponseResult.findResult(method.invoke(method.getDeclaringClass().newInstance(), new RequestWrapper(request)));
        } catch (Exception e) {
            return StringResult.init(e.getMessage());
        }
    }
}