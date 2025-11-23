package org.project.server;

import org.project.controller.PersonController;
import org.project.exception.GlobalExceptionHandler;
import org.project.protocol.HttpRequest;
import org.project.protocol.HttpResponse;

/**
 * Leitet HTTP-Anfragen an den passenden Controller weiter.
 */
public class Router {
    private final PersonController controller;

    /**
     * Konstruktor der Router Klasse
     * */
    public Router(){
        this.controller = new PersonController();
    }

    /**
     * Behandelt eine eingehende HTTP-Anfrage.
     *
     * @param request die HTTP-Anfrage
     * @return die HTTP-Antwort
     */
    public HttpResponse handle(HttpRequest request) {
        try {
            if (request.path.startsWith("/person")) {
                return this.controller.handle(request);
            }
            return HttpResponse.notFound("Route not found");
        } catch (Exception e){
            return GlobalExceptionHandler.handle(e);
        }
    }
}
