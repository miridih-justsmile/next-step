package webserver.response.result;

import app.json.JsonParser;
import webserver.web.ContentType;

import java.nio.charset.StandardCharsets;

public class JsonResult extends AbstractResult {
    private JsonResult(Object obj) {
        super(obj);
        super.setContentType(new ContentType.Builder()
                .setContentType("application/json")
                .setCharset(StandardCharsets.UTF_8)
                .build());
    }

    public static JsonResult init(Object obj) {
        return new JsonResult(obj);
    }

    @Override
    public byte[] getByte() {
        return JsonParser.getInstance().toJson(obj).getBytes();
    }
}
