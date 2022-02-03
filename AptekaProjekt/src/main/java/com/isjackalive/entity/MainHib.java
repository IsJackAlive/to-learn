package com.isjackalive.entity;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class MainHib {

    private static SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory(){
        if (sessionFactory == null) {
            try{
                Class.forName("org.sqlite.JDBC");
            }catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }

            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }

}