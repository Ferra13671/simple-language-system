package com.ferra13671.SimpleLanguageSystem;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import java.util.HashMap;

public class LanguageUtils {

    protected static String[] split(String path) {
        return path.split("\\.");
    }

    protected static HashMap<String, TextContainer> loadFromJsonElement(JsonElement jsonElement) {
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        HashMap<String, TextContainer> languageMap = new HashMap<>();

        jsonObject.asMap().forEach((name, value) -> {
            if (value.isJsonPrimitive()) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) value;
                if (jsonPrimitive.isString())
                    languageMap.put(name, new TextContainer(jsonPrimitive.getAsString(), null));
            }
        });

        return languageMap;
    }
}
