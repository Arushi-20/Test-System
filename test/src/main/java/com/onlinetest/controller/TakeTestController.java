package com.onlinetest.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TakeTestController {

    @FXML
    private Label lblQuestion;
    @FXML
    private RadioButton optA, optB, optC, optD;

    private ToggleGroup optionsGroup;
    private List<Question> questions = new ArrayList<>();
    private int currentQuestionIndex = 0;
    private int score = 0;

    private final String DB_URL = "jdbc:mysql://localhost:3306/onlinetest";
    private final String DB_USER = "root";
    private final String DB_PASS = "arth";

    public static class Question {
        private String question;
        private String optionA, optionB, optionC, optionD;
        private String answer;

        public Question(String question, String a, String b, String c, String d, String answer) {
            this.question = question;
            this.optionA = a;
            this.optionB = b;
            this.optionC = c;
            this.optionD = d;
            this.answer = answer;
        }

        public String getQuestion() { return question; }
        public String getOptionA() { return optionA; }
        public String getOptionB() { return optionB; }
        public String getOptionC() { return optionC; }
        public String getOptionD() { return optionD; }
        public String getAnswer() { return answer; }
    }

    @FXML
    public void initialize() {
        // Initialize toggle group
        optionsGroup = new ToggleGroup();
        optA.setToggleGroup(optionsGroup);
        optB.setToggleGroup(optionsGroup);
        optC.setToggleGroup(optionsGroup);
        optD.setToggleGroup(optionsGroup);

        // Load questions safely
        questions = loadQuestionsFromDB();
        if (questions.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "No Questions", "No questions found in the database!");
        } else {
            loadQuestion(0);
        }
    }

    private List<Question> loadQuestionsFromDB() {
        List<Question> list = new ArrayList<>();
        String sql = "SELECT * FROM questions";
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Question q = new Question(
                        rs.getString("question_text"),
                        rs.getString("option_a"),
                        rs.getString("option_b"),
                        rs.getString("option_c"),
                        rs.getString("option_d"),
                        rs.getString("answer")
                );
                list.add(q);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Database Error", "Failed to load questions:\n" + e.getMessage());
        }
        return list;
    }

    private void loadQuestion(int index) {
        if (index < 0 || index >= questions.size()) return;
        Question q = questions.get(index);
        lblQuestion.setText(q.getQuestion());
        optA.setText(q.getOptionA());
        optB.setText(q.getOptionB());
        optC.setText(q.getOptionC());
        optD.setText(q.getOptionD());
        optionsGroup.selectToggle(null); // clear previous selection
    }

    @FXML
    private void handleNext() {
        if (optionsGroup.getSelectedToggle() == null) {
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select an option before proceeding!");
            return;
        }

        RadioButton selected = (RadioButton) optionsGroup.getSelectedToggle();
        Question current = questions.get(currentQuestionIndex);
        if (selected.getText().equals(current.getAnswer())) score++;

        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            loadQuestion(currentQuestionIndex);
        } else {
            showResultWindow();
        }
    }

    private void showResultWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Result.fxml"));
            Parent root = loader.load();

            ResultController controller = loader.getController();
            controller.setScore(score, questions.size());

            Stage stage = new Stage();
            stage.setTitle("Test Result");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to open result window:\n" + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
