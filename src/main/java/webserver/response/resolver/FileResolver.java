package webserver.response.resolver;

import webserver.request.Request;
import webserver.response.result.FileResult;
import webserver.response.result.ResponseResult;
import webserver.response.result.StringResult;

class FileResolver extends ViewResolverDefault {

    FileResolver(final Request request) {
        super(request);
    }

    @Override
    protected ResponseResult initResult() {
        try {
            return FileResult.init(request.getHttpHeader().getUri().getUrlString());
        } catch (Exception e) {
            return StringResult.init(e.getMessage());
        }
    }
}
