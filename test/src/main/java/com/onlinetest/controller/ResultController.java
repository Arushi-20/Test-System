package com.onlinetest.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ResultController {

    @FXML
    private Label lblScore;

    @FXML
    private Button btnClose;

    // Set score from TakeTestController
    public void setScore(int score, int total) {
        lblScore.setText("You scored " + score + " out of " + total);
    }

    @FXML
    private void initialize() {
        // Close button closes entire program
        btnClose.setOnAction(e -> {
            Stage stage = (Stage) btnClose.getScene().getWindow();
            stage.close();
            System.exit(0); // Exits the entire application
        });
    }
}
