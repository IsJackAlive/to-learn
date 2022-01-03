package com.isjackalive.Controller;

import java.io.IOException;

import com.isjackalive.App;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
