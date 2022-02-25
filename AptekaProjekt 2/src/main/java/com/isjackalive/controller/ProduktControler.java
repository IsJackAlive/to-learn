package com.isjackalive.controller;

import com.isjackalive.entity.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;
import org.hibernate.Session;

import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import java.io.Serializable;
import java.net.URL;
import java.security.ProtectionDomain;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JavaFX Controller dla zakładki Produkt
 */
public class ProduktControler implements Initializable {

    /**
     * Okno dialogowe umożliwiające wyświetlanie użytkownikowi monitów, np. o błędzie
     */
    Alert a = new Alert(Alert.AlertType.NONE);

    //Widok tabeli
    @FXML
    private TableView<Produkt> produktViewTable;

    @FXML
    private TableColumn produktViewIdColumn;

    @FXML
    private TableColumn produktViewCenaColumn;

    @FXML
    private TableColumn produktViewDataColumn;

    @FXML
    private TableColumn produktViewNazwaColumn;

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

    //Pola tekstowe
    @FXML
    private TextField idFiled;

    @FXML
    private TextField cenaFiled;

    @FXML
    private TextField dataFiled;

    @FXML
    private TextField nazwaFiled;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources){

        //tableViewAll();

        ArrayList<Recepta> r = new ArrayList<>();
        tableView(r);

        szukajButton.setOnAction((ActionEvent event) -> {
            Integer id;
            Integer price;
            Date date;
            String name;

            id = getIdFiled();
            price = getCenaFiled();
            try {
                date = getDataFiled();

            name = getNazwaFiled();
                ArrayList<Produkt> pac;
                pac = szukajObj(id, price, date, name);
                tableView(pac);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });

        dodajButton.setOnAction((ActionEvent event) -> {
            Integer price;
            Date date;
            String name;

            price = getCenaFiled();
            try {
                date = getDataFiled();
                name = getNazwaFiled();

                try
                {
                    dodajObj(price, date, name);
                }
                catch (NullPointerException e)
                {
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Nie można dodać.");
                    a.show();
                }
                tableViewAll();
            } catch (ParseException e) {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Aby poprawnie dodać uzupełnij pola: \nCena, data(RRRR-MM-DD), Nazwa");
                a.show();
            }
        });

        usunButton.setOnAction((ActionEvent event) -> {
            Integer id;
            id = (getIdFiled());
            usunObj(id);
            tableViewAll();
            idFiled.setText("");
        });

        odswiezButton.setOnAction((ActionEvent event) -> {
            tableViewAll();
        });

        wyczyscButton.setOnAction((ActionEvent event) -> {
            idFiled.setText("");
            cenaFiled.setText("");
            dataFiled.setText("");
            nazwaFiled.setText("");
            tableView(r);
        });
    }

    /**
     * Metoda dodajaca obiekt do bazy
     * @param price
     * @param date
     * @param name
     */
    private void dodajObj(Integer price, Date date, String name) {

        Produkt obj = new Produkt();
        if(price > 0) {
            if (!name.equals("") && date != null) {
                obj.setCena(price);
                obj.setData(date);
                obj.setNazwa(name);

                if (!isExist(obj)) {
                    Session session = MainHib.getSessionFactory().openSession();
                    session.beginTransaction();
                    session.save(obj);
                    session.getTransaction().commit();
                    session.close();
                } else {
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("Ten produkt już istnieje");
                    a.show();
                }
            } else {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Aby poprawnie dodać uzupełnij pola: \nCena, data(RRRR-MM-DD), Nazwa");
                a.show();
            }
        } else {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Błędnie wprowadzona cena");
            a.show();
        }
    }

    /**
     * Metoda szukająca obiektów, szuka wyłącznie dla wprowadzonych danych
     * @param id
     * @param price
     * @param date
     * @param name
     * @return
     */
    private ArrayList<Produkt> szukajObj(Integer id, Integer price, Date date, String name) {
        Produkt obj = new Produkt();
        ArrayList<Produkt> arr = new ArrayList<>();

        if(id != null)  arr.add(obj.getById(id));

        if(price != null) {
            List<Produkt> pr = obj.getByPrice(price);
            for(int i=0; i<pr.size(); i++)
                arr.add (pr.get(i));
        }

        if(date != null) {
            List<Produkt> dt = obj.getByDate(date);
            for(int i=0; i<dt.size(); i++)
                arr.add (dt.get(i));
        }

        if(!name.equals("") && name != null) {
            List<Produkt> n = obj.getByName(name);
            for(int i=0; i<n.size(); i++)
                arr.add (n.get(i));
        }

        return arr;
    }

    private Integer getCenaFiled() {
        Integer cena = null;
        try {
            cena = Integer.parseInt(cenaFiled.getText());
            if(cena <= 0) {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Cena musi być większa od 0.");
                a.show();
                cena  = null;
            }
        } catch (RuntimeException e){}
        finally {
            return cena ;}
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

    public Date getDataFiled() throws ParseException {
        String obj = dataFiled.getText();

        if(validateData(obj) == true)
        {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(obj);
            return date;
        }
        else{
//      UMIEŚĆ TEN KOMUNIKAT GDZIEŚ INDZIEJ!
//            a.setAlertType(Alert.AlertType.INFORMATION);
//            a.setContentText("Błędnie wprowadzona data\nyyyy-mm-dd");
//            a.show();
            return null;
        }
    }

    /**
     * Metoda sprawdzająca poprawność wprowadzonej daty
     * @param obj
     * @return true or false
     */
    public boolean validateData(String obj) {
        Pattern pattern = Pattern.compile("^\\d{4}[-]?\\d{2}[-]?\\d{2}$"); //rok miesiac data
        Matcher matcher = pattern.matcher(obj);
        return matcher.matches();
    }

    public String getNazwaFiled() {
        String obj = nazwaFiled.getText();
        return obj;
    }

    /**
     * Metoda usuwania obiektu, za pomocą ID
     * @param idS
     */
    public void usunObj(Integer idS)
    {
        if (idS != null) {
            Model obj = new Model();
            obj.deleteById(Produkt.class, idS);
        }else
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Aby poprawnie usunąć uzupełnij pole ID");
            a.show();
        }
    }

    /**
     * Metoda sprawdza czy wprowadzona nazwa produktu już nie istnieje
     * @param obj
     * @return
     */
    public boolean isExist (Produkt obj)
    {
        Produkt prod = new Produkt();
        String nazwaObj = obj.getNazwa();
        try
        {
            prod.getByExclName(nazwaObj);
            return true;
        } catch (IndexOutOfBoundsException | OptimisticLockException is)
        {
            return false;
        }
    }


    /**
     * Lista przechowująca wyszukane obiekty
     */
    private ObservableList<Produkt> produktObsTable1 = FXCollections.observableArrayList();

    /**
     * Widok w tabeli dla wyszukanych obiektów
     * @param obj
     */
    public void tableView(ArrayList obj)
    {
        produktViewIdColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Integer>("id"));
        produktViewCenaColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Integer>("cena"));
            produktViewCenaColumn.setEditable(true);
            produktViewCenaColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        produktViewDataColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Date>("data"));        //java.sql.Date>
        produktViewNazwaColumn.setCellValueFactory(new PropertyValueFactory<Produkt, String>("nazwa"));
            produktViewNazwaColumn.setEditable(true);
            produktViewNazwaColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Produkt> produkts = obj;
        session.getTransaction().commit();
        session.close();
        produktObsTable1.setAll(produkts);
        //
        produktViewTable.setEditable(true);
        produktViewTable.setItems(produktObsTable1);
        produktViewTable.refresh();
    }

    /**
     * Widok w tabeli dla wszystkich obiektów
     */
    public void tableViewAll()
    {
        produktViewIdColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Integer>("id"));
        produktViewCenaColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Integer>("cena"));
        produktViewDataColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Date>("data"));
        produktViewNazwaColumn.setCellValueFactory(new PropertyValueFactory<Produkt, String>("nazwa"));
        produktViewTable.setItems(Model.getModel().getProduktObsTable());
        produktViewTable.setEditable(false);
    }

    /**
     * Metoda zapisująca edytowane pola
     * @param obj
     */
    public void saveEdit(Produkt obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }

    /**
     * Metoda sprawdzająca poprawność edytowanej nazwy
     * @param obStringCellEditEvent
     */
    public void onEditCommitName(TableColumn.CellEditEvent<Produkt, String> obStringCellEditEvent) {
        String name = obStringCellEditEvent.getNewValue();
        System.out.println("Editname: " + name);
        try {
            if (name != null && !name.equals("")) {
                Integer idS = getIdFiled();
                if (idS != null) {
                    Produkt objCp = new Produkt();
                    objCp = objCp.getById(idS);
                    Produkt obj = objCp;
                    obj.setNazwa(name);

                    if (!isExist(obj)) {
                        saveEdit(obj);
                    } else {
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("Ten produkt już istnieje");
                        a.show();
                    }
                } else {
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Aby edytować wprowadź ID dla edytowanego elementu.");
                    a.show();
                }
            } else {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Błąd!");
                a.show();
            }
        } catch (IllegalArgumentException ie)
        {
            a.setAlertType(Alert.AlertType.WARNING);
            a.setContentText(ie.getMessage());
            a.show();
        }

    }

    /**
     * Metoda sprawdzająca poprawność edytowanej ceny
     * @param obStringCellEditEvent
     */
    public void onEditCommitPrice(TableColumn.CellEditEvent<Produkt, Integer> obStringCellEditEvent){
        Integer price = obStringCellEditEvent.getNewValue();
        System.out.println("Edit price: " + price);
        try{
            if(price != null && price > 0)
            {
                Integer idS = getIdFiled();
                if(idS != null)
                {
                    Produkt objCp = new Produkt();
                    objCp = objCp.getById(idS.intValue());
                    Produkt obj = objCp;
                    obj.setCena(price);
                    saveEdit(obj);

                } else {
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Aby edytować wprowadź ID dla edytowanego elementu.");
                    a.show();
                }
            } else {
                a.setAlertType(Alert.AlertType.WARNING);
                a.setContentText("Błąd!");
                a.show();
            }
        } catch (RuntimeException re)
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Błąd przy zapisywaniu edytowanej komórki");
            a.show();
        }
    }


}
