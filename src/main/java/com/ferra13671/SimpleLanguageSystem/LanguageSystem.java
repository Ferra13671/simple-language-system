package com.ferra13671.SimpleLanguageSystem;

import java.io.InputStream;
import java.util.HashMap;
import java.util.function.Supplier;

public class LanguageSystem {
    private static final HashMap<String, Language> languages = new HashMap<>();
    private static Supplier<String> languageGetter = null;

    public static void setLanguageGetter(Supplier<String> languageGetter) {
        LanguageSystem.languageGetter = languageGetter;
    }

    public static String translate(String id) {
        if (languageGetter == null)
            throw new UnsupportedOperationException("Language getter has not initialized!");
        return getOrCreateLanguage(languageGetter.get()).get(id);
    }

    public static String translate(String id, String... args) {
        return String.format(translate(id), args);
    }

    public static void addTranslations(String languageName, HashMap<InputStream, String> languageMap) {
        Language language = getOrCreateLanguage(languageName);
        language.addTranslations(languageMap);
    }

    public static Language getOrCreateLanguage(String languageName) {
        Language language = languages.get(languageName);
        if (language == null) {
            language = new Language();
            languages.put(languageName, language);
        }
        return language;
    }
}
