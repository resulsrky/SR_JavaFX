package com.saferoom.controller;

import com.saferoom.controller.cards.ActionCardController;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.SVGPath;

public class DashboardController {

    // FXML'deki fx:id'ler ile bu değişkenler birbirine bağlanır.
    // Bu, iç içe FXML'lerin kontrolcülerine erişmemizi sağlar.
    @FXML private ActionCardController newMeetingCardController;
    @FXML private ActionCardController joinRoomCardController;
    @FXML private ActionCardController scheduleRoomCardController;
    @FXML private ActionCardController encryptedFilesCardController;

    @FXML
    private VBox activityContainer;

    @FXML
    public void initialize() {
        // Set up card content
        newMeetingCardController.setData("M12 5v14m-7-7h14", "New Meeting", "Start Instant Room");
        joinRoomCardController.setData("M5 12h14m-7-7 7 7-7 7", "Join Room", "Connect to Room");
        scheduleRoomCardController.setData("M12 6v6l4 2 M12 22A10 10 0 1 1 12 2a10 10 0 0 1 0 20z", "Schedule Room", "Plan Meeting");
        encryptedFilesCardController.setData("M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z M14 2v6h6 M12 18v-6 M10 14h4", "Encrypted Files", "Access File Vault");

        // Set up card click handlers
        newMeetingCardController.setOnAction(e -> handleNewMeeting());
        joinRoomCardController.setOnAction(e -> handleJoinRoom());
        scheduleRoomCardController.setOnAction(e -> handleScheduleRoom());
        encryptedFilesCardController.setOnAction(e -> handleFileVault());

        // Add modern timeline activities
        addTimelineActivity("🚀", "You started a secure room 'Project Alpha'", "2 minutes ago");
        addTimelineActivity("👥", "Sarah Davis joined 'Project Alpha'", "5 minutes ago");
        addTimelineActivity("📁", "You shared 'design_final.zip' in File Vault", "1 hour ago");
        addTimelineActivity("💬", "New message from John Doe", "2 hours ago");
        addTimelineActivity("🔒", "End-to-end encryption enabled", "1 day ago");
    }

    private void handleNewMeeting() {
        System.out.println("Starting new meeting...");
        // Navigate to video call view
        com.saferoom.utils.ViewManager.getInstance().showVideoCallView();
    }

    private void handleJoinRoom() {
        System.out.println("Joining room...");
        // TODO: Show join room dialog
    }

    private void handleScheduleRoom() {
        System.out.println("Scheduling room...");
        // TODO: Show schedule room dialog
    }

    private void handleFileVault() {
        System.out.println("Opening file vault...");
        // Navigate to file vault view
        com.saferoom.utils.ViewManager.getInstance().showFileVaultView();
    }

    private void addTimelineActivity(String icon, String description, String timeAgo) {
        HBox activityItem = new HBox();
        activityItem.getStyleClass().add("timeline-item");
        activityItem.setSpacing(12.0);

        // Icon
        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("timeline-icon");

        // Content
        VBox contentBox = new VBox();
        contentBox.getStyleClass().add("timeline-content");
        contentBox.setSpacing(4.0);

        Label descriptionLabel = new Label(description);
        descriptionLabel.getStyleClass().add("timeline-description");
        descriptionLabel.setWrapText(true);

        Label timeLabel = new Label(timeAgo);
        timeLabel.getStyleClass().add("timeline-time");

        contentBox.getChildren().addAll(descriptionLabel, timeLabel);
        activityItem.getChildren().addAll(iconLabel, contentBox);
        
        activityContainer.getChildren().add(activityItem);
    }
}
