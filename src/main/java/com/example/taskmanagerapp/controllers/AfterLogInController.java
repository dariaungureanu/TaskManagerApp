package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class AfterLogInController extends SceneController implements Initializable {

    @FXML
    private ChoiceBox<String> TaskCattegoryChoice;

    private String[] cattegory = {"work", "personal", "school", "nonprofits"};

    public void initialize(URL url, ResourceBundle resourceBundle) {

        TaskCattegoryChoice.getItems().addAll(cattegory);
    }
    @FXML
    void NextButtonClick(ActionEvent event) {
        String selectedCategory = TaskCattegoryChoice.getValue();

        if (selectedCategory != null && !selectedCategory.isEmpty()) {
            DatabaseConnection db = new DatabaseConnection();
            db.insertCategory(selectedCategory);
            System.out.println("Selected category: " + selectedCategory);

            this.changeScene(SCENE_IDENTIFIER.CREATE_FIRST_PROJECT);
        } else {
            System.out.println("Please select a category before proceeding.");
        }
    }
    @FXML
    void SkipButtonClick(ActionEvent event) {
        this.changeScene(SCENE_IDENTIFIER.CREATE_TASKS);
    }

}
