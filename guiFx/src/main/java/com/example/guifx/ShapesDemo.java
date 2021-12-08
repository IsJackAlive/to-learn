package com.example.guifx;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class ShapesDemo extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        stage.setTitle("Shape:  rectangle");
        Group group = new Group();

        Rectangle rctgl = new Rectangle();

        rctgl.setX(200);
        rctgl.setY(180);
        rctgl.setWidth(300);
        rctgl.setHeight(300);

        group.getChildren().addAll(rctgl);

        Scene scene = new Scene(group, 300, 300, Color.WHITE);
        stage.setScene(scene);
        stage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}
