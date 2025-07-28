package com.saferoom.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

public class FriendsController {

    @FXML private TabPane friendsTabPane;
    @FXML private ListView<String> onlineFriendsListView;
    @FXML private ListView<String> allFriendsListView;
    @FXML private ListView<String> pendingFriendsListView;
    @FXML private ListView<String> blockedFriendsListView;

    @FXML
    public void initialize() {
        // Online arkadaşlar
        onlineFriendsListView.getItems().addAll(
                "🟢 Ahmet Çelik - Online",
                "🟢 Zeynep Kaya - Online",
                "🟢 Mehmet Yılmaz - Online",
                "🟡 Ayşe Demir - Away",
                "🟡 Can Özkan - Away"
        );

        // Tüm arkadaşlar
        allFriendsListView.getItems().addAll(
                "🟢 Ahmet Çelik - Online",
                "🟢 Zeynep Kaya - Online", 
                "🟢 Mehmet Yılmaz - Online",
                "🟡 Ayşe Demir - Away",
                "🟡 Can Özkan - Away",
                "⚫ Fatma Şen - Offline",
                "⚫ Ali Kara - Offline",
                "⚫ Sema Beyaz - Offline"
        );

        // Bekleyen istekler
        pendingFriendsListView.getItems().addAll(
                "📤 Outgoing: Emre Güneş",
                "📤 Outgoing: Deniz Mavi",
                "📥 Incoming: Cem Yılmaz",
                "📥 Incoming: Pınar Yeşil"
        );

        // Engellenen kullanıcılar
        blockedFriendsListView.getItems().addAll(
                "🚫 Spam User 1",
                "🚫 Spam User 2"
        );

        // CSS sınıflarını ekle
        onlineFriendsListView.getStyleClass().add("friends-list");
        allFriendsListView.getStyleClass().add("friends-list");
        pendingFriendsListView.getStyleClass().add("friends-list");
        blockedFriendsListView.getStyleClass().add("friends-list");
    }
} 