package com.isjackalive;

import com.isjackalive.controller.ProduktControler;
import com.isjackalive.entity.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

/**
 * JavaFX App
 * Aplikacja obsługująca recepty
 *
 * @author damian
 */
public class App extends Application {

    private static Scene scene;

    /**
        Dodanie przykładowych danych do bazy.
     */
    private void loadAnyData(boolean lad)
    {
        if(lad)
        {
            Model m = new Model();
            m.testData();
        }
    }

    @Override
    public void start(Stage stage) throws IOException {

        loadAnyData(false);

            //ładowanie obiektów z dokumentu xml
            FXMLLoader loader = new FXMLLoader(App.class.getResource("Main.fxml"));
            VBox mainPane = loader.load();
            Scene scene = new Scene(mainPane);

            //wczytanie kaskadowego arkusza stylów (CSS)
            String cssStyle = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(cssStyle);

            stage.setTitle("Recepta-projekt");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource( fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    private void saveToFile(File file) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.newDocument();

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(file);
        transformer.transform(source, result);
    }
}