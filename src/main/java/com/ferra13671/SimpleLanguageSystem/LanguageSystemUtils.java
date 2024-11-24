package com.ferra13671.SimpleLanguageSystem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

/**
 * @author Ferra13671
 * @LastUpdate 1.0
 */

public final class LanguageSystemUtils {

    public static BufferedReader createBufferedReader(InputStream stream, Charset charset) {
        CharsetDecoder decoder = charset.newDecoder();
        Reader reader = new InputStreamReader(stream, decoder);
        return new BufferedReader(reader);
    }

    public static TranslatedKeyText getTranslatedKeyText(String lang, CharSequence chars, String line) {
        StringBuilder key = new StringBuilder();
        String text;
        boolean check = false;
        for (int i = 0; i < chars.length() - 1; i++) {
            char symbol = chars.charAt(i);
            if (symbol == '"') {
                check = !check;
                continue;
            }

            if (check)
                key.append(symbol);
        }
        text = line.replace('"' + key.toString() + '"' + ": ", "");
        return new TranslatedKeyText(key.toString(), text, lang);
    }
}
