package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.Admin;
import com.example.taskmanagerapp.Task;
import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminViewController extends  SceneController {



    @FXML
    private TableView<Admin> taskTableView;
    @FXML
    private Button LoadDataButton;

    @FXML
    private TableColumn<Admin, String> categoryColumn;

    @FXML
    private TableColumn<Admin, String> projectColumn;

    public void initialize() {
        System.out.println("ViewTasksController initialized");
        // Set up the TableView columns
        projectColumn.setCellValueFactory(new PropertyValueFactory<>("project"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        LoadDataButton.setOnAction(event -> {
            try {
                loadData();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //System.out.println("After loadTasks");

    }
    public void loadData() {
        try {
            DatabaseConnection db = new DatabaseConnection();
            ResultSet resultSet = db.getData();
            // Clear existing items
            taskTableView.getItems().clear();

            // Populate TableView with tasks
            while (resultSet.next()) {
                Admin admin = new Admin(
                        resultSet.getString("project_name"),
                        resultSet.getString("category_name")
                );
                taskTableView.getItems().add(admin);
                //System.out.println("Task added: " + task.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void ExitButton(ActionEvent event) {
        this.changeDimensions(550, 400);
        this.changeScene(SCENE_IDENTIFIER.SIGNUP);
    }

}
