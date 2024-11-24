package com.ferra13671.SimpleLanguageSystem;

import java.util.HashMap;

/**
 * @author Ferra13671
 * @LastUpdate 1.0
 */

public class Language {

    public final String langName;
    public final HashMap<String, String> keyTexts = new HashMap<>();

    public Language(String langName) {
        this.langName = langName;
    }
}
