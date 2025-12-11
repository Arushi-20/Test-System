package com.onlinetest.controller;

import com.onlinetest.dao.QuestionDao;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import com.onlinetest.model.Question;

public class AddQuestionController {

    @FXML
    private TextField txtQuestion, txtOptionA, txtOptionB, txtOptionC, txtOptionD, txtAnswer;

    private QuestionDao questionDao = new QuestionDao();

    @FXML
    void handleAddQuestion(ActionEvent event) {
        Question q = new Question();
        q.setQuestion(txtQuestion.getText());
        q.setOptionA(txtOptionA.getText());
        q.setOptionB(txtOptionB.getText());
        q.setOptionC(txtOptionC.getText());
        q.setOptionD(txtOptionD.getText());
        q.setAnswer(txtAnswer.getText());

        questionDao.addQuestion(q);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Question added successfully!");
        alert.showAndWait();

        txtQuestion.clear();
        txtOptionA.clear();
        txtOptionB.clear();
        txtOptionC.clear();
        txtOptionD.clear();
        txtAnswer.clear();
    }
}
