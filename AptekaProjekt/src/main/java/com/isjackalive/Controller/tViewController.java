package com.isjackalive.Controller;

import com.isjackalive.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class tViewController {

    public Button viewButton;

    @FXML
    public void switchToEdit(ActionEvent actionEvent) throws IOException {
        App.setRoot("tableEdit");
    }
}

