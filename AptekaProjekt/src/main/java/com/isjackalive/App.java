package com.isjackalive;

import com.isjackalive.entity.Doktor;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;

/**
 * JavaFX App
 * Hibernate connection
 *
 * @author damian
 */
public class App extends Application {

    public static EntityManagerFactory emf;

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

            emf = Persistence.createEntityManagerFactory("apteka-projekt");
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Main.fxml"));
            VBox mainPane = loader.load();
            Scene scene = new Scene(mainPane);

            stage.setTitle("Recepta-projekt");
            stage.setScene(scene);
            stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( fxml + ".fxml"));
        //fxmlLoader.setController(TableMain.getInstance());
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}