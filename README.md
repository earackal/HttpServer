# Person Management HTTP Server

Dies ist ein einfacher Java-basierter HTTP-Server zur Verwaltung von Personen.  
Dieses Projekt bietet REST-ähnliche Endpunkte für CRUD-Operationen 
(Create, Read, Update, Delete) und nutzt Hibernate und eine PostgreSQL-Datenbank,
um Personendaten zu speichern und zu manipulieren.

---

## Features

- HTTP Server auf konfigurierbarem Port (Standard: 9090)
- CRUD-Operationen für Personen
- JSON-basiertes Request- und Response-Format
- Exception Handling mit aussagekräftigen HTTP-Statuscodes
- Logging für Server- und Request-Verarbeitung
- Hibernate für ORM-Persistenz in PostgreSQL

---

## Technologien

- Java 21+
- Hibernate ORM
- PostgreSQL
- Jackson (für JSON-Konvertierung)
- Java Logging (java.util.logging)
- Maven 4.0 
- Lombok (`@Data`, `@NoArgsConstructor`) für Modellklassen

---

## Endpunkte

| Methode | Pfad           | Beschreibung                    |
|---------|----------------|---------------------------------|
| GET     | `/persons`     | Alle Personen abrufen           |
| GET     | `/person/{id}` | Person nach ID abrufen          |
| POST    | `/person`      | Neue Person erstellen           |
| PUT     | `/person/{id}` | Bestehende Person aktualisieren |
| DELETE  | `/person/{id}` | Person löschen                  |

## Starten vom Server

Um diesen Server zu starten, passe zunächst das Hibernate-Konfigurationsfile an. 
Anschliessend, kann die main Methode der Klasse Main.java ausgeführt werden.
