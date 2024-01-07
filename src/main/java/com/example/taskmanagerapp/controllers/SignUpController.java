package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.utils.DatabaseConnection;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.sql.*;

public class SignUpController extends SceneController {
    @FXML
    private Text signUpInfo;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    void LogInButton(ActionEvent event) {
        this.changeScene(SCENE_IDENTIFIER.LOGIN);
    }
    @FXML
    public void SignUpButtonClick(ActionEvent event) throws SQLException {
        DatabaseConnection db = new DatabaseConnection();
        if(username.getText()!="" && password.getText()!=""){
            db.insertUser(username.getText(), password.getText());
            if(username.getText()=="admin" && password.getText()=="12345"){
                this.changeScene(SCENE_IDENTIFIER.ADMIN);
            }else {
                this.changeScene(SCENE_IDENTIFIER.LOGGEDIN);

            }
        }else{
            signUpInfo.setText("All fields are required! -Please make sure you enter all data");
        }
    }

    public void username(ActionEvent actionEvent) {
    }

    public void password(ActionEvent actionEvent) {
    }


}
