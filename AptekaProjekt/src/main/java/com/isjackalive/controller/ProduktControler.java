package com.isjackalive.controller;

import com.isjackalive.entity.Model;
import com.isjackalive.entity.Produkt;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ProduktControler implements Initializable {

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

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        produktViewIdColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Integer>("id"));
        produktViewCenaColumn.setCellValueFactory(new PropertyValueFactory<Produkt, Integer>("cena"));
        produktViewDataColumn.setCellValueFactory(new PropertyValueFactory<Produkt, String>("data"));
        produktViewNazwaColumn.setCellValueFactory(new PropertyValueFactory<Produkt, String>("nazwa"));

        produktViewTable.setItems(Model.getModel().getProduktObsTable());
    }
}
