package com.saferoom.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class ViewManager {
    private static ViewManager instance;
    private BorderPane mainPane;
    
    private ViewManager() {
        // Private constructor for singleton
    }
    
    public static ViewManager getInstance() {
        if (instance == null) {
            instance = new ViewManager();
        }
        return instance;
    }
    
    public void setMainPane(BorderPane mainPane) {
        this.mainPane = mainPane;
    }
    
    public void showVideoCallView() {
        loadView("VideoCallView.fxml");
    }
    
    public void showFileVaultView() {
        loadView("FileVaultView.fxml");
    }
    
    private void loadView(String fxmlFile) {
        try {
            if (mainPane != null) {
                Parent view = FXMLLoader.load(getClass().getResource("/com/saferoom/view/" + fxmlFile));
                mainPane.setCenter(view);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
