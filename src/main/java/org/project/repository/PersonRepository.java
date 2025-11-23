package org.project.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.project.exception.BadRequestException;
import org.project.model.Person;
import org.project.util.HibernateUtil;

import java.util.List;

/**
 * Repository-Klasse für den Zugriff auf Personendaten über Hibernate.
 */
public class PersonRepository {
    /**
     * Liefert alle Personen aus der Datenbank.
     *
     * @return Liste aller Personen
     * @throws BadRequestException
     */
    public List<Person> findAll() {
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("FROM Person", Person.class).getResultList();
        } catch (Exception e){
            throw new BadRequestException("Failed to fetch persons");
        }
    }

    /**
     * Liefert eine Person anhand ihrer ID.
     *
     * @param id die ID der Person
     * @return die gefundene Person oder null
     * @throws BadRequestException
     */
    public Person findById(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.get(Person.class, id);
        } catch (Exception e){
            throw new BadRequestException("Failed to fetch person");
        }
    }

    /**
     * Speichert eine neue Person in der Datenbank.
     *
     * @param person die zu speichernde Person
     * @return die gespeicherte Person
     * @throws RuntimeException
     */
    public Person save(Person person) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(person);
            transaction.commit();
            return person;
        } catch (RuntimeException e){
            if(transaction != null){
                transaction.rollback();
            }
            throw e;
        }
    }

    /**
     * Aktualisiert eine bestehende Person in der Datenbank.
     *
     * @param person die zu aktualisierende Person
     * @return die aktualisierte Person
     * @throws RuntimeException
     */
    public Person update(Person person) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Person updatedPerson = session.merge(person);
            transaction.commit();
            return updatedPerson;
        } catch (RuntimeException e){
            if(transaction != null){
                transaction.rollback();
            }
            throw e;
        }

    }

    /**
     * Löscht eine Person aus der Datenbank.
     *
     * @param person die zu löschende Person
     * @throws RuntimeException
     */
    public void delete(Person person) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.remove(person);
            transaction.commit();
        } catch (RuntimeException e){
            if(transaction != null){
                transaction.rollback();
            }
            throw e;
        }
    }
}
