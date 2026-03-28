package com.ferra13671.SimpleLanguageSystem;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
class LanguageUtils {

    String[] split(String path) {
        return path.split("\\.");
    }

    Map<String, TextContainer> parseTranslations(JsonObject jsonObject) {
        return parseTranslations(jsonObject, null);
    }

    Map<String, TextContainer> parseTranslations(JsonObject jsonObject, String basicId) {
        Map<String, TextContainer> map = new HashMap<>();

        jsonObject.asMap().forEach((name, value) -> {
            if (value.isJsonPrimitive()) {
                JsonPrimitive jsonPrimitive = (JsonPrimitive) value;

                if (jsonPrimitive.isString()) {
                    String s = name.isEmpty() ?
                            basicId == null ? "" : basicId
                            :
                            basicId == null ? name : basicId + "." + name;

                    map.put(
                            s,
                            new TextContainer(
                                    jsonPrimitive.getAsString(),
                                    null
                            )
                    );
                }
            }

            if (value.isJsonObject())
                map.putAll(parseTranslations(value.getAsJsonObject(), (basicId == null || basicId.isEmpty() ? "" : basicId + ".").concat(name)));
        });

        return map;
    }
}
