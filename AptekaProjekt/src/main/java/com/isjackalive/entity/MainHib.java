package com.isjackalive.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainHib {
    @FXML
    private ObservableList<Doktor> doktorTable = FXCollections.observableArrayList();

    SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    public ObservableList<Doktor> getDoktorTable()
    {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Doktor> doktorzy = session.createQuery("FROM Doktor").list();
        session.getTransaction().commit();
        session.close();

        doktorTable.setAll(doktorzy);
        return doktorTable;
    }

    private final static MainHib instance = new MainHib();

    public static MainHib getInstance()
    {
        return instance;
    }
}
