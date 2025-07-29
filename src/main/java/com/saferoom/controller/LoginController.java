package com.saferoom.controller;

import com.saferoom.utils.ViewManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.Objects;

/**
 * Controller for LoginView.fxml.
 * Manages user authentication and UI interactions.
 */
public class LoginController {

    @FXML
    private VBox rootPane;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberMe;

    @FXML
    private Hyperlink forgotPasswordLink;

    @FXML
    private Button signInButton;

    @FXML
    private Hyperlink signUpLink;

    /**
     * Called by JavaFX when FXML is loaded.
     * Set up event handlers and initial configurations.
     */
    @FXML
    public void initialize() {
        // Set up event handlers
        signInButton.setOnAction(event -> handleSignIn());
        signUpLink.setOnAction(event -> handleSignUp());
        forgotPasswordLink.setOnAction(event -> handleForgotPassword());
        
        // Enable sign in on Enter key
        passwordField.setOnAction(event -> handleSignIn());
    }

    /**
     * Handle "Sign In" button click.
     */
    private void handleSignIn() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        boolean remember = rememberMe.isSelected();

        System.out.println("Sign in attempt. Username: " + username + ", Remember me: " + remember);

        // TODO: Implement actual authentication logic with backend
        // For now, we'll proceed directly to the main view

        try {
            // Get current stage from rootPane
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            
            // Load MainView.fxml
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/com/saferoom/view/MainView.fxml")));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/com/saferoom/styles/styles.css")).toExternalForm());
            
            currentStage.setScene(scene);
            currentStage.centerOnScreen();
            
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load main view");
        }
    }

    /**
     * Handle "Sign Up" link click.
     */
    private void handleSignUp() {
        System.out.println("Navigating to registration screen...");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sign Up");
        alert.setHeaderText("Registration");
        alert.setContentText("Registration feature will be implemented soon!");
        alert.showAndWait();
    }

    /**
     * Handle "Forgot password?" link click.
     */
    private void handleForgotPassword() {
        System.out.println("Forgot password clicked...");
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Password Recovery");
        alert.setHeaderText("Forgot Password");
        alert.setContentText("Password recovery feature will be implemented soon!");
        alert.showAndWait();
    }

    /**
     * Show error dialog
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
