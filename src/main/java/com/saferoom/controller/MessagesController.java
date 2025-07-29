package com.saferoom.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MessagesController {

    @FXML private VBox chatListContainer;
    @FXML private ScrollPane messagesScrollPane;
    @FXML private VBox messagesContainer;
    @FXML private TextField messageField;
    @FXML private JFXButton sendButton;

    @FXML
    public void initialize() {
        setupEventHandlers();
        loadSampleChats();
        loadSampleMessages();
    }

    private void setupEventHandlers() {
        sendButton.setOnAction(event -> sendMessage());
        messageField.setOnAction(event -> sendMessage());
    }

    private void loadSampleChats() {
        // Sample chat data
        addChatItem("John Doe", "JD", "Hey! How are you?", "14:23", 2, true);
        addChatItem("Sarah Wilson", "SW", "Thanks for the help today", "13:45", 0, false);
        addChatItem("Project Team", "PT", "Alice: Meeting at 3 PM", "12:30", 5, true);
        addChatItem("Mike Johnson", "MJ", "See you tomorrow", "11:15", 0, false);
        addChatItem("Emma Davis", "ED", "Great work on the presentation!", "10:45", 1, true);
    }

    private void addChatItem(String name, String initials, String lastMessage, String time, int unreadCount, boolean isOnline) {
        HBox chatItem = new HBox();
        chatItem.setAlignment(Pos.CENTER_LEFT);
        chatItem.setSpacing(14);
        chatItem.getStyleClass().addAll("chat-item");

        // İlk öğe aktif olsun
        if (chatListContainer.getChildren().isEmpty()) {
            chatItem.getStyleClass().add("active");
        }

        // Avatar konteyneir
        StackPane avatarContainer = new StackPane();
        avatarContainer.getStyleClass().add("chat-avatar-container");

        Label avatar = new Label(initials);
        avatar.getStyleClass().add("chat-avatar");

        Circle statusIndicator = new Circle(6);
        statusIndicator.setFill(javafx.scene.paint.Color.web(isOnline ? "#7c8f61" : "#666666"));
        statusIndicator.getStyleClass().addAll("chat-status-indicator", isOnline ? "online" : "offline");

        avatarContainer.getChildren().addAll(avatar, statusIndicator);

        // Chat bilgileri
        VBox chatInfo = new VBox();
        chatInfo.setSpacing(4);
        HBox.setHgrow(chatInfo, javafx.scene.layout.Priority.ALWAYS);

        // İsim ve zaman
        HBox nameTimeRow = new HBox();
        nameTimeRow.setAlignment(Pos.CENTER_LEFT);
        nameTimeRow.setSpacing(8);

        Label nameLabel = new Label(name);
        nameLabel.getStyleClass().add("chat-name");

        Region spacer = new Region();
        HBox.setHgrow(spacer, javafx.scene.layout.Priority.ALWAYS);

        Label timeLabel = new Label(time);
        timeLabel.getStyleClass().add("chat-time");

        nameTimeRow.getChildren().addAll(nameLabel, spacer, timeLabel);

        // Mesaj ve okunmamış sayısı
        HBox messageUnreadRow = new HBox();
        messageUnreadRow.setAlignment(Pos.CENTER_LEFT);
        messageUnreadRow.setSpacing(8);

        Label lastMessageLabel = new Label(lastMessage);
        lastMessageLabel.getStyleClass().add("chat-last-message");

        Region spacer2 = new Region();
        HBox.setHgrow(spacer2, javafx.scene.layout.Priority.ALWAYS);

        messageUnreadRow.getChildren().addAll(lastMessageLabel, spacer2);

        if (unreadCount > 0) {
            Label unreadLabel = new Label(String.valueOf(unreadCount));
            unreadLabel.getStyleClass().add("unread-count");
            messageUnreadRow.getChildren().add(unreadLabel);
        }

        chatInfo.getChildren().addAll(nameTimeRow, messageUnreadRow);

        chatItem.getChildren().addAll(avatarContainer, chatInfo);
        chatListContainer.getChildren().add(chatItem);

        // Click event
        chatItem.setOnMouseClicked(event -> selectChat(chatItem));
    }

    private void selectChat(HBox selectedItem) {
        // Tüm chat item'lardan active class'ını kaldır
        for (var node : chatListContainer.getChildren()) {
            node.getStyleClass().remove("active");
        }
        // Seçilen item'a active class'ını ekle
        selectedItem.getStyleClass().add("active");
    }

    private void loadSampleMessages() {
        // Sample message groups
        addMessageGroup("John Doe", "JD", "14:20", false, new String[]{
            "Hey! How are you doing?",
            "Haven't seen you in a while"
        });
        
        addMessageGroup("You", "Y", "14:21", true, new String[]{
            "I'm good, thanks! How are you?",
            "Yeah, been quite busy lately"
        });
        
        addMessageGroup("John Doe", "JD", "14:22", false, new String[]{
            "Same here, work has been crazy",
            "We should catch up soon"
        });
        
        addMessageGroup("You", "Y", "14:23", true, new String[]{
            "Definitely! How about this weekend?"
        });
    }

    private void addMessageGroup(String senderName, String senderInitials, String timestamp, boolean isOwn, String[] messages) {
        VBox messageGroup = new VBox();
        messageGroup.setSpacing(8);
        messageGroup.getStyleClass().addAll("message-group", isOwn ? "own" : "other");

        // Message group header
        HBox headerRow = new HBox();
        headerRow.setSpacing(12);
        headerRow.setAlignment(isOwn ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

        if (!isOwn) {
            Label avatar = new Label(senderInitials);
            avatar.getStyleClass().addAll("message-avatar", "small");
            headerRow.getChildren().add(avatar);

            Label senderLabel = new Label(senderName);
            senderLabel.getStyleClass().add("message-sender-name");
            headerRow.getChildren().add(senderLabel);
        }

        Label timestampLabel = new Label(timestamp);
        timestampLabel.getStyleClass().add("message-timestamp");
        headerRow.getChildren().add(timestampLabel);

        if (isOwn) {
            Label ownSenderLabel = new Label(senderName);
            ownSenderLabel.getStyleClass().addAll("message-sender-name", "own");
            headerRow.getChildren().add(ownSenderLabel);
        }

        messageGroup.getChildren().add(headerRow);

        // Message bubbles
        VBox messageBubbles = new VBox();
        messageBubbles.setSpacing(4);
        messageBubbles.getStyleClass().addAll("message-bubbles", isOwn ? "own" : "");

        for (String messageText : messages) {
            HBox bubbleRow = new HBox();
            bubbleRow.setAlignment(isOwn ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

            if (!isOwn) {
                Region leftSpacer = new Region();
                leftSpacer.setMinWidth(44);
                bubbleRow.getChildren().add(leftSpacer);
            }

            VBox messageBubble = new VBox();
            messageBubble.getStyleClass().addAll("message-bubble", isOwn ? "own" : "other");

            Label messageLabel = new Label(messageText);
            messageLabel.getStyleClass().addAll("message-text", isOwn ? "own" : "");
            messageLabel.setWrapText(true);

            messageBubble.getChildren().add(messageLabel);

            // Add status indicator for our own messages (only for the last message)
            if (isOwn && messageText.equals(messages[messages.length - 1])) {
                HBox statusRow = new HBox();
                statusRow.setAlignment(Pos.CENTER_RIGHT);
                statusRow.setSpacing(4);
                statusRow.getStyleClass().add("message-status");

                SVGPath deliveredIcon = new SVGPath();
                deliveredIcon.setContent("M20.285 2l-11.285 11.567-5.286-5.011-3.714 3.716 9 8.728 15-15.285z");
                deliveredIcon.getStyleClass().addAll("message-status-icon", "delivered");

                SVGPath readIcon = new SVGPath();
                readIcon.setContent("M20.285 2l-11.285 11.567-5.286-5.011-3.714 3.716 9 8.728 15-15.285z");
                readIcon.getStyleClass().addAll("message-status-icon", "read");

                statusRow.getChildren().addAll(deliveredIcon, readIcon);
                messageBubble.getChildren().add(statusRow);
            }

            bubbleRow.getChildren().add(messageBubble);

            if (isOwn) {
                Region rightSpacer = new Region();
                rightSpacer.setMinWidth(44);
                bubbleRow.getChildren().add(rightSpacer);
            }

            messageBubbles.getChildren().add(bubbleRow);
        }

        messageGroup.getChildren().add(messageBubbles);
        messagesContainer.getChildren().add(messageGroup);
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            // Mevcut zamanı al
            String currentTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));

            // Add new message
            addMessageGroup("You", "Y", currentTime, true, new String[]{message});

            messageField.clear();

            // Scroll'u en alta kaydır
            messagesScrollPane.applyCss();
            messagesScrollPane.layout();
            messagesScrollPane.setVvalue(1.0);
        }
    }
} 