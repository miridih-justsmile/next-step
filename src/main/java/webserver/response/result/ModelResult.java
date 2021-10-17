package webserver.response.result;

import webserver.web.ContentType;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public class ModelResult implements ResponseResult {
    private final Map<String, Object> coreMap;

    public ModelResult(Map<String, Object> coreMap) {
        this.coreMap = coreMap;
    }

    public static ModelResult init() {
        return new ModelResult(new HashMap<>());
    }

    public static ModelResult init(Map<String, Object> coreMap) {
        return new ModelResult(coreMap);
    }

    public ModelResult add(final String key, final Object obj) {
        this.coreMap.put(key, obj);
        return this;
    }

    @Override
    public Charset getCharset() {
        return Charset.defaultCharset();
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
