package org.project.service;

import org.project.exception.BadRequestException;
import org.project.exception.PersonNotFoundException;
import org.project.model.Person;
import org.project.repository.PersonRepository;

import java.util.List;

/**
 * Service-Klasse zur Verwaltung von Personen.
 * Bietet Methoden zum Abrufen, Erstellen, Aktualisieren und
 * Löschen von Personen.
 */
public class PersonService {

    private final PersonRepository repository;

    /** Erstellt eine neue Instanz des PersonService. */
    public PersonService(){
        this.repository = new PersonRepository();
    }

    /**
     * Liefert eine Liste aller Personen zurück.
     *
     * @return Liste aller Personen
     */
    public List<Person> getAllPersons() {
        return this.repository.findAll();
    }

    /**
     * Liefert eine Person anhand ihrer ID zurück.
     *
     * @param id die ID der gesuchten Person
     * @return die gefundene Person
     * @throws PersonNotFoundException
     */
    public Person getPersonById(int id) {
        Person foundPerson = this.repository.findById(id);
        if(foundPerson == null){
            throw new PersonNotFoundException(id);
        }
        return foundPerson;
    }

    /**
     * Fügt neue Person in die Datenbank hinzu.
     *
     * @param firstname der Vorname der Person
     * @param surname der Nachname der Person
     * @return die erstellte Person
     * @throws BadRequestException
     */
    public Person createPerson(String firstname, String surname) {
        if (firstname == null || firstname.isBlank()){
            throw new BadRequestException("firstname is required");
        }
        if (surname == null || surname.isBlank()) {
            throw new BadRequestException("surname is required");
        }

        Person newPerson = new Person();
        newPerson.setFirstname(firstname);
        newPerson.setSurname(surname);
        return this.repository.save(newPerson);
    }

    /**
     * Aktualisiert eine bestehende Person.
     *
     * @param id die ID der zu aktualisierenden Person
     * @param firstname der neue Vorname
     * @param surname der neue Nachname
     * @return die aktualisierte Person
     * @throws PersonNotFoundException
     */
    public Person updatePerson(int id, String firstname, String surname) {
        Person person = this.repository.findById(id);
        if (person == null){
            throw new PersonNotFoundException(id);
        }
        person.setFirstname(firstname);
        person.setSurname(surname);

        return this.repository.update(person);
    }

    /**
     * Löscht eine Person anhand ihrer ID.
     *
     * @param id die ID der zu löschenden Person
     * @throws PersonNotFoundException
     */
    public void deletePerson(int id) {
        Person person = this.repository.findById(id);
        if (person == null) {
            throw new PersonNotFoundException(id);
        }
        this.repository.delete(person);
    }
}
