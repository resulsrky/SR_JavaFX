package com.saferoom.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

public class FriendsController {

    @FXML private TabPane friendsTabPane;
    @FXML private VBox onlineFriendsContainer;
    @FXML private VBox allFriendsContainer;
    @FXML private VBox pendingFriendsContainer;
    @FXML private VBox blockedFriendsContainer;
    @FXML private JFXButton addFriendButton;

    @FXML
    public void initialize() {
        setupEventHandlers();
        loadSampleFriends();
    }

    private void setupEventHandlers() {
        addFriendButton.setOnAction(event -> handleAddFriend());
    }

    private void loadSampleFriends() {
        // Sample data for online friends
        addOnlineFriend("John Doe", "JD", "Online - Active 2 mins ago", "🎮 Playing VALORANT");
        addOnlineFriend("Jane Smith", "JS", "Online - Active now", "💻 Writing code");
        addOnlineFriend("Mike Johnson", "MJ", "Online - Active 5 mins ago", "🎵 Listening to Spotify");

        // All friends (online + offline)
        copyOnlineToAll();
        addOfflineFriend("Sarah Davis", "SD", "Last seen 12 minutes ago", "");
        addOfflineFriend("Tom Wilson", "TW", "Last seen 2 hours ago", "");

        // Pending requests
        addPendingFriend("Emily Brown", "EB", "Friend request sent", "3 days ago");
        addPendingFriend("Chris Lee", "CL", "Received friend request", "1 week ago");
    }

    private void addOnlineFriend(String name, String initials, String status, String activity) {
        HBox friendCard = createFriendCard(name, initials, status, activity, true);
        onlineFriendsContainer.getChildren().add(friendCard);
    }

    private void addOfflineFriend(String name, String initials, String status, String activity) {
        HBox friendCard = createFriendCard(name, initials, status, activity, false);
        allFriendsContainer.getChildren().add(friendCard);
    }

    private void addPendingFriend(String name, String initials, String status, String time) {
        HBox friendCard = createPendingFriendCard(name, initials, status, time);
        pendingFriendsContainer.getChildren().add(friendCard);
    }

    private void copyOnlineToAll() {
        for (var child : onlineFriendsContainer.getChildren()) {
            if (child instanceof HBox) {
                HBox originalCard = (HBox) child;
                HBox clonedCard = createSimpleFriendCard(originalCard);
                allFriendsContainer.getChildren().add(clonedCard);
            }
        }
    }

    private HBox createSimpleFriendCard(HBox original) {
        // Basit bir klon oluştur - gerçek uygulamada daha detaylı olmalı
        HBox clone = new HBox();
        clone.setAlignment(Pos.CENTER_LEFT);
        clone.setSpacing(16);
        clone.getStyleClass().addAll("friend-card", "online");
        
        Label cloneLabel = new Label("Cloned Friend");
        cloneLabel.getStyleClass().add("friend-name");
        clone.getChildren().add(cloneLabel);
        
        return clone;
    }

    private HBox createFriendCard(String name, String initials, String status, String activity, boolean isOnline) {
        HBox friendCard = new HBox();
        friendCard.setAlignment(Pos.CENTER_LEFT);
        friendCard.setSpacing(16);
        friendCard.getStyleClass().addAll("friend-card", isOnline ? "online" : "offline");

        // Avatar ve durum göstergesi
        VBox avatarContainer = new VBox();
        avatarContainer.setAlignment(Pos.CENTER);
        avatarContainer.setSpacing(4);

        Label avatar = new Label(initials);
        avatar.getStyleClass().addAll("friend-avatar", isOnline ? "online" : "offline");

        if (isOnline) {
            Circle statusIndicator = new Circle(4);
            statusIndicator.setFill(javafx.scene.paint.Color.web("#7c8f61"));
            avatarContainer.getChildren().addAll(avatar, statusIndicator);
        } else {
            avatarContainer.getChildren().add(avatar);
        }

        // Arkadaş bilgileri
        VBox infoContainer = new VBox();
        infoContainer.setSpacing(4);
        HBox.setHgrow(infoContainer, javafx.scene.layout.Priority.ALWAYS);

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("friend-name");

        Label statusLabel = new Label(status);
        statusLabel.getStyleClass().addAll("friend-status", isOnline ? "online" : "offline");

        infoContainer.getChildren().addAll(nameLabel, statusLabel);

        if (!activity.isEmpty()) {
            Label activityLabel = new Label(activity);
            activityLabel.getStyleClass().add("friend-game");
            infoContainer.getChildren().add(activityLabel);
        }

        // Aksiyon butonları
        HBox actionButtons = new HBox();
        actionButtons.setSpacing(8);

        JFXButton messageButton = createActionButton("message");
        JFXButton callButton = createActionButton("call");

        actionButtons.getChildren().addAll(messageButton, callButton);

        friendCard.getChildren().addAll(avatarContainer, infoContainer, actionButtons);
        return friendCard;
    }

    private HBox createPendingFriendCard(String name, String initials, String status, String time) {
        HBox friendCard = new HBox();
        friendCard.setAlignment(Pos.CENTER_LEFT);
        friendCard.setSpacing(16);
        friendCard.getStyleClass().add("friend-card");

        // Avatar
        Label avatar = new Label(initials);
        avatar.getStyleClass().add("friend-avatar");

        // Bilgiler
        VBox infoContainer = new VBox();
        infoContainer.setSpacing(4);
        HBox.setHgrow(infoContainer, javafx.scene.layout.Priority.ALWAYS);

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("friend-name");

        Label statusLabel = new Label(status);
        statusLabel.getStyleClass().add("friend-status");

        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("friend-game");

        infoContainer.getChildren().addAll(nameLabel, statusLabel, timeLabel);

        // Aksiyon butonları
        HBox actionButtons = new HBox();
        actionButtons.setSpacing(8);

        JFXButton acceptButton = new JFXButton("Accept");
        acceptButton.getStyleClass().add("action-button");

        JFXButton rejectButton = new JFXButton("Reject");
        rejectButton.getStyleClass().add("secondary-button");

        actionButtons.getChildren().addAll(acceptButton, rejectButton);

        friendCard.getChildren().addAll(avatar, infoContainer, actionButtons);
        return friendCard;
    }

    private JFXButton createActionButton(String type) {
        JFXButton button = new JFXButton();
        button.getStyleClass().addAll("friend-action-button", type);

        SVGPath icon = new SVGPath();
        if ("message".equals(type)) {
            icon.setContent("M8 12h.01M12 12h.01M16 12h.01M21 12c0 4.418-4.03 8-9 8a9.863 9.863 0 01-4.255-.949L3 20l1.395-3.72C3.512 15.042 3 13.574 3 12c0-4.418 4.03-8 9-8s9 3.582 9 8z");
        } else if ("call".equals(type)) {
            icon.setContent("M3 5a2 2 0 012-2h3.28a1 1 0 01.948.684l1.498 4.493a1 1 0 01-.502 1.21l-2.257 1.13a11.042 11.042 0 005.516 5.516l1.13-2.257a1 1 0 011.21-.502l4.493 1.498a1 1 0 01.684.949V19a2 2 0 01-2 2h-1C9.716 21 3 14.284 3 6V5z");
        }
        icon.getStyleClass().add("friend-action-icon");
        button.setGraphic(icon);

        return button;
    }

    private void handleAddFriend() {
        System.out.println("Arkadaş ekleme diyalogu açılıyor...");
        // TODO: Arkadaş ekleme diyalogu implementasyonu
    }
}