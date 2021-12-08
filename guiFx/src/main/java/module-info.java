module com.example.guifx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.guifx to javafx.fxml;
    exports com.example.guifx;
}