package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.User;
import com.example.taskmanagerapp.utils.ApplicationHandler;
import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;
import java.sql.SQLException;

public class LogInController extends SceneController {
    private ApplicationHandler applicationHandler=new ApplicationHandler();

    @FXML
    private Text logInInfo;
    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField usernameField;

    @FXML
    void LogInButtonClick(ActionEvent event) throws SQLException{
        if(usernameField.getText()!="" && passwordField.getText()!=""){
            if(verifyLogIn(usernameField.getText(), passwordField.getText())){
                User.getInstance().setUsername(usernameField.getText());
                this.changeDimensions(550, 480);
                this.changeScene(SCENE_IDENTIFIER.CREATE_TASKS);
            }else{
                logInInfo.setText("Invalid username or password");
            }
        }else{
            logInInfo.setText("All fields are required! - Please provide username and password");
        }
    }

    private boolean verifyLogIn(String username, String password) throws SQLException {
        DatabaseConnection db =new DatabaseConnection();
        //System.out.println("Entered verifyLogIn with username: " + username + " and password: " + password);
        return db.getUser(usernameField.getText(), passwordField.getText());
    }

    @FXML
    void SignUpButton(ActionEvent event) {
        this.changeScene(SCENE_IDENTIFIER.SIGNUP);
    }

    @FXML
    void passwordField(ActionEvent event) {

    }

    @FXML
    void usernameField(ActionEvent event) {

    }

}
