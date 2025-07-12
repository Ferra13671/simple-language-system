package com.ferra13671.SimpleLanguageSystem;

import com.google.gson.JsonElement;

import java.io.InputStream;
import java.util.HashMap;
import java.util.function.Supplier;

public class LanguageSystem {
    private static final HashMap<String, Language> languages = new HashMap<>();
    private static Supplier<String> languageGetter = null;
    private static String defaultLanguage = "";

    public static void setLanguageGetter(Supplier<String> languageGetter) {
        LanguageSystem.languageGetter = languageGetter;
    }

    public static void setDefaultLanguage(String defaultLanguage) {
        LanguageSystem.defaultLanguage = defaultLanguage;
    }

    public static String translate(String id) {
        if (languageGetter == null)
            throw new UnsupportedOperationException("Language getter has not initialized!");
        return getLanguageOrDefault(languageGetter.get(), defaultLanguage).get(id);
    }

    public static String translate(String id, String... args) {
        return String.format(translate(id), args);
    }

    public static void addTranslations(String languageName, HashMap<JsonElement, String> languageMap) {
        Language language = getOrCreateLanguage(languageName);
        language.addTranslations(languageMap);
    }

    public static Language getLanguage(String languageName) {
        return languages.get(languageName);
    }

    public static Language getLanguageOrDefault(String languageName, String defaultLanguageName) {
        Language language = getLanguage(languageName);
        return language == null ? getOrCreateLanguage(defaultLanguageName) : language;
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
