package com.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    public static Stage stage;


     @Override
    public void start(Stage myStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/land.fxml")));

        myStage.setTitle("land system");
        myStage.setScene(new Scene(root));
        myStage.show();
        stage = myStage;

    }

    public static void main(String[] args) {
        launch(args);
    }
}