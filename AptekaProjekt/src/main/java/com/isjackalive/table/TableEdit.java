package com.isjackalive.table;

import com.isjackalive.entity.Doktor;

import com.isjackalive.entity.MainHib;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TableEdit implements Initializable {

    @FXML
    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @FXML
    private TextField doktorEditName;
    @FXML
    private TextField doktorEditLastName;
    @FXML
    private TextField doktorEditPhone;
    @FXML
    private Integer doktorEID;

    @FXML
    private TextField pacjentEditName;
    @FXML
    private TextField pacjentEditLastName;
    @FXML
    private TextField pacjentEditPhone;

    @FXML
    private Integer receptaEditDoktor;
    @FXML
    private Integer receptaEditPacjent;
    @FXML
    private Integer receptaEditProdukt;

    @FXML
    private Integer produktEditCena;
    @FXML
    private Date produktEditData;
    @FXML
    private TextField produktEditNazwa;

    @FXML
    private TableView<Doktor> doktorTableView;

    public TableEdit(Integer doktorEID)
    {
        this.doktorEID = doktorEID;
    }

    @FXML
    @Override
    public void initialize(URL url,ResourceBundle rb)
    {
        Doktor editDoktor = MainHib.getInstance().getDoktorTable().get(doktorEID);
        doktorEditName.setText(editDoktor.getImie());
        doktorEditName.setText(editDoktor.getNazwisko());
        doktorEditName.setText(editDoktor.getTelefon());

        doktorTableView.getItems().add(editDoktor);
    }

}
