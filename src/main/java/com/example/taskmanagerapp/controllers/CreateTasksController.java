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
import java.util.ResourceBundle;

public class CreateTasksController extends SceneController implements Initializable {

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

    public void initialize(URL url, ResourceBundle resourceBundle) {
        priority.getItems().addAll(priorityArray);
    }
    @FXML
    void AddTaskButton(ActionEvent event) throws SQLException {
        DatabaseConnection db=new DatabaseConnection();
        if( title.getText()!="" && description.getText()!="" && due_date.getValue()!=null && time_spent.getText()!="" && priority.getValue()!=null){
            db.insertTask(title.getText(),description.getText(), due_date.getValue(), Integer.parseInt(time_spent.getText()), priority.getValue(), User.getInstance().getUserId());
            InfoAddTask.setText("Your task was added successfully!");
            title.setText("");
            description.setText("");
            due_date.setValue(null);
            time_spent.setText("");
            priority.setValue(null);
        }else{
            InfoAddTask.setText("All fields are required! - Please make sure you enter all data");
        }

    }
    @FXML
    void ViewTasksButton(ActionEvent event) {

        this.changeDimensions(1477, 700);
        this.changeScene(SCENE_IDENTIFIER.VIEW_TASKS);
    }


}
