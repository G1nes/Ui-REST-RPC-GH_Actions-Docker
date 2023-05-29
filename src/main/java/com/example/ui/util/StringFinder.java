package com.example.ui.util;

public class StringFinder {

    public static String getSubstringValueByKey(String source, String key, String separator) {
        if (source.isEmpty()) {
            throw new IllegalArgumentException("Source string is empty");
        }
        int keyIndex = source.indexOf(key);
        if (keyIndex == -1) {
            throw new IllegalArgumentException("Source doesn't contain key: " + key);
        }
        String result = source.substring(keyIndex);
        int endIndex = separator.isEmpty() ? result.length() : result.indexOf(separator);
        if (endIndex == -1) {
            throw new IllegalArgumentException(
                    String.format("Substring %s doesn't contain separator: %s",
                                  result, separator));
        }
        return result.substring(result.indexOf(": ") + 2, endIndex);
    }
}
