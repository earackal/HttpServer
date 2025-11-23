package org.project.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Diese Klasse wird verwendet, um Objekte in JSON umzuwandeln
 * und um Werte von JSON Strings zu extrahieren.
 * */
public class JsonConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Wandelt ein Java Objekt ins JSON Repräsentation
     * @param object das zu konvertierende Objekt
     * @return JSON string Repräsentation
     * @throws RuntimeException
     */
    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Extrahiert den Wert von einem spezifischen Key von einem JSON String
     *
     * @param json das JSON String
     * @param key das Key, dessen Wert extrahiert werden soll
     * @return der extrahierte Wert
     */
    public static String getString(String json, String key) {
        try {
            return mapper.readTree(json).get(key).asText();
        } catch (Exception e) {
            return null;
        }
    }
}
