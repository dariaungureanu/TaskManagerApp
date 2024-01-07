module com.example.taskmanagerapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires java.sql;

    opens com.example.taskmanagerapp to javafx.fxml;
    exports com.example.taskmanagerapp;
    exports com.example.taskmanagerapp.utils.enums;
    opens com.example.taskmanagerapp.utils.enums to javafx.fxml;
    exports com.example.taskmanagerapp.controllers;
    opens com.example.taskmanagerapp.controllers to javafx.fxml;
    exports com.example.taskmanagerapp.utils;
    opens com.example.taskmanagerapp.utils to javafx.fxml;


}