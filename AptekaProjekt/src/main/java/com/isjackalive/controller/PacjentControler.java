package com.isjackalive.controller;

import com.isjackalive.entity.Model;
import com.isjackalive.entity.Pacjent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class PacjentControler implements Initializable {

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

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        pacjentViewIdColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, Integer>("id"));
        pacjentViewImieColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("imie"));
        pacjentViewNazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("nazwisko"));
        pacjentViewTelefonColumn.setCellValueFactory(new PropertyValueFactory<Pacjent, String>("telefon"));

        pacjentViewTable.setItems(Model.getModel().getPacjentObsTable());
    }
}
