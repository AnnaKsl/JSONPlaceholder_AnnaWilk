package utils;

import com.google.gson.Gson;
import io.restassured.response.Response;

import java.lang.reflect.Type;

public class JsonFormatter {
    private static Gson GSON = new Gson();

    public static <T> T convertFromJson(Response response, Class<T> clazz){
        return GSON.fromJson(response.jsonPath().prettify(), (Type) clazz);
    }

}
