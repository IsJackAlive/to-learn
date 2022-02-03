package com.isjackalive.entity;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;

import java.util.List;

public class Model {

    private final static Model model = new Model();

    private ObservableList<Doktor> doktorObsTable = FXCollections.observableArrayList();

    private ObservableList<Pacjent> pacjentObsTable = FXCollections.observableArrayList();

    private ObservableList<Produkt> produktObsTable = FXCollections.observableArrayList();

    private ObservableList<Recepta> ReceptaObsTable = FXCollections.observableArrayList();

    public static Model getModel()
    {
        return model;
    }

    public ObservableList<Doktor> getDoktorObsTable() {

        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Doktor> doktorzy = session.createQuery("FROM Doktor ").list();
        session.getTransaction().commit();
        session.close();
        doktorObsTable.setAll(doktorzy);

        return doktorObsTable;
    }

    public void setDoktorObsTable(ObservableList<Doktor> doktorObsTable) {
        this.doktorObsTable = doktorObsTable;
    }

    public ObservableList<Pacjent> getPacjentObsTable() {

        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Pacjent> pacjenci = session.createQuery("FROM Pacjent ").list();
        session.getTransaction().commit();
        session.close();
        pacjentObsTable.setAll(pacjenci);

        return pacjentObsTable;
    }

    public void setPacjentObsTable(ObservableList<Pacjent> pacjentObsTable) {
        this.pacjentObsTable = pacjentObsTable;
    }

    public ObservableList<Produkt> getProduktObsTable() {

        Session sesion = MainHib.getSessionFactory().openSession();
        sesion.beginTransaction();
        List<Produkt> produkty = sesion.createQuery("FROM Produkt ").list();
        sesion.getTransaction().commit();
        sesion.close();
        produktObsTable.setAll(produkty);

        return produktObsTable;
    }

    public void setProduktObsTable(ObservableList<Produkt> produktObsTable) {
        this.produktObsTable = produktObsTable;
    }

    public ObservableList<Recepta> getReceptaObsTable() {

        Session sesion = MainHib.getSessionFactory().openSession();
        sesion.beginTransaction();
        List<Recepta> recepty = sesion.createQuery("FROM Recepta ").list();
        sesion.getTransaction().commit();
        sesion.close();
        ReceptaObsTable.setAll(recepty);

        return ReceptaObsTable;
    }

    public void setReceptaObsTable(ObservableList<Recepta> receptaObsTable) {
        ReceptaObsTable = receptaObsTable;
    }
}
