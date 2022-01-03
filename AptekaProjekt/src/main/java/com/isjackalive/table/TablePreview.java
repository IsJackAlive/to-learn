package com.isjackalive.table;

import com.isjackalive.App;
import com.isjackalive.entity.Doktor;
import com.isjackalive.entity.MainHib;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class TablePreview implements Initializable {
    @FXML
    private SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    @FXML
    private TextField doktorName;
    @FXML
    private TextField doktorLastName;
    @FXML
    private TextField doktorPhone;
    @FXML
    private Integer doktorEID;

    @FXML
    private TextField pacjentName;
    @FXML
    private TextField pacjentLastName;
    @FXML
    private TextField pacjentPhone;

    @FXML
    private Integer receptaDoktor;
    @FXML
    private Integer receptaPacjent;
    @FXML
    private Integer receptaProdukt;

    @FXML
    private Integer produktCena;
    @FXML
    private Date produktData;
    @FXML
    private TextField produktNazwa;

    @FXML
    private TableView<Doktor> doktorTableView;


    @FXML
    private void newTable(Integer doktorEID) throws IOException{
        Tab tab = new Tab("Edytuj: " + MainHib.getInstance().getDoktorTable().get(doktorEID).getNazwisko());
        FXMLLoader fmxlL = new FXMLLoader(App.class.getResource("tableEdit.fxml"));
        fmxlL.setController(new TableEdit(doktorEID));
        AnchorPane pane = fmxlL.load();
        tab.setContent(pane);

    }

    @FXML
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
