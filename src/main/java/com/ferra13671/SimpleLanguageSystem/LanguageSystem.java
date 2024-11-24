package com.ferra13671.SimpleLanguageSystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Ferra13671
 * @LastUpdate 1.1
 */

public final class LanguageSystem {
    private static Supplier<String> currentLanguageGetter;

    private static final ArrayList<Language> languages = new ArrayList<>();
    private static final ArrayList<String> loadedLangs = new ArrayList<>();


    public static String translate(String key) {
        return translate(key, currentLanguageGetter != null ? currentLanguageGetter.get() : !loadedLangs.isEmpty() ? loadedLangs.get(0) : "");
    }

    public static String translate(String key, String lang) {
        String result = getText(key, lang);
        if (result.isEmpty()) {
            result = getText(key, currentLanguageGetter != null ? currentLanguageGetter.get() : !loadedLangs.isEmpty() ? loadedLangs.get(0) : "");
        }
        return result;
    }

    public static void loadTranslations(InputStream inputStream, String lang) {
        try {
            BufferedReader reader = LanguageSystemUtils.createBufferedReader(inputStream, StandardCharsets.UTF_8);
            String line;
            line = reader.readLine();
            while (line != null) {
                if (line.startsWith("//") || line.isEmpty()) {
                    line = reader.readLine();
                    continue;
                }
                CharSequence chars = line;
                TranslatedKeyText keyText = LanguageSystemUtils.getTranslatedKeyText(lang, chars, line);
                addTextToLanguage(keyText);
                line = reader.readLine();
            }
            loadedLangs.add(lang);
            inputStream.close();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setCurrentLanguageGetter(Supplier<String> getter) {
        currentLanguageGetter = getter;
    }

    private static String getText(String key, String lang) {
        for (Language language : languages) {
            if (language.langName.equals(lang)) {
                String text = language.keyTexts.get(key);
                if (text == null) {
                    System.out.printf("[LanguageSystem] Text with key %s in language %s was not found.%n", key, lang);
                    return "";
                }
                return text;
            }
        }
        return "";
    }

    private static void addTextToLanguage(TranslatedKeyText text) {
        boolean needCreateLanguage = true;
        for (Language language : languages) {
            if (language.langName.equals(text.lang)) {
                needCreateLanguage = false;
                break;
            }
        }
        if (needCreateLanguage) {
            Language language = new Language(text.lang);
            language.keyTexts.put(text.key, text.text);
            languages.add(language);
        } else {
            for (Language language : languages) {
                if (!language.keyTexts.containsKey(text.key))
                    language.keyTexts.put(text.key, text.text);
            }
        }
    }

    public static boolean isLanguageLoaded(String lang) {
        return loadedLangs.contains(lang);
    }

    public static List<String> getLoadedLangs() {
        return loadedLangs;
    }

    public static List<Language> getAllLanguages() {
        return languages;
    }
}
