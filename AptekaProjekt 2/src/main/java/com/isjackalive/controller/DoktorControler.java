package com.isjackalive.controller;

import com.isjackalive.entity.Doktor;
import com.isjackalive.entity.MainHib;
import com.isjackalive.entity.Model;
import com.isjackalive.entity.Produkt;
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
public class DoktorControler implements Initializable {

    /**
     * Okno dialogowe umożliwiające wyświetlanie użytkownikowi monitów, np. o błędzie
     */
    Alert a = new Alert(Alert.AlertType.NONE);

    //Widok tabeli
    @FXML
    private TableView<Doktor> doktorViewTable;

    @FXML
    private TableColumn<Doktor, Integer> doktorViewIdColumn;

    @FXML
    private TableColumn doktorViewImieColumn;

    @FXML
    private TableColumn doktorViewNazwiskoColumn;

    @FXML
    private TableColumn<Doktor, String> doktorViewTelefonColumn;

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
        ArrayList<Doktor> r = new ArrayList<>();
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

                    if (isStringOnlyAlphabet(name) == true && isStringOnlyAlphabet(lstName) ==true)
                    {
                        ArrayList<Doktor> dok = szukajObj(id, name, lstName, tel);
                        tableView(dok);
                    }
                    else {
                        a.setAlertType(Alert.AlertType.INFORMATION);
                        a.setContentText("Niepoprawne imie / nazwisko.");
                        a.show();
                    }
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
     * Metoda szukająca obiektów, szuka wyłącznie dla wprowadzonych danych.
     * Metoda przyjmuje 4 argumenty, dla każdego pola tekstowego osobno.
     * id wyświetlanemu w GUI odpowiada idS itd.
     * Dla każdego pola tekstowego wynik jest wyszukiwany indywidualnie, tzn. mamy możliwość
     * wpisania ID oraz nieodpowiadającego mu Imienia, Nazwiska, aby utworzyć listę wyników
     * wyszukiwania: ID, Imienia, Nazwiska, którą na samym końcu zwraca.
     *
     * @param idS   wartość całkowita wyszukująca id
     * @param nameS tekst wyszukujący imienia
     * @param lstNameS  tekst wyszukujący nazwiska
     * @param telS  tekst wyszukujący podobnego telefonu
     * @return Zwraca listę obiektów spełniające wprowadzone kryteria.
     */
    public ArrayList<Doktor> szukajObj(Integer idS, String nameS, String lstNameS, String telS)
    {
        Doktor obj = new Doktor();
        ArrayList<Doktor> arr = new ArrayList<>();

            if(idS != null)     arr.add(obj.getDoktorById(idS.intValue()));
            if (!Objects.equals(telS, "")) {
                List<Doktor> tel = obj.getDoktorByTelephone(telS);
                for(int i=0; i < tel.size(); i++)
                    arr.add (tel.get(i));
            }
            if (!Objects.equals(lstNameS, "")) {
                List<Doktor> lnm =obj.getDoktorByLastName(lstNameS);
                for(int i=0; i < lnm.size(); i++)
                    arr.add (lnm.get(i));
            }
            if (!Objects.equals(nameS, "")){
                List<Doktor> nm = obj.getDoktorByName(nameS);
                for(int i=0; i < nm.size(); i++)
                    arr.add (nm.get(i));
            }
        return arr;
    }

    /**
     * Zczytanie pola tekstowego ID
     * @return Zwraca ID jeżeli zostało poprawnie wprowadzone.
     */
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

    /**
     * Zczytanie pola tekstowego Imie
     * @return Zwraca Imię jeżeli zostało poprawnie wprowadzone.
     */
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

    /**
     * Zczytanie pola tekstowego Nazwisko
     * @return Zwraca Nazwisko jeżeli zostało poprawnie wprowadzone.
     */
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

    /**
     * Zczytanie pola tekstowego Telefon
     * @return Zwraca Telefon jeżeli został poprawnie wprowadzony.
     */
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
                }   else {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Błąd przy odczytaniu pola Telefon");
                a.show();
                return null; }
            }
        }
    }

    /**
     * Metoda sprawdzająca poprawność wpisanego telefonu.
     *
     * @param obj   tekst (numer telefonu) do sprawdzenia
     * @return  Jeżeli telefon spełnia warunek odpowiadający (3cyfry - 3cyfry - 4cyfry)
     *      * zwraca wartość true.
     */
    public boolean validatePhonenumber(String obj) {
        Pattern pattern = Pattern.compile("^(\\d{3}[-]?){2}\\d{4}$");     //"^(\\d{3}[- .]?){2}\\d{4}$"
        Matcher matcher = pattern.matcher(obj);
        return matcher.matches();
    }

    /**
     * Metoda sprawdzająca czy do pola tekstowego wprowadzono wyłącznie litery
     * @param str   tekst do sprawdzenia
     * @return  Jeżeli tekst składa się z liter A-Z, a-z, oraz polskich znaków diakrytycznych
     *      zwraca wartość true.
     */
    public static boolean isStringOnlyAlphabet(String str)
    {
        return (str.matches("^[A-Za-zżźćńółęąśŻŹĆĄŚĘŁÓŃ]*$"));
    }

    /**
     * Metoda dodajaca obiekt do bazy
     * W pierwszej kolejności sprawdza poprawność wprowadzonych danych,
     * jeżeli któreś pole nie zostało wpisane poprawnie, metoda wyświetli
     * odpowiedni alert. Jeżeli jednak pola zostały poprawnie uzupełnione,
     * metoda tworzy pusty obiekt, któremu dodawane są kolejno wprowadzone atrybuty.
     * Następnie obiekt ten jest zapisywany przez funkcję hibernate .save(obj)
     *
     * @param nameS     imię dodawane do nowego obiektu
     * @param lstNameS  nazwisko dodawane do nowego obiektu
     * @param telS      telefon dodawany do nowego obiektu
     */
    public void dodajObj(String nameS, String lstNameS, String telS)
    {
            Doktor obj = new Doktor();
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
                        a.setContentText("Ten doktor już istnieje. \nWprowadź inny numer telefonu");
                        a.show();
                    }
                } else{
                    a.setAlertType(Alert.AlertType.INFORMATION);
                    a.setContentText("Niepoprawnie wprowadzone Imię lub Nazwisko");
                    a.show();
                }
            } else {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("Aby poprawnie dodać uzupełnij pola: \nImię, Nazwisko, Telefon(xxx-xxx-xxxx)");
                a.show();
            }
    }

    /**
     *  //Druga metoda sprawdzająca czy wpisany telefon nie jest
     *  pusty i czy posiada odpowiednią ilość znaków.
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
            obj.deleteById(Doktor.class, idS);
        }else
        {
            a.setAlertType(Alert.AlertType.INFORMATION);
            a.setContentText("Aby poprawnie usunąć uzupełnij pole ID");
            a.show();
        }
    }


    /**
     * //Lista przechowująca wyszukane obiekty
     */
    private ObservableList<Doktor> doktorObsTable1 = FXCollections.observableArrayList();

    /**
     * Widok w tabeli dla wyszukanych obiektów, znajdujących się w liście
     *
     * @param obj to lista którą ta metoda wyświetla
     */
    public void tableView (ArrayList<Doktor> obj)
    {
        doktorViewIdColumn.setCellValueFactory(new PropertyValueFactory<Doktor, Integer>("id"));
        doktorViewImieColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("imie"));
            doktorViewImieColumn.setEditable(true);
            doktorViewImieColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        doktorViewNazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("nazwisko"));
            doktorViewNazwiskoColumn.setEditable(true);
            doktorViewNazwiskoColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        doktorViewTelefonColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("telefon"));
            doktorViewTelefonColumn.setEditable(true);
            doktorViewTelefonColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        //
        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
        List<Doktor> doktorzy = obj;
        session.getTransaction().commit();
        session.close();
        doktorObsTable1.setAll(doktorzy);
        //
        doktorViewTable.setEditable(true);
        doktorViewTable.setItems(doktorObsTable1);
        //doktorViewTable.refresh();
    }

    /**
     * //Widok w tabeli dla wszystkich obiektów
     */
    public void tableViewAll()
    {
        doktorViewIdColumn.setCellValueFactory(new PropertyValueFactory<Doktor, Integer>("id"));
        doktorViewImieColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("imie"));
        doktorViewNazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("nazwisko"));
        doktorViewTelefonColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("telefon"));
        doktorViewTable.setItems(Model.getModel().getDoktorObsTable());
        doktorViewTable.setEditable(false);
    }

    /**
     * //Metoda sprawdzająca poprawność edytowanego imienia
     * @param doktorStringCellEditEvent
     */
    public void onEditCommitName(TableColumn.CellEditEvent<Doktor, String> doktorStringCellEditEvent) {
        String name = doktorStringCellEditEvent.getNewValue();
        System.out.println("Editname: " + name);
        try{
            System.out.println( isStringOnlyAlphabet(name));
            if(isStringOnlyAlphabet(name) == true && name != null && !name.equals(""))
            {
                Integer idS = getIdFiled();
                if(idS != null)
                {
                    Doktor objCp = new Doktor();
                    objCp = objCp.getDoktorById(idS.intValue());
                    Doktor obj = objCp;
                    obj.setImie(name);

                    System.out.println("change: " + objCp.getImie() + objCp.getNazwisko());
                    System.out.println("to: " + obj.getImie() + obj.getNazwisko());
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
     * //Metoda sprawdzająca poprawność edytowanego nazwiska
     * @param doktorStringCellEditEvent
     */
    public void onEditCommitLastName(TableColumn.CellEditEvent<Doktor, String> doktorStringCellEditEvent) {
        String name = doktorStringCellEditEvent.getNewValue();
        System.out.println("Edit lastname: " + name);
        try{
            System.out.println( isStringOnlyAlphabet(name));
            if(isStringOnlyAlphabet(name) == true && name != null && !name.equals(""))
            {
                Integer idS = getIdFiled();
                if(idS != null)
                {
                    Doktor objCp = new Doktor();
                    objCp = objCp.getDoktorById(idS.intValue());
                    Doktor obj = objCp;
                    obj.setNazwisko(name);

                    System.out.println("change: " + objCp.getImie() + objCp.getNazwisko());
                    System.out.println("to: " + obj.getImie() + obj.getNazwisko());
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
     * Metoda sprawdza czy wprowadzona nazwa produktu już nie istnieje
     * @param obj poszukiwany obiekt
     * @return true dla telefonu(który jest unikatowy), który bez przeszkód wywołał metodę getByExclTel()
     *  tj. pobrał obiekt za pomocą tej metody.
     *  false, jeżeli w trakcie tej operacji metoda getByExclTel() wykroczyła poza indeksy sprawdzanych obiektów
     */
    public boolean isExist (Doktor obj)
    {
        Doktor current = new Doktor();
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
     * Metoda sprawdzająca poprawność edytowanego telefonu,
     * Edytowany telefon nie może zostać usunięty, nie może zawierać liter,
     * a także musi spełniać warunki narzucone przez validatePhoneNumber().
     * Telefon nie może się powtarzać, co sprawdza metoda isExist()
     *
     * @param doktorStringCellEditEvent wartość edytowanej komórki
     */
    public void onEditCommitPhone(TableColumn.CellEditEvent<Doktor, String> doktorStringCellEditEvent) {
        String name = doktorStringCellEditEvent.getNewValue();
        System.out.println("Edit phone number: " + name);
        try{
            System.out.println( isStringOnlyAlphabet(name));
            if(isStringOnlyAlphabet(name) == false && name != null && validatePhonenumber(name) == true)
            {
                Integer idS = getIdFiled();
                if(idS != null)
                {
                    Doktor objCp = new Doktor();
                    objCp = objCp.getDoktorById(idS.intValue());
                    Doktor obj = objCp;
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
     * Metoda zapisująca wprowadzony obiekt do bazy (po edytowaniu)
     * @param obj   obiekt wprowadzany do bazy
     */
    public void saveEdit(Doktor obj)
    {
        Session session = MainHib.getSessionFactory().openSession();
        session.beginTransaction();
                session.update(obj);
        session.getTransaction().commit();
        session.close();
    }
}

