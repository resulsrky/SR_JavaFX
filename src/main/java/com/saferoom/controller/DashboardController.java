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
        // Kartların içeriğini ayarla
        newMeetingCardController.setData("M12 5v14m-7-7h14", "New Meeting", "Instant Secure Room");
        joinRoomCardController.setData("M5 12h14m-7-7 7 7-7 7", "Join Room", "Connect to Tunnel");
        scheduleRoomCardController.setData("M12 6v6l4 2 M12 22A10 10 0 1 1 12 2a10 10 0 0 1 0 20z", "Schedule Room", "Programmatic Sync");
        encryptedFilesCardController.setData("M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z M14 2v6h6 M12 18v-6 M10 14h4", "Encrypted Files", "File Vault");

        // Modern timeline aktivitelerini ekle
        addTimelineActivity("🚀", "You started a secure room 'Project Phoenix'", "2 minutes ago");
        addTimelineActivity("👥", "Zeynep Kaya joined 'Project Phoenix'", "5 minutes ago");
        addTimelineActivity("📁", "You shared 'design_final_v3.zip' in File Vault", "1 hour ago");
        addTimelineActivity("💬", "New message from Ahmet Çelik", "2 hours ago");
        addTimelineActivity("🔒", "End-to-end encryption enabled", "1 day ago");
    }

    private void addTimelineActivity(String icon, String description, String timeAgo) {
        HBox activityItem = new HBox();
        activityItem.getStyleClass().add("timeline-item");
        activityItem.setSpacing(12.0);

        // İkon
        Label iconLabel = new Label(icon);
        iconLabel.getStyleClass().add("timeline-icon");

        // İçerik
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
