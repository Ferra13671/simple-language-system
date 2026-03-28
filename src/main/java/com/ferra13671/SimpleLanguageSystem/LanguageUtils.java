package com.ferra13671.SimpleLanguageSystem;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.experimental.UtilityClass;

import java.util.HashMap;

@UtilityClass
class LanguageUtils {

    String[] split(String path) {
        return path.split("\\.");
    }

    HashMap<String, TextContainer> loadFromJsonObject(JsonObject jsonObject) {
        HashMap<String, TextContainer> languageMap = new HashMap<>();

        jsonObject.asMap().forEach((name, value) -> {
            if (value.isJsonPrimitive()) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) value;

                if (jsonPrimitive.isString())
                    languageMap.put(
                            name,
                            new TextContainer(jsonPrimitive.getAsString(), null)
                    );
            }
        });

        return languageMap;
    }
}
