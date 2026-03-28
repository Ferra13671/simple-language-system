package com.ferra13671.SimpleLanguageSystem;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@AllArgsConstructor
public class TextContainer {
    @Setter
    @Getter
    private String text;
    @Getter
    private HashMap<String, TextContainer> subTexts;

    public void addSubText(String id, TextContainer textContainer) {
        if (this.subTexts == null)
            this.subTexts = new HashMap<>();

        this.subTexts.put(id, textContainer);
    }

    public void copyFrom(TextContainer textContainer) {
        setText(textContainer.getText());
        if (this.subTexts == null)
            this.subTexts = textContainer.getSubTexts();
        else if (textContainer.getSubTexts() != null)
            this.subTexts.putAll(textContainer.getSubTexts());
    }
}
