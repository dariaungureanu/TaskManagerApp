package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.User;
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
            if("admin".equals(username.getText()) && "12345".equals(password.getText())){
                this.changeScene(SCENE_IDENTIFIER.ADMIN);
            }else {
                if(!verifySignUp(username.getText(), password.getText()))
                {
                    db.insertUser(username.getText(), password.getText());
                    this.changeScene(SCENE_IDENTIFIER.LOGIN);
                }else{
                    signUpInfo.setText("This username already exists! Use another unsername");
                }

            }
        }else{
            signUpInfo.setText("All fields are required! -Please make sure you enter all data");
        }
    }

    private boolean verifySignUp(String name, String pass) throws SQLException {
        DatabaseConnection db =new DatabaseConnection();
        System.out.println("Entered verifyLogIn with username: " + name + " and password: " + pass);
        return db.getUser(username.getText(), password.getText());
    }
    public void username(ActionEvent actionEvent) {
    }

    public void password(ActionEvent actionEvent) {
    }


}
