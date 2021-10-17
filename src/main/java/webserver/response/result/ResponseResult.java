package webserver.response.result;

import webserver.web.ContentType;

import java.nio.charset.Charset;

public interface ResponseResult {
    byte[] getByte();
    ContentType getContentType();

    Charset getCharset();

    static ResponseResult findResult(final Object obj) {
        if (obj instanceof JsonResult) {
            return (JsonResult) obj;
        } else if (obj instanceof ModelResult) {
            return (ModelResult) obj;
        } else if (obj instanceof StringResult) {
            return (StringResult) obj;
        } else {
            return StringResult.init(obj);
        }
    }
}
