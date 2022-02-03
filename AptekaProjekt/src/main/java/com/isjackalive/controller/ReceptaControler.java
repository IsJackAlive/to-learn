package com.isjackalive.controller;

import com.isjackalive.entity.Model;
import com.isjackalive.entity.Pacjent;
import com.isjackalive.entity.Produkt;
import com.isjackalive.entity.Recepta;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ReceptaControler implements Initializable {

    @FXML
    private TableView<Recepta> receptaViewTable;

    @FXML
    private TableColumn receptaViewIdColumn;

    @FXML
    private TableColumn receptaViewDoktorColumn;

    @FXML
    private TableColumn receptaViewPacjentColumn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        receptaViewIdColumn.setCellValueFactory(new PropertyValueFactory<Recepta, Integer>("id"));
        receptaViewDoktorColumn.setCellValueFactory(new PropertyValueFactory<Recepta, Integer>("doktor"));
        //receptaViewPacjentColumn.setCellValueFactory(new PropertyValueFactory<Recepta, Integer>("pacjent"));
        receptaViewTable.setItems(Model.getModel().getReceptaObsTable());
    }
}
