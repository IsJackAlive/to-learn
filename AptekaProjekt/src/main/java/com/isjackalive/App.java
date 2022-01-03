package com.isjackalive;

import com.isjackalive.entity.Doktor;
import com.isjackalive.entity.Pacjent;
import com.isjackalive.entity.Recepta;
import com.isjackalive.table.TableMain;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


import java.io.IOException;

/**
 * JavaFX App
 * Hibernate connection
 *
 * @author damian
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {

        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();

        Doktor doktor1 = new Doktor();
        doktor1.setImie("Jan");

        Pacjent pacjent1 = new Pacjent();
        pacjent1.setImie("Marek");

        Recepta recepta = new Recepta();
        recepta.setDoktor(doktor1);
        recepta.setPacjent(pacjent1);

        scene = new Scene(loadFXML("tableView"));
        stage.setScene(scene);
        stage.show();
<<<<<<< Updated upstream
        SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        Session session = sessionFactory.openSession();
=======
        stage.setTitle("Recepty-projekt");

>>>>>>> Stashed changes
        session.close();
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