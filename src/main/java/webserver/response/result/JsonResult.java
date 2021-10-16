package webserver.response.result;

import app.json.JsonParser;
import com.oracle.webservices.internal.api.message.ContentType;

import java.nio.charset.StandardCharsets;

public class JsonResult extends AbstractResult {
    private JsonResult(Object obj) {
        super(obj);
        super.setContentType(new ContentType.Builder()
                .contentType("application/json")
                .charset(StandardCharsets.UTF_8.name())
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
