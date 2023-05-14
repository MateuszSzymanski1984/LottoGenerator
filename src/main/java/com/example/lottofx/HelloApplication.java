package com.example.lottofx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 840, 510);
        scene.getStylesheets().addAll(this.getClass().getResource("main.css").toExternalForm());
        stage.getIcons().add(new Image(HelloApplication.class.getResourceAsStream("gold.png")));
        stage.setTitle("Hello Player!");
        stage.setScene(scene);
        stage.show();


    }

    public static void main(String[] args) {
        launch();
    }
}