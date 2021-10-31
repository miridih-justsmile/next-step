package webserver.response.result;

import webserver.response.HTTPStatus;
import webserver.web.ContentType;

public interface ResponseResult {
    byte[] getByte();
    ContentType getContentType();
    HTTPStatus getHttpStatus();

    static ResponseResult findResult(final Object obj) {
        if (obj instanceof JsonResult) {
            return (JsonResult) obj;
        } else if (obj instanceof ApiResult) {
            return (ApiResult) obj;
        } else if (obj instanceof StringResult) {
            return (StringResult) obj;
        } else if (obj instanceof FileResult) {
            return (FileResult) obj;
        } else {
            return StringResult.init(obj);
        }
    }
}
