package org.project.exception;

import org.project.protocol.HttpResponse;
import java.util.logging.Logger;

/**
 * Behandelt globale Exceptions und erstellt passende HTTP-Antworten.
 */
public class GlobalExceptionHandler {

    private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class.getName());

    /**
     * Wandelt eine Exception in eine entsprechende HttpResponse um.
     *
     * @param e die zu behandelnde Exception
     * @return HttpResponse mit passendem Statuscode und Fehlermeldung
     */
    public static HttpResponse handle(Exception e) {

        if (e instanceof PersonNotFoundException) {
            logger.warning("Got PersonNotFoundException");
            return HttpResponse.notFound(json(e.getMessage()));
        }

        if (e instanceof BadRequestException) {
            logger.warning("Got BadRequestException");
            return HttpResponse.badRequest(json(e.getMessage()));
        }

        logger.warning("Got InternalServerError");
        return HttpResponse.internalServerError(json("Unexpected error: " + e.getMessage()));
    }

    /**
     * Hilfsmethode, um eine Fehlermeldung in JSON-Format zu bringen.
     *
     * @param msg die Fehlermeldung
     * @return JSON-String mit der Fehlermeldung
     */
    private static String json(String msg) {
        return "{ \"error\": \"" + msg + "\" }";
    }
}
