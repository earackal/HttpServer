package org.project.exception;

/**
 * Exception, die ausgelöst wird, wenn eine ungültige Anfrage erfolgt.
 */
public class BadRequestException extends RuntimeException {
    /**
     * Erstellt eine neue BadRequestException mit einer angegebenen Nachricht.
     *
     * @param message die Fehlermeldung
     */
    public BadRequestException(String message) { super(message); }
}
