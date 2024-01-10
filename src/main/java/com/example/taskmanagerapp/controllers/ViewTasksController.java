package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.Task;
import com.example.taskmanagerapp.User;
import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
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

public class ViewTasksController extends SceneController {

    @FXML
    private TextField TaskNameDelete;
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
        System.out.println("ViewTasksController initialized");
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
            ResultSet resultSet = db.getTask();
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
                //System.out.println("Task added: " + task.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void DeleteTaskButton(ActionEvent event) {
        String taskName=TaskNameDelete.getText();
        try{
            DatabaseConnection db = new DatabaseConnection();
            int taskId = db.getTaskIdFromTaskName(taskName);
            db.deleteTask(taskId);
            loadTasks();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    @FXML
    void AddTagButton(ActionEvent event) {
        this.changeDimensions(470, 440);

        this.changeScene(SCENE_IDENTIFIER.ADD_TAG);
    }
    @FXML
    void AddCommentButton(ActionEvent event) {
        this.changeDimensions(470, 440);
        this.changeScene(SCENE_IDENTIFIER.ADD_COMMENT); }


    @FXML
    void ExitButton(ActionEvent event) {
        this.changeDimensions(550, 400);
        this.changeScene(SCENE_IDENTIFIER.LOGIN);
    }

    @FXML
    void NewTaskButton(ActionEvent event) {
        this.changeDimensions(550, 480);
        this.changeScene(SCENE_IDENTIFIER.CREATE_TASKS);
    }

    @FXML
    void RemainTasksButton(ActionEvent event) {
        this.changeDimensions(1477, 700);
        this.changeScene(SCENE_IDENTIFIER.VIEW_REMAINTASKS);
    }

    @FXML
    void UpdateTaskButton(ActionEvent event) {
        this.changeDimensions(550, 530);
        this.changeScene(SCENE_IDENTIFIER.UPDATE_TASKS);
    }

}
