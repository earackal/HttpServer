package org.project.exception;

/**
 * Exception, die ausgelöst wird, wenn eine Person mit einer bestimmten ID nicht gefunden wird.
 */
public class PersonNotFoundException extends RuntimeException {
    /**
     * Erstellt eine neue Exception mit einer Nachricht, dass die Person nicht gefunden wurde.
     *
     * @param id die ID der nicht gefundenen Person
     */
    public PersonNotFoundException(int id) {
        super("Person with ID " + id + " not found");
    }
}