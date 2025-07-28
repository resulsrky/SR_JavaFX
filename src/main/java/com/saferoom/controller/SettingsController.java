package com.saferoom.controller;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;

public class SettingsController {

    // Privacy & Security
    @FXML private JFXToggleButton twoFactorToggle;
    @FXML private JFXToggleButton encryptionToggle;
    @FXML private JFXToggleButton onlineStatusToggle;

    // Notifications
    @FXML private JFXToggleButton desktopNotificationsToggle;
    @FXML private JFXToggleButton soundNotificationsToggle;
    @FXML private JFXSlider notificationVolumeSlider;

    // Application
    @FXML private JFXToggleButton autoStartToggle;
    @FXML private JFXToggleButton minimizeToTrayToggle;

    @FXML
    public void initialize() {
        // Toggle butonlarına event listener'lar ekle
        setupToggleListeners();
        setupSliderListeners();
    }

    private void setupToggleListeners() {
        twoFactorToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Two-Factor Authentication: " + (newVal ? "Enabled" : "Disabled"));
        });

        encryptionToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("End-to-End Encryption: " + (newVal ? "Enabled" : "Disabled"));
        });

        onlineStatusToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Show Online Status: " + (newVal ? "Enabled" : "Disabled"));
        });

        desktopNotificationsToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Desktop Notifications: " + (newVal ? "Enabled" : "Disabled"));
        });

        soundNotificationsToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Sound Notifications: " + (newVal ? "Enabled" : "Disabled"));
        });

        autoStartToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Auto-start with System: " + (newVal ? "Enabled" : "Disabled"));
        });

        minimizeToTrayToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Minimize to System Tray: " + (newVal ? "Enabled" : "Disabled"));
        });
    }

    private void setupSliderListeners() {
        notificationVolumeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            System.out.println("Notification Volume: " + Math.round(newVal.doubleValue()) + "%");
        });
    }

    @FXML
    private void clearAllData() {
        System.out.println("Clear All Data clicked");
        // Burada kullanıcıya onay dialog'u gösterilmeli
    }

    @FXML
    private void deleteAccount() {
        System.out.println("Delete Account clicked");
        // Burada kullanıcıya onay dialog'u gösterilmeli
    }
} 