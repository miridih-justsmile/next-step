package webserver.response.resolver;

import com.oracle.webservices.internal.api.message.ContentType;
import javassist.NotFoundException;
import util.StringUtil;
import webserver.request.RequestHeader;
import webserver.request.RequestWrapper;
import webserver.response.result.ResponseResult;
import webserver.servlet.ReflectionsFactory;

import java.lang.reflect.Method;
import java.nio.charset.Charset;


class ServletResolver extends ViewResolverDefault{
    private final Method method;
    private final ResponseResult result;

    ServletResolver(final RequestHeader requestHeader) throws ViewResolverException {
        super(requestHeader);
        this.method = ReflectionsFactory.findApiMethod(requestHeader.getHttpHeader().getMethod(), requestHeader.getHttpHeader().getUrl());
        this.result = methodRun();
    }

    @Override
    public byte[] getBodyByte() {
        return result.getByte();
    }
    @Override
    public ContentType responseContentType() {
        return result.getContentType();
    }

    @Override
    public Charset getCharset() {
        return result.getCharset();
    }
    private ResponseResult methodRun() throws ViewResolverException {
        try {
            return ResponseResult.findResult(method.invoke(method.getDeclaringClass().newInstance(), new RequestWrapper(requestHeader)));
        } catch (final Exception e) {
            throw new ViewResolverException("결과를 생성할 수 없습니다.", e);
        }
    }
}