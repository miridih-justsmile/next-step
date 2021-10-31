package webserver.response.result;

import webserver.web.ContentType;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ApiResult extends ResponseDefault {
    private final Map<String, Object> coreMap;

    private ApiResult(final Map<String, Object> coreMap) {
        this.coreMap = coreMap;
    }

    public static ApiResult init() {
        return new ApiResult(new HashMap<>());
    }

    public ApiResult put(final String key, final Object obj) {
        this.coreMap.put(key, obj);
        return this;
    }

    @Override
    public ContentType getContentType() {
        return null;
    }

    @Override
    public byte[] getByte() {
        return coreMap.toString().getBytes();
    }
}
