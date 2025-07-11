package com.ferra13671.SimpleLanguageSystem;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class LanguageUtils {

    protected static String[] split(String path) {
        return path.split("\\.");
    }

    protected static HashMap<String, TextContainer> loadFromInputStream(InputStream inputStream) {
        JsonObject jsonObject = JsonParser.parseReader(new InputStreamReader(inputStream)).getAsJsonObject();

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
