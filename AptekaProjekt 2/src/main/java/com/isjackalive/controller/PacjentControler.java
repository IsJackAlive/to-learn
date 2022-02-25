package com.isjackalive.controller;

import com.isjackalive.entity.*;
import com.isjackalive.entity.Pacjent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import org.hibernate.Session;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JavaFX Controller dla zakładki Doktor
 */
public class PacjentControler  implements Initializable{

    /**
     * Okno dialogowe umożliwiające wyświetlanie użytkownikowi monitów, np. o błędzie
     */
    Alert a = new Alert(Alert.AlertType.NONE);

    //Widok tabeli
    @FXML
    private TableView<Pacjent> pacjentViewTable;

    @FXML
    private TableColumn pacjentViewIdColumn;

    @FXML
    private TableColumn pacjentViewImieColumn;

    @FXML
    private TableColumn pacjentViewNazwiskoColumn;

    @FXML
    private TableColumn pacjentViewTelefonColumn;

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
    private TextField imieFiled;

    @FXML
    private TextField nazwiskoFiled;

    @FXML
    private TextField telefonFiled;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources){

        //tableViewAll();
        ArrayList<Pacjent> r = new ArrayList<>();
        tableView(r);

        szukajButton.setOnAction((ActionEvent event) -> {
            Integer id;
            String name;
            String lstName;
            String tel;

            id = (getIdFiled());
            name = getImieFiled();
            lstName = getNazwiskoFiled();
            tel = getTelefonFiled();

                ArrayList<Pacjent> pac;
                pac = szukajObj(id, name, lstName, tel);
                tableView(pac);
        });

        dodajButton.setOnAction((ActionEvent event) -> {
            String name;
            String lstName;
            String tel;

            name = getImieFiled();
            lstName = getNazwiskoFiled();
            tel = getTelefonFiled();

            try
            {
                dodajObj(name, lstName, tel);
            }
            catch (NullPointerException e)
            {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Nie można dodać.");
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
            tableViewAll();
        });

        wyczyscButton.setOnAction((ActionEvent event) -> {
            idFiled.setText("");
            imieFiled.setText("");
            nazwiskoFiled.setText("");
            telefonFiled.setText("");
            tableView(r);
        });
    }

    /**
     * Metoda szukająca obiektów, szuka wyłącznie dla wprowadzonych danych
     * @param idS
     * @param nameS
     * @param lstNameS
     * @param telS
     * @return Zwraca listę obiektów spełniające wprowadzone kryteria.
     */
    public ArrayList<Pacjent> szukajObj(Integer idS, String nameS, String lstNameS, String telS)
    {
        System.out.println("szukaj: " + idS + " " + nameS + " " + lstNameS + " " + telS);

        Pacjent obj = new Pacjent();
        ArrayList<Pacjent> arr = new ArrayList<>();

        if(idS != null)     arr.add(obj.getById(idS.intValue()));
        if (!Objects.equals(telS, "")) {
            List<Pacjent> tel = obj.getByTelephone(telS);
            for(int i=0; i < tel.size(); i++)
                arr.add (tel.get(i));
        }
        if (!Objects.equals(lstNameS, "")) {
            List<Pacjent> lnm =obj.getByLastName(lstNameS);
            for(int i=0; i < lnm.size(); i++)
                arr.add (lnm.get(i));
        }
        if (!Objects.equals(nameS, "")){
            List<Pacjent> nm = obj.getByName(nameS);
            for(int i=0; i < nm.size(); i++)
                arr.add (nm.get(i));
        }

        return arr;
    }

    public Integer getIdFiled() {
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

    public String getImieFiled() {
        String obj = imieFiled.getText();
        char[] chars = obj.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c: chars)
        {
            if(!Character.isDigit(c))
            {
                obj = String.valueOf(sb.append(c));
            }
        }
        return obj;
    }

    public String getNazwiskoFiled() {
        String obj = nazwiskoFiled.getText();
        char[] chars = obj.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c: chars)
        {
            if(!Character.isDigit(c))
            {
                obj = String.valueOf(sb.append(c));
            }
        }
        return obj;
    }

    public String getTelefonFiled() {
        String obj = telefonFiled.getText();
        if(obj == null || obj.equals("")) return null;
        if(isStringOnlyAlphabet(obj))
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Pole Telefon zawiera błędne znaki!");
            a.show();
            return null;
        }
        else
        {
            if(validatePhonenumber(obj))
            {
                return obj;
            }
            else
            {
                if(obj.length() < 4 && obj.matches("[0-9]+")) return obj;
                if((obj.length() >= 4) && (obj.length() <= 12)){
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Wpisz 3 cyfry lub cały numer telefonu\nWprowadź w formacie xxx-xxx-xxxx");
                    a.show();
                    return null;
                } else {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Błąd przy odczytaniu pola Telefon");
                a.show();
                return null;}
            }
        }
    }

    /**
     * Metoda sprawdzająca poprawność wpisanego telefonu.
     * @param obj
     * @return
     */
    public boolean validatePhonenumber(String obj) {
        Pattern pattern = Pattern.compile("^(\\d{3}[-]?){2}\\d{4}$");     //"^(\\d{3}[- .]?){2}\\d{4}$"
        Matcher matcher = pattern.matcher(obj);
        return matcher.matches();
    }

    /**
     * Metoda sprawdzająca czy do pola tekstowego wprowadzono wyłącznie litery
     * @param str
     * @return
     */
    public static boolean isStringOnlyAlphabet(String str)
    {
        return (str.matches("^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*$"));
    }

    /**
     * Metoda dodajaca obiekt do bazy
     * @param nameS
     * @param lstNameS
     * @param telS
     */
    public void dodajObj(String nameS, String lstNameS, String telS)
    {
       Pacjent obj = new Pacjent();
        if(checkTelefonF(telS) && !nameS.equals("") && !lstNameS.equals(""))
        {
            if (isStringOnlyAlphabet(nameS) && isStringOnlyAlphabet(lstNameS)) {

                Session session = MainHib.getSessionFactory().openSession();
                session.beginTransaction();

                obj.setImie(nameS);
                obj.setNazwisko(lstNameS);
                obj.setTelefon(telS);

                if (!isExist(obj))
                {
                    session.save(obj);
                    session.getTransaction().commit();
                    session.close();
                }
                else {
                    a.setAlertType(Alert.AlertType.WARNING);
                    a.setContentText("Ten pacjent już istnieje. \nWprowadź inny numer telefonu");
                    a.show();
                }
            } else{
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Niepoprawnie wprowadzone Imię lub Nazwisko");
                a.show();
            }
        }
        else {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Aby poprawnie dodać uzupełnij pola: \nImię, Nazwisko, Telefon(xxx-xxx-xxxx)");
            a.show();
        }
    }

    /**
     * Druga metoda sprawdzająca poprawność wpisanego telefonu.
     * @param telS
     * @return
     */
    public boolean checkTelefonF(String telS)
    {
        if(telS.equals("")) return false;
        if(telS.length() <= 10) return false;
        if(telS.length() > 12) return false;
        return true;
    }

    /**
     * Metoda usuwania obiektu, za pomocą ID
     * @param idS
     */
    public void usunObj(Integer idS)
    {
        if (idS != null) {
            Model obj = new Model();
            obj.deleteById(Pacjent.class, idS);
        }else
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Aby poprawnie usunąć uzupełnij pole ID");
            a.show();
        }
    }

    /**
     * Lista przechowująca wyszukane obiekty
     */
    private ObservableList<Pacjent> pacjentObsTable1 = FXCollections.observableArrayList();

    /**
     * Widok w tabeli dla wyszukanych obiektów
     * @param obj
     */
    public void tableView(ArrayList obj)
    {
        pacjentViewIdColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, Integer>("id"));
        pacjentViewImieColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("imie"));
            pacjentViewImieColumn.setEditable(true);
            pacjentViewImieColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            pacjentViewImieColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        pacjentViewNazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("nazwisko"));
            pacjentViewNazwiskoColumn.setEditable(true);
            pacjentViewNazwiskoColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        pacjentViewTelefonColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("telefon"));
            pacjentViewTelefonColumn.setEditable(true);
            pacjentViewTelefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        //
        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Pacjent> pacjenci = obj;
        session.getTransaction().commit();
        session.close();
        pacjentObsTable1.setAll(pacjenci);
        //
        pacjentViewTable.setEditable(true);
        pacjentViewTable.setItems(pacjentObsTable1);
        pacjentViewTable.refresh();
    }

    /**
     * Widok w tabeli dla wszystkich obiektów
     */
    public void tableViewAll()
    {
        pacjentViewIdColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, Integer>("id"));
        pacjentViewImieColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("imie"));
        pacjentViewNazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("nazwisko"));
        pacjentViewTelefonColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("telefon"));
        pacjentViewTable.setItems(Model.getModel().getPacjentObsTable());
        pacjentViewTable.setEditable(false);
    }

    /**
     * Metoda sprawdza czy wprowadzona nazwa produktu już nie istnieje
     * @param obj
     * @return
     */
    public boolean isExist (Pacjent obj)
    {
        Pacjent current = new Pacjent();
        String numer = obj.getTelefon();
        try
        {
            current.getByExclTel(numer);
            return true;
        } catch (IndexOutOfBoundsException is)
        {
            return false;
        }
    }

    /**
     * Metoda sprawdzająca poprawność edytowanego imienia
     * @param obStringCellEditEvent
     */
    public void onEditCommitName(TableColumn.CellEditEvent<Pacjent, String> obStringCellEditEvent) {
        String name = obStringCellEditEvent.getNewValue();
        System.out.println("Editname: " + name);
        try{
            System.out.println( isStringOnlyAlphabet(name));
            if(isStringOnlyAlphabet(name) == true && name != null && !name.equals(""))
            {
                Integer idS = getIdFiled();
                if(idS != null)
                {
                    Pacjent objCp = new Pacjent();
                    objCp = objCp.getById(idS.intValue());
                    Pacjent obj = objCp;
                    obj.setImie(name);

                    System.out.println("change: " + objCp.getImie() + objCp.getNazwisko());
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

    /**
     * Metoda sprawdzająca poprawność edytowanego nazwiska
     * @param obStringCellEditEvent
     */
    public void onEditCommitLastName(TableColumn.CellEditEvent<Pacjent, String> obStringCellEditEvent) {
        String name = obStringCellEditEvent.getNewValue();
        System.out.println("Edit lastname: " + name);
        try{
            System.out.println(isStringOnlyAlphabet(name));
            if(isStringOnlyAlphabet(name) == true && name != null && !name.equals(""))
            {
                Integer idS = getIdFiled();
                if(idS != null)
                {
                    Pacjent objCp = new Pacjent();
                    objCp = objCp.getById(idS.intValue());
                    Pacjent obj = objCp;
                    obj.setNazwisko(name);

                    System.out.println("change: " + objCp.getImie() + objCp.getNazwisko());
                    if(obj.getImie() != null && obj.getNazwisko() != null && obj.getTelefon() != null)
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

    /**
     * Metoda sprawdzająca poprawność edytowanego telefonu
     * @param obStringCellEditEvent
     */
    public void onEditCommitPhone(TableColumn.CellEditEvent<Pacjent, String> obStringCellEditEvent) {
        String name = obStringCellEditEvent.getNewValue();
        System.out.println("Edit phone number: " + name);
        try{
            System.out.println( isStringOnlyAlphabet(name));
            if(isStringOnlyAlphabet(name) == false && name != null && validatePhonenumber(name) == true)
            {
                Integer idS = getIdFiled();
                if(idS != null)
                {
                    Pacjent objCp = new Pacjent();
                    objCp = objCp.getById(idS.intValue());
                    Pacjent obj = objCp;
                    obj.setTelefon(name);

                    if (isExist(obj) == false)
                    {saveEdit(obj);}
                    else {
                        a.setAlertType(Alert.AlertType.WARNING);
                        a.setContentText("Ten numer telefonu już istnieje");
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
        } catch (RuntimeException re)
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Błąd przy zapisywaniu edytowanej komórki");
            a.show();
        }
    }

    /**
     * Metoda zapisująca edytowane pola
     * @param obj
     */
    public void saveEdit(Pacjent obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(obj);
        session.getTransaction().commit();
        session.close();
    }
}

