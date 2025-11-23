package org.project.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hilfsklasse, um einen Hibernate SessionFactory Singleton bereitszustellen.
 */
public class HibernateUtil {
    private static SessionFactory sessionFactory = null;

    /**
     * Gibt die Singleton-SessionFactory zurück und erstellt sie bei Bedarf.
     *
     * @return die Hibernate SessionFactory
     */
    public static synchronized SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = buildSessionFactory();
        }
        return sessionFactory;
    }

    /**
     * Erstellt eine neue Hibernate SessionFactory basierend auf der Konfigurationsdatei.
     *
     * @return die neu erstellte SessionFactory
     * @throws RuntimeException
     */
    public static SessionFactory buildSessionFactory(){
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            return configuration.configure().buildSessionFactory();
        } catch (Exception e) {
            throw new RuntimeException("Failed to create SessionFactory", e);
        }
    }
}
