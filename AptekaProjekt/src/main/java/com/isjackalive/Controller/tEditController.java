package com.isjackalive.Controller;

import com.isjackalive.App;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class tEditController {
    public Button viewButton;

    @FXML
    private void switchToView() throws IOException {
        App.setRoot("tableView");
    }
}
