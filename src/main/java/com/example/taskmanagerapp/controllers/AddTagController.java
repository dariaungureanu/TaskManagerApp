package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.Task;
import com.example.taskmanagerapp.User;
import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class AddTagController extends SceneController implements Initializable {
    @FXML
    private Text InfoAddTask;

    @FXML
    private ChoiceBox<String> ChooseTag;
    @FXML
    private TextField taskNameField;

    private String[] tagArray = {"done", "in progress", "not done"};
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ChooseTag.getItems().addAll(tagArray);
    }
    @FXML
    void AddTagButton(ActionEvent event) {
        if (ChooseTag.getValue() != null) {
            try {
                DatabaseConnection db = new DatabaseConnection();
                int taskId = db.getTaskIdFromTaskName(taskNameField.getText());
                db.insertTag(ChooseTag.getValue(), taskId);
                InfoAddTask.setText("Your tag was added successfully!");
                taskNameField.setText("");
            } catch (Exception e) {
                e.printStackTrace();
                InfoAddTask.setText("Error adding tag to the task!");
            }
        } else {
            InfoAddTask.setText("All fields are required! - Please make sure you enter all data");
        }
    }



    @FXML
    void ViewTasksButton(ActionEvent event) {
        this.changeDimensions(1477, 700);
        this.changeScene(SCENE_IDENTIFIER.VIEW_TASKS);
    }

}
