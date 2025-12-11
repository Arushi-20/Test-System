package com.onlinetest.controller;

import com.onlinetest.dao.UserDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.onlinetest.model.User;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    private UserDao userDao = new UserDao();

    @FXML
    void handleLogin(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        User user = userDao.login(username, password);
        if (user != null) {
            try {
                Stage stage = (Stage) txtUsername.getScene().getWindow();
                if (user.getRole().equalsIgnoreCase("admin")) {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/admin_dashboard.fxml"))));
                } else {
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/fxml/student_dashboard.fxml"))));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Login Failed");
            alert.setHeaderText(null);
            alert.setContentText("Invalid username or password!");
            alert.showAndWait();
        }
    }
}
