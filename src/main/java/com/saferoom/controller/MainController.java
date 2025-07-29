package com.saferoom.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import com.saferoom.utils.ViewManager;

public class MainController {

    @FXML private BorderPane mainPane;
    @FXML private HBox topBar;
    @FXML private Label viewTitleLabel;
    @FXML private Button dashboardButton;
    @FXML private Button messagesButton;
    @FXML private Button friendsButton;
    @FXML private Button fileVaultButton;
    @FXML private Button settingsButton;

    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    public void initialize() {
        // Initialize ViewManager with mainPane
        ViewManager.getInstance().setMainPane(mainPane);
        
        handleDashboard();

        dashboardButton.setOnAction(event -> handleDashboard());
        messagesButton.setOnAction(event -> handleMessages());
        friendsButton.setOnAction(event -> handleFriends());
        fileVaultButton.setOnAction(event -> handleFileVault());
        settingsButton.setOnAction(event -> handleSettings());
    }

    private void handleDashboard() {
        setActiveButton(dashboardButton);
        viewTitleLabel.setText("Dashboard");
        loadView("DashBoardView.fxml");
    }

    private void handleMessages() {
        setActiveButton(messagesButton);
        viewTitleLabel.setText("Messages");
        loadView("MessagesView.fxml");
    }

    private void handleFriends() {
        setActiveButton(friendsButton);
        viewTitleLabel.setText("Friends");
        loadView("FriendsView.fxml");
    }

    private void handleFileVault() {
        setActiveButton(fileVaultButton);
        viewTitleLabel.setText("File Vault");
        loadView("FileVaultView.fxml");
    }

    private void handleSettings() {
        setActiveButton(settingsButton);
        viewTitleLabel.setText("Settings");
        loadView("SettingsView.fxml");
    }

    private Stage getStage() {
        return (Stage) mainPane.getScene().getWindow();
    }

    private void loadView(String fxmlFile) {
        try {
            Parent view = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/saferoom/view/" + fxmlFile)));
            mainPane.setCenter(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setActiveButton(Button activeButton) {
        dashboardButton.getStyleClass().remove("active");
        messagesButton.getStyleClass().remove("active");
        friendsButton.getStyleClass().remove("active");
        fileVaultButton.getStyleClass().remove("active");
        settingsButton.getStyleClass().remove("active");

        activeButton.getStyleClass().add("active");
    }
}