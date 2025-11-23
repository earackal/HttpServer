package org.project.protocol;

/**
 * Repräsentiert eine HTTP-Antwort mit Status, Body und Content-Type.
 */
public class HttpResponse {

    private int status;
    private String body;
    private String contentType = "application/json";

    /**
     * Erstellt eine neue HttpResponse mit Status und Body.
     *
     * @param status der HTTP-Statuscode
     * @param body der Antworttext
     */
    public HttpResponse(int status, String body) {
        this.status = status;
        this.body = body;
    }

    /**
     * Erstellt eine Antwort mit Status 200 OK.
     *
     * @param body der Antworttext
     * @return HttpResponse mit Status 200
     */
    public static HttpResponse ok(String body) {
        return new HttpResponse(200, body);
    }

    /**
     * Erstellt eine Antwort mit Status 201 Created.
     *
     * @param body der Antworttext
     * @return HttpResponse mit Status 201
     */
    public static HttpResponse created(String body) {
        return new HttpResponse(201, body);
    }

    /**
     * Erstellt eine Antwort mit Status 404 Not Found.
     *
     * @param body der Antworttext
     * @return HttpResponse mit Status 404
     */
    public static HttpResponse notFound(String body) {
        return new HttpResponse(404, body);
    }

    /**
     * Erstellt eine Antwort mit Status 400 Bad Request.
     *
     * @param body der Antworttext
     * @return HttpResponse mit Status 400
     */
    public static HttpResponse badRequest(String body){ return new HttpResponse(400, body); }

    /**
     * Erstellt eine Antwort mit Status 500 Internal Server Error.
     *
     * @param body der Antworttext
     * @return HttpResponse mit Status 500
     */
    public static HttpResponse internalServerError(String body){return new HttpResponse(500, body); }

    /**
     * Wandelt die HttpResponse in ein Byte-Array für die Übertragung über einen Socket.
     * ACHTUNG: Diese Methode wurde anhand CHAT-GPT erstellt.
     * @return die HttpResponse als Byte-Array
     */
    public byte[] toBytes() {
        String response =
                "HTTP/1.1 " + status + " OK\r\n" +
                        "Content-Type: " + contentType + "\r\n" +
                        "Content-Length: " + body.length() + "\r\n" +
                        "\r\n" +
                        body;

        return response.getBytes();
    }
}