package com.ferra13671.SimpleLanguageSystem;

import com.google.gson.JsonObject;
import lombok.Setter;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.function.Supplier;

@UtilityClass
public class LanguageSystem {
    private final HashMap<String, Language> languages = new HashMap<>();
    @Setter
    private Supplier<String> languageGetter = null;
    @Setter
    private String defaultLanguage = "";

    public String translate(String id) {
        if (languageGetter == null)
            throw new UnsupportedOperationException("Language getter has not initialized!");
        return getLanguageOrDefault(languageGetter.get(), defaultLanguage).get(id);
    }

    public String translate(String id, String... args) {
        return String.format(translate(id), args);
    }

    public void addTranslations(String languageName, HashMap<JsonObject, String> languageMap) {
        Language language = getOrCreateLanguage(languageName);
        language.addTranslations(languageMap);
    }

    public Language getLanguage(String languageName) {
        return languages.get(languageName);
    }

    public Language getLanguageOrDefault(String languageName, String defaultLanguageName) {
        Language language = getLanguage(languageName);
        return language == null ? getOrCreateLanguage(defaultLanguageName) : language;
    }

    public Language getOrCreateLanguage(String languageName) {
        Language language = languages.get(languageName);
        if (language == null) {
            language = new Language();
            languages.put(languageName, language);
        }
        return language;
    }
}
