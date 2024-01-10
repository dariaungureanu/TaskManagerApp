package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateFirstProjectController extends SceneController{

    @FXML
    private TextField ProjectName;

    @FXML
    void NextButtonClick(ActionEvent event) {
        String project = ProjectName.getText();

        if (project != "") {
            DatabaseConnection db = new DatabaseConnection();
            db.insertProject(project);
            System.out.println("Selected project: " + project);

            this.changeDimensions(550, 480);
            this.changeScene(SCENE_IDENTIFIER.CREATE_TASKS);
        } else {
            System.out.println("Please select a category before proceeding.");
        }
    }


    @FXML
    void ProjectName(ActionEvent event) {

    }

}
