package com.saferoom.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;

public class FileVaultController {

    @FXML private TabPane fileTabPane;
    @FXML private ListView<String> allFilesListView;
    @FXML private ListView<String> recentFilesListView;
    @FXML private ListView<String> sharedFilesListView;
    @FXML private ListView<String> favoriteFilesListView;

    @FXML
    public void initialize() {
        setupFileLists();
    }

    private void setupFileLists() {
        // All Files
        allFilesListView.getItems().addAll(
            "📄 Project_Report.pdf",
            "🖼️ vacation_photo_2024.jpg",
            "🎬 presentation_video.mp4",
            "📄 meeting_notes.docx",
            "🖼️ screenshot_design.png",
            "📦 backup_files.zip",
            "📄 budget_2024.xlsx",
            "🎬 tutorial_recording.mov"
        );

        // Recent Files
        recentFilesListView.getItems().addAll(
            "📄 Project_Report.pdf",
            "🖼️ screenshot_design.png",
            "📄 meeting_notes.docx",
            "🎬 presentation_video.mp4"
        );

        // Shared Files
        sharedFilesListView.getItems().addAll(
            "📄 Project_Report.pdf",
            "🖼️ vacation_photo_2024.jpg",
            "📄 budget_2024.xlsx"
        );

        // Favorite Files
        favoriteFilesListView.getItems().addAll(
            "📄 Project_Report.pdf",
            "📄 meeting_notes.docx",
            "🖼️ screenshot_design.png"
        );

        // Add CSS classes
        allFilesListView.getStyleClass().add("file-list");
        recentFilesListView.getStyleClass().add("file-list");
        sharedFilesListView.getStyleClass().add("file-list");
        favoriteFilesListView.getStyleClass().add("file-list");
    }
} 