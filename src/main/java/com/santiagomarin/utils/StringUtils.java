package com.santiagomarin.utils;

import java.text.Normalizer;

public class StringUtils {

    private StringUtils() {}

    public static String normalize(String text) {
        if (text == null) return null;
        return Normalizer
                .normalize(text.trim().toLowerCase(), Normalizer.Form.NFD)
                .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    }
}
