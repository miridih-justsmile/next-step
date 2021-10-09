package webserver.resolver;

import javassist.NotFoundException;
import util.StringUtil;
import webserver.request.RequestHeader;
import webserver.request.RequestWrapper;
import webserver.servlet.ReflectionsFactory;

import java.lang.reflect.Method;


class ServletResolver extends ViewResolverDefault{
    private Method method;

    ServletResolver(final RequestHeader requestHeader) {
        super(requestHeader);
        this.method = ReflectionsFactory.findApiMethod(requestHeader.getHttpHeader().getMethod(), requestHeader.getHttpHeader().getUrl());
    }

    public byte[] getBodyByte() {
        try {
            if (method != null) {
                return StringUtil.defaultStr(method.invoke(method.getDeclaringClass().newInstance(), new RequestWrapper(requestHeader)).toString(), "")
                        .getBytes();
            }
            throw new NotFoundException("path를 찾을 수 없음");
        } catch (final Exception e) {
            return e.getMessage().getBytes();
        }
    }
}