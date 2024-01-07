package com.example.taskmanagerapp;

import com.example.taskmanagerapp.utils.ApplicationHandler;
import com.example.taskmanagerapp.utils.DatabaseConnection;
import javafx.application.Application;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ApplicationRun  extends Application {
    public void start(Stage stage) {
        ApplicationHandler.getInstance().start(stage);
    }

    public static void main(String[] args) throws SQLException {
        launch();
    }
}
