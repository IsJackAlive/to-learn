package com.example.guifx;

import javafx.collections.ObservableList;
import javafx.scene.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.stage.Stage;

public class guiFx {

    public void start (Stage primaryStage){
        //rysowanie linii konstruktor bezparametrowy
        Line line =new Line();
        line.setStartX(0.0f);
        line.setStartY(0.0f);
        line.setEndX(0.0f);
        line.setEndY(0.0f);

        //rysowanie linii z konstruktorem ustawiającym jej punkty
        Line blueLine = new Line(100, 100f, 200f, 50f);

        blueLine.setStroke(Color.BLUE);
        blueLine.setStrokeWidth(10);
        blueLine.setStrokeLineCap(StrokeLineCap.ROUND);

        VBox root = new VBox();
        //Pozyskanie referencji do listy obiektów Node w kontenerze tablica
        ObservableList<Node> tablica = root.getChildren();
        tablica.add(line);
        tablica.add(blueLine);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}