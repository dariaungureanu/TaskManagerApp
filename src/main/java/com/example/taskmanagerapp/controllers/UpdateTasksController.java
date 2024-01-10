package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.User;
import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class UpdateTasksController extends SceneController implements Initializable {

    @FXML
    private Text InfoAddTask;
    @FXML
    private TextField description;

    @FXML
    private DatePicker due_date;

    @FXML
    private ChoiceBox<String> priority;

    private String[] priorityArray = {"high", "medium", "low"};
    @FXML
    private TextField time_spent;

    @FXML
    private TextField title;

    @FXML
    private TextField oldTitle;


    public void initialize(URL url, ResourceBundle resourceBundle) {

        priority.getItems().addAll(priorityArray);
    }
    @FXML
    void UpdateTaskButton(ActionEvent event)throws SQLException {
        DatabaseConnection db=new DatabaseConnection();
        if (oldTitle.getText() != "") {

            String updatedTitle = (title.getText() != null && !title.getText().isEmpty()) ? title.getText() : null;
            String updatedDescription = (description.getText() != null) ? description.getText() : null;
            LocalDate updatedDueDate = due_date.getValue();
            Integer updatedTimeSpent = (time_spent.getText() != null && !time_spent.getText().isEmpty()) ? Integer.parseInt(time_spent.getText()) : null;
            String updatedPriority = priority.getValue();

            db.updateTask(updatedTitle, updatedDescription, updatedDueDate, updatedTimeSpent, updatedPriority, User.getInstance().getUserId(), oldTitle.getText());
            InfoAddTask.setText("Your task was updated successfully!");
            oldTitle.setText("");
            title.setText("");
            description.setText("");
            due_date.setValue(null);
            time_spent.setText("");
            priority.setValue(null);
        } else {
            InfoAddTask.setText("Please make sure you enter the old task's title ");
        }
    }

    @FXML
    void ViewTasksButton(ActionEvent event) {
        this.changeDimensions(1477, 700);
        this.changeScene(SCENE_IDENTIFIER.VIEW_TASKS);
    }

}
