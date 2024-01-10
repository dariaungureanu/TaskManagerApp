package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AddCommentController extends SceneController {

    @FXML
    private Text InfoAddTask;

    @FXML
    private TextField commentField;

    @FXML
    private TextField taskNameField;

    @FXML
    void AddCommentButton(ActionEvent event) {
        if (commentField.getText()!="") {
            try {
                DatabaseConnection db = new DatabaseConnection();
                int taskId = db.getTaskIdFromTaskName(taskNameField.getText());
                db.insertComment(commentField.getText(), taskId);
                InfoAddTask.setText("Your comment was added successfully!");
            } catch (Exception e) {
                e.printStackTrace();
                InfoAddTask.setText("Error adding comment to the task!");
            }
        } else {
            InfoAddTask.setText("All fields are required! - Please make sure you enter all data");
        }
    }

    @FXML
    void ViewTasksButton(ActionEvent event) {
        this.changeDimensions(1477, 700);
        this.changeScene(SCENE_IDENTIFIER.VIEW_TASKS);}
}
