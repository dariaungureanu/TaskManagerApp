package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.Task;
import com.example.taskmanagerapp.User;
import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewRemainTasksController extends SceneController {

    @FXML
    private Button LoadTasksButton;
    @FXML
    TableView<Task> taskTableView;

    @FXML
    private TableColumn<Task, String> titleColumn;

    @FXML
    private TableColumn<Task, String> descriptionColumn;

    @FXML
    private TableColumn<Task, String> dueDateColumn;

    @FXML
    private TableColumn<Task, String> priorityColumn;

    @FXML
    private TableColumn<Task, String> timeSpentColumn;

    @FXML
    private TableColumn<Task, String> tagColumn;
    @FXML
    private TableColumn<Task, String> commentColumn;

    @FXML
    public void initialize() {
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        dueDateColumn.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        timeSpentColumn.setCellValueFactory(new PropertyValueFactory<>("timeSpent"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tagName"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("commentText"));
        LoadTasksButton.setOnAction(event -> {
            try {
                loadTasks();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //System.out.println("After loadTasks");

    }

    public void loadTasks() {
        try {
            DatabaseConnection db = new DatabaseConnection();
            ResultSet resultSet = db.getRemainTask();
            taskTableView.getItems().clear();

            while (resultSet.next()) {
                Task task = new Task(
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getDate("due_date"),
                        resultSet.getString("priority"),
                        resultSet.getString("time_spent"),
                        resultSet.getString("tag_name"),
                        resultSet.getString("comment_text")
                );
                taskTableView.getItems().add(task);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void BackButton(ActionEvent event) {
        this.changeDimensions(1477, 700);
        this.changeScene(SCENE_IDENTIFIER.VIEW_TASKS);
    }

}
