package com.isjackalive.entity;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import org.hibernate.Session;

import javax.persistence.PersistenceException;
import java.util.List;

public class Model {

    private final static Model model = new Model();

    /**
     * Okno dialogowe umożliwiające wyświetlanie użytkownikowi monitów, np. o błędzie
     */
    Alert a = new Alert(Alert.AlertType.NONE);

    private ObservableList<Doktor> doktorObsTable = FXCollections.observableArrayList();
    private ObservableList<Pacjent> pacjentObsTable = FXCollections.observableArrayList();
    private ObservableList<Produkt> produktObsTable = FXCollections.observableArrayList();
    private ObservableList<Recepta> ReceptaObsTable = FXCollections.observableArrayList();

    public static Model getModel()
    {
        return model;
    }

    /**
     * @return Encje Doktor z Bazy Danych
     */
    public ObservableList<Doktor> getDoktorObsTable() {

        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Doktor> doktorzy = session.createQuery("FROM Doktor ").list();
        session.getTransaction().commit();
        session.close();
        doktorObsTable.setAll(doktorzy);

        return doktorObsTable;
    }

    /**
     * @return Encje Pacjent z Bazy Danych
     */
    public ObservableList<Pacjent> getPacjentObsTable() {

        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Pacjent> pacjenci = session.createQuery("FROM Pacjent ").list();
        session.getTransaction().commit();
        session.close();
        pacjentObsTable.setAll(pacjenci);

        return pacjentObsTable;
    }

    /**
     * @return Encje Produtkt z Bazy Danych
     */
    public ObservableList<Produkt> getProduktObsTable() {

        Session sesion = MainHib.getSessionFactory().openSession();
        sesion.beginTransaction();
        List<Produkt> produkty = sesion.createQuery("FROM Produkt ").list();
        sesion.getTransaction().commit();
        sesion.close();
        produktObsTable.setAll(produkty);

        return produktObsTable;
    }

    /**
     * @return Encje Recepta z Bazy Danych
     */
    public ObservableList<Recepta> getReceptaObsTable() {

        Session sesion = MainHib.getSessionFactory().openSession();
        sesion.beginTransaction();
        List<Recepta> recepty = sesion.createQuery("FROM Recepta ").list();
        sesion.getTransaction().commit();
        sesion.close();
        ReceptaObsTable.setAll(recepty);

        return ReceptaObsTable;
    }

    /**
     * @return usuń obiekt przez wprowadzone ID
     */
    public <T> void deleteById(Class<T> entityClass, Integer id) {

        try {
            Session session = MainHib.getSessionFactory().openSession();
            session.beginTransaction();
            T entity = session.find(entityClass, id);
            session.remove(entity);
            session.getTransaction().commit();
            session.close();
        } catch (PersistenceException | NullPointerException excpt) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Nie możesz usunąć tego obiektu,\nnajpierw usuń powiązane z nim recepty!");
            a.show();
        }
    }

    /**
     * @return Encje Pacjent z Bazy Danych
     */
    public void testData()
    {
        Session session = MainHib.getSessionFactory().openSession();
        Doktor d1 = new Doktor("Jan", "Kowalski", "492-134-1234");
        Doktor d2 = new Doktor("Błażej", "Wybicki", "732-332-3421");
        Pacjent p1 = new Pacjent("Andrzej", "Nowak", "422-134-4451");
        Pacjent p2 = new Pacjent("Miłosz", "Kukułka", "754-323-1234");
        Produkt pr1 = new Produkt(7, java.sql.Date.valueOf("2021-12-11"), "Antybiotyk");
        Produkt pr2 = new Produkt(12, java.sql.Date.valueOf("2022-01-17"), "Syrop na kaszel");
        Produkt pr3 = new Produkt(16, java.sql.Date.valueOf("2023-05-21"), "Maść");
        Recepta r1 = new Recepta(d1, p1, pr1);
        Recepta r2 = new Recepta(d1, p1, pr2);
        Recepta r3 = new Recepta(d1, p2, pr3);
        session.beginTransaction();
        session.save(d1);
        session.save(d2);
        session.save(p1);
        session.save(p2);
        session.save(pr1);
        session.save(pr2);
        session.save(pr3);
        session.save(r1);
        session.save(r2);
        session.save(r3);
        session.getTransaction().commit();
        session.close();
    }
}
