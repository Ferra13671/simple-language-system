package com.ferra13671.SimpleLanguageSystem;

import java.util.HashMap;

public class Language {

    public final String langName;
    public final HashMap<String, String> keyTexts = new HashMap<>();

    public Language(String langName) {
        this.langName = langName;
    }
}
