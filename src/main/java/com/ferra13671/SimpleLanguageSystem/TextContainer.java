package com.ferra13671.SimpleLanguageSystem;

import java.util.HashMap;

public class TextContainer {
    private String text;
    private HashMap<String, TextContainer> subTexts;

    public TextContainer(String text, HashMap<String, TextContainer> subTexts) {
        this.text = text;
        this.subTexts = subTexts;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void addSubText(String id, TextContainer textContainer) {
        if (subTexts == null) subTexts = new HashMap<>();
        subTexts.put(id, textContainer);
    }

    public String getText() {
        return text;
    }

    public HashMap<String, TextContainer> getSubTexts() {
        return subTexts;
    }

    public void copyFrom(TextContainer textContainer) {
        setText(textContainer.getText());
        if (subTexts == null) subTexts = textContainer.getSubTexts();
        else subTexts.putAll(textContainer.getSubTexts());
    }
}
