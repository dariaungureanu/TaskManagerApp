package com.example.taskmanagerapp.utils.enums;

public enum SCENE_IDENTIFIER {
    LOGIN("make_account-view.fxml"),
    SIGNUP("signin-view.fxml"),
    CREATE_FIRST_PROJECT("create-project.fxml"),
    LOGGEDIN("login-view.fxml"),
    ADMIN("admin-view.fxml"),
    CREATE_TASKS("create-tasks.fxml"),
    VIEW_TASKS("view-tasks.fxml"),
    VIEW_REMAINTASKS("view-remaintasks.fxml"),
    ADD_TAG("add-tag-view.fxml"),
    ADD_COMMENT("add-comment-view.fxml"),
    UPDATE_TASKS("update-tasks.fxml");

    public final String label;
    SCENE_IDENTIFIER(String label){this.label=label;}

}
