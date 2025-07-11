package com.ferra13671.SimpleLanguageSystem;

import java.io.InputStream;
import java.util.HashMap;

public class Language {
    private final HashMap<String, TextContainer> textContainers = new HashMap<>();

    public Language() {}

    public void addTranslations(HashMap<InputStream, String> languageMap) {
        languageMap.forEach((inputStream, basicId) ->
                LanguageUtils.loadFromInputStream(inputStream).forEach((name, value) ->
                        gotoPath(String.join(".", basicId, name), true).copyFrom(value)
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

            textContainer = prevTextContainer != null ? prevTextContainer.getSubTexts().get(containerId) : textContainers.get(containerId);
            if (textContainer == null) {
                if (!createSubContainers)
                    return new TextContainer(path, null);
                else {
                    textContainer = new TextContainer("", null);
                    if (prevTextContainer != null)
                        prevTextContainer.addSubText(containerId, textContainer);
                }
            }
            prevId = containerId;
        }

        return textContainer;
    }
}
