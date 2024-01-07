package com.example.taskmanagerapp.utils;

import com.example.taskmanagerapp.ApplicationRun;
import com.example.taskmanagerapp.controllers.SceneController;
import com.example.taskmanagerapp.controllers.ViewTasksController;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class ApplicationHandler extends Application {
    private final HashMap<SCENE_IDENTIFIER, Pane> views = new HashMap<>();
    private Stage stage;

    public ApplicationHandler() {}


    @Override
    public void start(Stage stage){
        this.initializeViews();

        this.stage=stage;
        this.stage.setTitle(Environment.APP_TITLE);
        this.stage.setFullScreen(Environment.IS_FULLSCREEN);
        this.stage.setTitle("TaskMaster Application");

        Scene scene = new Scene(this.views.get(SCENE_IDENTIFIER.LOGIN), 550, 400);
        this.stage.setScene(scene);

        this.stage.show();
    }

    public void setSceneDimensions(double width, double height) {
        this.stage.getScene().getWindow().setWidth(width);
        this.stage.getScene().getWindow().setHeight(height);
    }
    public void changeScene(SCENE_IDENTIFIER newScene){
        this.stage.getScene().setRoot(views.get(newScene));
    }


    public void initializeViews() {
        try {
            for (SCENE_IDENTIFIER value : SCENE_IDENTIFIER.values()) {
                this.views.put(value, FXMLLoader.load(Objects.requireNonNull(ApplicationRun.class.getResource(value.label))));
                System.out.println(value.label + " loaded successfully");
            }
        } catch (IOException | NullPointerException exception) {
            System.err.print("Could not initialize views. Please check the resources folder.");
            this.closeApplication();
        }
    }

    public void closeApplication() {
        Platform.exit();
        System.exit(0);
    }

    public static ApplicationHandler _instance = null;

    public static ApplicationHandler getInstance() {
        if(ApplicationHandler._instance == null){
            ApplicationHandler._instance = new ApplicationHandler();
        }

        return ApplicationHandler._instance;
    }


}