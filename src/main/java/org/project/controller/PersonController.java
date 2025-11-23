package org.project.controller;

import org.project.model.Person;
import org.project.protocol.HttpRequest;
import org.project.protocol.HttpResponse;
import org.project.service.PersonService;
import org.project.util.JsonConverter;

import java.util.List;
import java.util.logging.Logger;

/**
 * Controller um HTTPRequest anzunehmen
 */
public class PersonController {
    private final PersonService service;

    public PersonController(){
        this.service = new PersonService();
    }

    private static final Logger logger = Logger.getLogger(PersonController.class.getName());

    /**
     * Behandelt eine eingehende HttpRequest und liefert eine passende HttpResponse.
     *
     * @param request die eingehende HTTP-Anfrage
     * @return die entsprechende HTTP-Antwort
     */
    public HttpResponse handle(HttpRequest request) {

        if (request.method.equals("GET") && request.path.equals("/persons")) {
            logger.info("Received request to retrieve all persons");
            List<Person> people = this.service.getAllPersons();
            return HttpResponse.ok(JsonConverter.toJson(people));
        }

        if (request.method.equals("GET") && request.path.startsWith("/person/")) {
            logger.info("Received request to retrieve person by id");
            int id = Integer.parseInt(request.path.substring("/person/".length()));
            Person person = this.service.getPersonById(id);
            return HttpResponse.ok(JsonConverter.toJson(person));
        }

        if (request.method.equals("POST") && request.path.equals("/person")) {
            logger.info("Received request to post new person");
            String firstname = JsonConverter.getString(request.body, "firstname");
            String lastname = JsonConverter.getString(request.body, "surname");
            Person newPerson = this.service.createPerson(firstname, lastname);
            return HttpResponse.created(JsonConverter.toJson(newPerson));
        }

        if (request.method.equals("PUT") && request.path.startsWith("/person/")) {
            logger.info("Received request to update person");
            int id = Integer.parseInt(request.path.substring("/person/".length()));
            String firstname = JsonConverter.getString(request.body, "firstname");
            String surname = JsonConverter.getString(request.body, "surname");
            Person updatedPerson = this.service.updatePerson(id, firstname, surname);
            return HttpResponse.ok(JsonConverter.toJson(updatedPerson));
        }

        if (request.method.equals("DELETE") && request.path.startsWith("/person/")) {
            logger.info("Received request to delete person");
            int id = Integer.parseInt(request.path.substring("/person/".length()));
            this.service.deletePerson(id);
            return HttpResponse.ok("{\"status\":\"deleted\"}");
        }

        return HttpResponse.notFound("Unknown route");
    }
}
