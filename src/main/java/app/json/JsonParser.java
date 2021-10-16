package app.json;

import com.google.gson.Gson;

public class JsonParser {

    private final Gson gson;

    private JsonParser() {
        this.gson = new Gson();
    }

    public static JsonParser getInstance() {
        return new JsonParser();
    }

    public String toJson(final Object o) {
        return this.gson.toJson(o);
    }
}
