package com.example.taskmanagerapp.controllers;

import com.example.taskmanagerapp.utils.ApplicationHandler;
import com.example.taskmanagerapp.utils.enums.SCENE_IDENTIFIER;

public class SceneController {
    public void changeScene(SCENE_IDENTIFIER newScene){
        ApplicationHandler.getInstance().changeScene(newScene);
    }
    public void changeDimensions(double width, double height){
        ApplicationHandler.getInstance().setSceneDimensions(width, height);
    }
    public void closeApplication(){
        ApplicationHandler.getInstance().closeApplication();
    }
}

