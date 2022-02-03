package com.isjackalive.controller;

import com.isjackalive.entity.Doktor;
import com.isjackalive.entity.MainHib;
import com.isjackalive.entity.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;

import javax.naming.directory.SearchResult;
import java.io.IOException;
import java.lang.invoke.StringConcatException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DoktorControler  implements Initializable{

    Alert a = new Alert(Alert.AlertType.NONE);

    @FXML
    private TableView<Doktor> doktorViewTable;

    @FXML
    private TableColumn doktorViewIdColumn;

    @FXML
    private TableColumn doktorViewImieColumn;

    @FXML
    private TableColumn doktorViewNazwiskoColumn;

    @FXML
    private TableColumn doktorViewTelefonColumn;

    @FXML
    private Button szukajButton;

    @FXML
    private TextField idFiled;

    @FXML
    private TextField imieFiled;

    @FXML
    private TextField nazwiskoFiled;

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources){

        doktorViewTable.setEditable(true);

        doktorViewIdColumn.setCellValueFactory(new PropertyValueFactory<Doktor, Integer>("id"));
        doktorViewImieColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("imie"));
        doktorViewNazwiskoColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("nazwisko"));
        doktorViewTelefonColumn.setCellValueFactory(new PropertyValueFactory<Doktor, String>("telefon"));
        doktorViewTable.setItems(Model.getModel().getDoktorObsTable());

            szukajButton.setOnAction((ActionEvent event) -> {

                Doktor obj = new Doktor();

                Integer id;
                String name;
                String lstName;

                id = (getIdFiled());
                name = getImieFiled();
                lstName = getNazwiskoFiled();

                szukajObj(id, name, lstName);

            });
    }

    public void szukajObj(Integer idS, String nameS, String lstNameS)
    {
        System.out.println("szukaj: " + idS + " " + nameS + " " + lstNameS);

        Doktor obj = new Doktor();

            if(idS != null)   //id najwyżej w hierarchi wyszukiwania
                obj = obj.getDoktorById(idS.intValue());
                else
                {
                    if(nameS != ""){    //imie najniżej w hierarchi wyszukiwania
                        obj = obj.getDoktorByName(nameS);
                    }
                    if(lstNameS != ""){
                        obj = obj.getDoktorByLastName(lstNameS);
                    }
                }

        System.out.println("znaleziono: " + obj.getId() + " " + obj.getImie() + " " + obj.getNazwisko() + " " + obj.getTelefon());
    }

    public Integer getIdFiled() {
        Integer id = null;
        try {
            id = Integer.parseInt(idFiled.getText());
            if(id <= 0) {
                a.setAlertType(Alert.AlertType.INFORMATION);
                a.setContentText("ID musi być większe od 0.");
                a.show();
                id = null;
            }
        } catch (RuntimeException e){}
        finally {
            return id;}
    }

    public String getImieFiled() {
        String obj = imieFiled.getText();
        char[] chars = obj.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c: chars)
        {
            if(!Character.isDigit(c))
            {
                obj = String.valueOf(sb.append(c));
            }
        }
        return obj;
    }

    public String getNazwiskoFiled() {
        String obj = nazwiskoFiled.getText();
        char[] chars = obj.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c: chars)
        {
            if(!Character.isDigit(c))
            {
                obj = String.valueOf(sb.append(c));
            }
        }
        return obj;
    }

    
}

