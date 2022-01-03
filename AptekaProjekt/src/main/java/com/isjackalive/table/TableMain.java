package com.isjackalive.table;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TableMain implements Initializable {

    public VBox tViewCon;
    public VBox tEditCon;
    @FXML
    private TableEdit tableEdit;

    @FXML
    private TablePreview tablePreview;

    @FXML
    private Tab tab;

    @FXML
    private final static TableMain instance = new TableMain();

    @FXML
    private TabPane mainPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void newTab(Tab tab)
    {
        mainPane.getTabs().add(tab);
        mainPane.getSelectionModel().select(tab);
    }

    public static TableMain getInstance()
    {
        return instance;
    }
}
