package webserver.response.result;

import app.json.JsonParser;
import webserver.response.HTTPStatus;
import webserver.web.ContentType;

import java.nio.charset.StandardCharsets;

public class JsonResult extends ResponseDefault {
    private final String result;

    private JsonResult(Object obj) {
        this.result = toJsonStr(obj);
        super.setContentType(new ContentType.Builder()
                .setContentType("application/json")
                .setCharset(StandardCharsets.UTF_8)
                .build());
    }

    private String toJsonStr(Object obj) {
        try {
            return JsonParser.getInstance().toJson(obj);
        } catch (Exception e) {
            this.httpStatus = HTTPStatus.BAD_REQUEST_400;
            return e.getMessage();
        }
    }

    public static JsonResult init(final Object obj) {
        return new JsonResult(obj);
    }

    @Override
    public byte[] getByte() {
        return result.getBytes();
    }
}
