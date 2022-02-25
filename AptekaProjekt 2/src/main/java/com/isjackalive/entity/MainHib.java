package com.isjackalive.entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Klasa zarządządzająca dostępem do Hiberante
 * @author damian
 */

public class MainHib {

    // Dostarczenie obiektów Session, wczytuje konfiguracje o mapowanych obiektach
    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}