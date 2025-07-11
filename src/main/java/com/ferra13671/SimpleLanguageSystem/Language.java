package com.ferra13671.SimpleLanguageSystem;

import com.google.gson.JsonElement;

import java.util.HashMap;

public class Language {
    private final HashMap<String, TextContainer> textContainers = new HashMap<>();

    public Language() {}

    public void addTranslations(HashMap<JsonElement, String> languageMap) {
        languageMap.forEach((inputStream, basicId) ->
                LanguageUtils.loadFromJsonElement(inputStream).forEach((name, value) ->
                        gotoPath(!basicId.isEmpty() ? String.join(".", basicId, name) : name, true).copyFrom(value)
                )
        );
    }

    public String get(String id) {
        return gotoPath(id, false).getText();
    }

    private TextContainer gotoPath(String path, boolean createSubContainers) {
        TextContainer prevTextContainer = null;
        String prevId = null;
        TextContainer textContainer = null;
        for (String containerId : LanguageUtils.split(path)) {
            if (textContainer != null) {
                if (prevTextContainer == null) {
                    prevTextContainer = textContainer;
                    textContainers.put(prevId, prevTextContainer);
                } else
                    prevTextContainer = textContainer;
            }

            textContainer = prevTextContainer != null ? prevTextContainer.getSubTexts() != null ? prevTextContainer.getSubTexts().get(containerId) : null : textContainers.get(containerId);
            if (textContainer == null) {
                if (!createSubContainers)
                    return new TextContainer(path, null);
                else {
                    textContainer = new TextContainer(containerId, null);
                    if (prevTextContainer != null)
                        prevTextContainer.addSubText(containerId, textContainer);
                }
            }
            prevId = containerId;
        }

        return textContainer;
    }
}
