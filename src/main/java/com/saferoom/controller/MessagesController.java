package com.saferoom.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MessagesController {

    @FXML private ListView<String> chatListView;
    @FXML private ScrollPane messagesScrollPane;
    @FXML private VBox messagesContainer;
    @FXML private TextField messageField;
    @FXML private JFXButton sendButton;

    @FXML
    public void initialize() {
        // Örnek sohbet listesi
        chatListView.getItems().addAll(
                "Ahmet Çelik - Hello! How are you?",
                "Zeynep Kaya - Project meeting at 3 PM",
                "Team SafeRoom - New features released!",
                "Mehmet Yılmaz - File shared successfully",
                "Ayşe Demir - Thanks for the help!"
        );

        // Örnek mesajları ekle
        loadSampleMessages();

        // Send butonu olayı
        sendButton.setOnAction(event -> sendMessage());
        messageField.setOnAction(event -> sendMessage());
    }

    private void loadSampleMessages() {
        // Örnek mesajlar
        addMessage("Ahmet Çelik", "Hey! How's the new SafeRoom update?", false);
        addMessage("You", "It's amazing! Love the new encryption features.", true);
        addMessage("Ahmet Çelik", "Great! Should we test the group chat?", false);
        addMessage("You", "Absolutely! Let me create a room.", true);
        addMessage("Ahmet Çelik", "Perfect! Send me the invite when ready.", false);
    }

    private void addMessage(String sender, String message, boolean isOwnMessage) {
        HBox messageBox = new HBox();
        messageBox.getStyleClass().add("message-box");
        
        if (isOwnMessage) {
            messageBox.getStyleClass().add("own-message");
        } else {
            messageBox.getStyleClass().add("other-message");
        }

        VBox messageContent = new VBox();
        messageContent.getStyleClass().add("message-content");

        if (!isOwnMessage) {
            Label senderLabel = new Label(sender);
            senderLabel.getStyleClass().add("message-sender");
            messageContent.getChildren().add(senderLabel);
        }

        Label messageLabel = new Label(message);
        messageLabel.getStyleClass().add("message-text");
        messageLabel.setWrapText(true);
        messageContent.getChildren().add(messageLabel);

        messageBox.getChildren().add(messageContent);
        messagesContainer.getChildren().add(messageBox);
    }

    private void sendMessage() {
        String message = messageField.getText().trim();
        if (!message.isEmpty()) {
            addMessage("You", message, true);
            messageField.clear();
            
            // Scroll to bottom
            messagesScrollPane.setVvalue(1.0);
        }
    }
} 