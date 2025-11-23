package org.project.protocol;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Repräsentiert eine HTTP-Anfrage mit Methode, Pfad und Body.
 * ACHTUNG: Diese Klasse wurden anhand CHAT-GPT erstellt.
 */
public class HttpRequest {
    public String method;
    public String path;
    public String body;

    /**
     * Liest und parst eine HTTP-Anfrage aus einem BufferedReader.
     *
     * @param in der BufferedReader, von dem die Anfrage gelesen wird
     * @return die geparste HttpRequest oder null, wenn keine Anfrage vorhanden
     * @throws IOException
     */
    public static HttpRequest parse(BufferedReader in) throws IOException {
        HttpRequest request = new HttpRequest();

        String line = in.readLine();
        if (line == null) return null;
        String[] parts = line.split(" ");
        request.method = parts[0];
        request.path = parts[1];

        int contentLength = 0;
        while (!(line = in.readLine()).isEmpty()) {
            if (line.startsWith("Content-Length:")) {
                contentLength = Integer.parseInt(line.split(":")[1].trim());
            }
        }

        if (contentLength > 0) {
            char[] bodyChars = new char[contentLength];
            in.read(bodyChars);
            request.body = new String(bodyChars);
        } else {
            request.body = "";
        }

        return request;
    }
}
