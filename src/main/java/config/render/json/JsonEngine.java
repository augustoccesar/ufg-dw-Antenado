package config.render.json;

import com.google.gson.Gson;
import spark.ResponseTransformer;

/**
 * Created by augustoccesar on 7/4/16.
 */
public class JsonEngine implements ResponseTransformer {
    private Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }

    public static <T extends Object> T  fromJson(String json, Class<T> classe) {
        return new Gson().fromJson(json, classe);
    }
}
