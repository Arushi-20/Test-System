package com.onlinetest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Load FXML from /src/main/resources/fxml/login.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));

        if (loader.getLocation() == null) {
            throw new IllegalStateException("❌ FXML not found at /fxml/login.fxml");
        }

        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Online Test System");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
