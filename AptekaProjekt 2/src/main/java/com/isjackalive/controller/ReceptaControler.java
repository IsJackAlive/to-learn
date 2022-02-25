package com.isjackalive.controller;

import com.isjackalive.entity.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.hibernate.PropertyValueException;
import org.hibernate.Session;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * JavaFX Controller dla zakładki Recepta
 */
public class ReceptaControler implements Initializable {

    //Widok tabeli
    @FXML
    private TableView<Recepta> receptaViewTable;

    @FXML
    private TableColumn receptaViewIdColumn;

    @FXML
    private TableColumn<Recepta, String> receptaViewDoktorColumn;

    @FXML
    private TableColumn<Recepta, String> receptaViewIDDoktorColumn;

    @FXML
    private TableColumn<Recepta, String> receptaViewPacjentColumn;

    @FXML
    private TableColumn<Recepta, String> receptaViewIDPacjentColumn;

    @FXML
    private TableColumn<Recepta, String> receptaViewNazwaColumn;

    //Przyciski obsługujące pola tekstowe
    @FXML
    private Button szukajButton;

    @FXML
    private Button dodajButton;

    @FXML
    private Button usunButton;

    @FXML
    private Button odswiezButton;

    @FXML
    private Button wyczyscButton;

    @FXML
    private Button zapiszButton;

    //Pola tekstowe
    @FXML
    private TextField idFiled;

    @FXML
    private TextField doktorFiled;

    @FXML
    private TextField pacjentFiled;

    @FXML
    private TextField produktFiled;

    /**
     * Okno dialogowe umożliwiające wyświetlanie użytkownikowi monitów, np. o błędzie
     */
    Alert a = new Alert(Alert.AlertType.NONE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

                                                        // po wybraniu karty recepta:

        //tableViewAll();                               // załaduj table view ze wszystkimi rekordami

        ArrayList<Recepta> r = new ArrayList<>();       //  załaduj table view bez rekordów
        tableView(r);

        szukajButton.setOnAction((ActionEvent event) -> {
            Integer id;
            Integer doktor;
            Integer pacjent;
            String name;

            id = getIdFiled();
            doktor = getDoktorFiled();
            pacjent = getPacjentFiled();
            name = getNazwaFiled();

                ArrayList<Recepta> recepty;
                recepty = szukajObj(id, doktor, pacjent, name);
                tableView(recepty);
        });

        dodajButton.setOnAction((ActionEvent event) -> {
            Integer doktor;
            Integer pacjent;
            String name;

            doktor = getDoktorFiled();
            pacjent = getPacjentFiled();
            name = getNazwaFiled();

            try
            {
                dodajObj(doktor, pacjent, name);
            }
            catch (NullPointerException e)
            {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Błędnie uzupełnione pola!");
                a.show();
            }
            tableViewAll();
        });

        usunButton.setOnAction((ActionEvent event) -> {
            Integer id;
            id = (getIdFiled());
            usunObj(id);
            tableViewAll();
            idFiled.setText("");
        });

        odswiezButton.setOnAction((ActionEvent event) -> {
                System.out.println("odswież button");
                tableViewAll();
            });

        wyczyscButton.setOnAction((ActionEvent event) -> {
            idFiled.setText("");
            doktorFiled.setText("");
            pacjentFiled.setText("");
            produktFiled.setText("");
            tableView(r);
        });

        zapiszButton.setOnAction((ActionEvent event) -> {

            Session session = MainHib.getSessionFactory().openSession();
            session.beginTransaction();
            PrintWriter writer = null;
            try {

                List<Recepta> receptaList = session.createQuery("from Recepta", Recepta.class).list();

                writer = new PrintWriter(new BufferedWriter(new FileWriter("/home/damian/Study/ProjektyZal/PO/sqLite/recepta.txt")));
                System.out.println("Suma recept : " + ((java.util.List) receptaList).size());
                for (int i=0; i < receptaList.size(); i++)
                {
                    writer.print("Lek " + receptaList.get(i).getProdukt().getNazwa());
                    writer.print("\tDoktor " + receptaList.get(i).getDoktor().getTelefon());
                    writer.println("\tPacjent " + receptaList.get(i).getPacjent().getTelefon());
                }

                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                writer.close();
                session.close();
            }
        });
    }

    /**
     * Metoda usuwania obiektu, za pomocą ID
     * @param id
     */
    private void usunObj(Integer id) {
        try {
            Session session = MainHib.getSessionFactory().openSession();
            session.beginTransaction();
            Recepta entity = session.find(Recepta.class, id);
            session.remove(entity);
            session.getTransaction().commit();
            session.close();
        } catch (PersistenceException | NullPointerException excpt) {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Nie możesz usunąć tego obiektu!");
            a.show();
        }
    }

    /**
     * Metoda dodajaca obiekt do bazy
     * @param doktor
     * @param pacjent
     * @param name
     */
    private void dodajObj(Integer doktor, Integer pacjent, String name) {
        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        Recepta obj = new Recepta();
        try {
            if (doktor > 0 && pacjent > 0) {
                    Doktor dok = new Doktor();
                    Pacjent pac = new Pacjent();
                    Produkt prod = new Produkt();
                    try {
                        dok = dok.getDoktorById(doktor);
                        pac = pac.getById(pacjent);
                        prod = prod.getByExclName(name);

                        obj.setDoktor(dok);
                        obj.setPacjent(pac);
                        obj.setProdukt(prod);
                        session.save(obj);
                    } catch (PropertyValueException e) //użytkownik nie może stworzyć recepty dla nie istniejącego doktora i pacjenta IndexOutOfBoundsException PropertyValueException
                    {
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Wpisany pacjent lub doktor nie istnieją");
                        a.show();
                    }
            } else {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Błędnie wprowadzone Id Doktor lub Id Pacjent");
                a.show();
            }
            session.getTransaction().commit();
            session.close();
        } catch (IndexOutOfBoundsException ie)
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Wpisany produkt nie istnieje");
            a.show();
        }
    }

    /**
     * Metoda szukająca obiektów, szuka wyłącznie dla wprowadzonych danych
     * @param id
     * @param doktor
     * @param pacjent
     * @param name
     * @return Zwraca listę obiektów spełniające wprowadzone kryteria.
     */
    private ArrayList<Recepta> szukajObj(Integer id, Integer doktor, Integer pacjent, String name) {
        Recepta obj = new Recepta();
        ArrayList<Recepta> arr = new ArrayList<>();

        try {
            if (id != null) arr.add(obj.getById(id));

            if (doktor != null) {
                List<Recepta> pr = obj.getByDoktor(doktor);
                for (int i = 0; i < pr.size(); i++)
                    arr.add(pr.get(i));
            }

            if (pacjent != null) {
                List<Recepta> pr = obj.getByPacjent(pacjent);
                for (int i = 0; i < pr.size(); i++)
                    arr.add(pr.get(i));
            }

            if (!name.equals("")) {
                List<Recepta> n = obj.getByName(name);
                for (int i = 0; i < n.size(); i++) {
                    System.out.println(i + " " + n.get(i));
                    arr.add(n.get(i));
                }
            }
        }
        catch (NullPointerException np)
        {
            a.setAlertType(Alert.AlertType.ERROR);
            a.setContentText("Nie wprowadzono danych");
            a.show();
        }
        return arr;
    }

    private String getNazwaFiled() {
        String obj = produktFiled.getText();
        return obj;
    }

    private Integer getPacjentFiled() {
        Integer id = null;
        try {
            id = Integer.parseInt(pacjentFiled.getText());
            if(id <= 0) {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("ID musi być większe od 0.");
                a.show();
                id = null;
            }
        } catch (RuntimeException e){}
        finally {
            return id;}
    }

    private Integer getDoktorFiled() {
        Integer id = null;
        try {
            id = Integer.parseInt(doktorFiled.getText());
            if(id <= 0) {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("ID musi być większe od 0.");
                a.show();
                id = null;
            }
        } catch (RuntimeException e){}
        finally {
            return id;}
    }

    private Integer getIdFiled() {
        Integer id = null;
        try {
            id = Integer.parseInt(idFiled.getText());
            if(id <= 0) {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("ID musi być większe od 0.");
                a.show();
                id = null;
            }
        } catch (RuntimeException e){}
        finally {
            return id;}
    }

    /**
     * Lista przechowująca wyszukane obiekty
     */
    private ObservableList<Recepta> recObsTable1 = FXCollections.observableArrayList();

    /**
     * Widok w tabeli dla wyszukanych obiektów
     * @param obj
     */
    public void tableView(ArrayList obj)
    {
        receptaViewIdColumn.setCellValueFactory(new PropertyValueFactory<Recepta, Integer>("id"));
        receptaViewIDDoktorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoktor().getId().toString()));
        receptaViewDoktorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoktor().getTelefon()));
        receptaViewIDPacjentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPacjent().getId().toString()));
        receptaViewPacjentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPacjent().getTelefon()));
        receptaViewNazwaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdukt().getNazwa()));

        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Recepta> rec = obj;
        session.getTransaction().commit();
        session.close();
        recObsTable1.setAll(rec);
        //
        receptaViewTable.setEditable(true);
        receptaViewTable.setItems(recObsTable1);
        receptaViewTable.refresh();
    }

    /**
     * Widok w tabeli dla wszystkich obiektów
     */
    public void tableViewAll()
    {
        try {
            receptaViewIdColumn.setCellValueFactory(new PropertyValueFactory<Recepta, Integer>("id"));
            receptaViewIDDoktorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoktor().getId().toString()));
            receptaViewDoktorColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDoktor().getTelefon()));
            receptaViewIDPacjentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPacjent().getId().toString()));
            receptaViewPacjentColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPacjent().getTelefon()));
            receptaViewNazwaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdukt().getNazwa()));
            receptaViewTable.setItems(Model.getModel().getReceptaObsTable());
            receptaViewTable.setEditable(false);
            receptaViewTable.refresh();
        } catch (EntityNotFoundException ex)
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Błąd przy ładowaniu widoku");
            a.show();
        }

    }

}
