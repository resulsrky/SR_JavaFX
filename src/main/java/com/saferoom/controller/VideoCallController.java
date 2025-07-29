package com.saferoom.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class VideoCallController {

    @FXML private JFXTextField meetingTitleField;
    @FXML private JFXToggleButton waitingRoomToggle;
    @FXML private JFXToggleButton recordToggle;
    @FXML private JFXToggleButton screenShareToggle;
    @FXML private JFXButton cameraToggleBtn;
    @FXML private JFXButton micToggleBtn;
    @FXML private JFXButton startMeetingBtn;
    @FXML private JFXButton scheduleBtn;
    @FXML private JFXButton joinMeetingBtn;
    @FXML private VBox recentMeetingsContainer;

    private boolean cameraEnabled = true;
    private boolean micEnabled = true;

    @FXML
    public void initialize() {
        setupControlButtons();
        setupActionButtons();
        setupRecentMeetings();
    }

    private void setupControlButtons() {
        // Camera toggle
        cameraToggleBtn.setOnAction(event -> {
            cameraEnabled = !cameraEnabled;
            if (cameraEnabled) {
                cameraToggleBtn.getStyleClass().remove("camera-off");
                cameraToggleBtn.getStyleClass().add("camera-on");
            } else {
                cameraToggleBtn.getStyleClass().remove("camera-on");
                cameraToggleBtn.getStyleClass().add("camera-off");
            }
            System.out.println("Camera: " + (cameraEnabled ? "ON" : "OFF"));
        });

        // Mic toggle
        micToggleBtn.setOnAction(event -> {
            micEnabled = !micEnabled;
            if (micEnabled) {
                micToggleBtn.getStyleClass().remove("mic-off");
                micToggleBtn.getStyleClass().add("mic-on");
            } else {
                micToggleBtn.getStyleClass().remove("mic-on");
                micToggleBtn.getStyleClass().add("mic-off");
            }
            System.out.println("Microphone: " + (micEnabled ? "ON" : "OFF"));
        });
    }

    private void setupActionButtons() {
        startMeetingBtn.setOnAction(event -> {
            String title = meetingTitleField.getText();
            boolean waitingRoom = waitingRoomToggle.isSelected();
            boolean record = recordToggle.isSelected();
            boolean screenShare = screenShareToggle.isSelected();
            
            System.out.println("🎥 Starting Meeting: " + title);
            System.out.println("   Camera: " + (cameraEnabled ? "ON" : "OFF"));
            System.out.println("   Mic: " + (micEnabled ? "ON" : "OFF"));
            System.out.println("   Waiting Room: " + (waitingRoom ? "ENABLED" : "DISABLED"));
            System.out.println("   Recording: " + (record ? "ENABLED" : "DISABLED"));
            System.out.println("   Screen Share: " + (screenShare ? "ENABLED" : "DISABLED"));
            
            // Simulate meeting start
            startMeetingBtn.setText("Meeting Started!");
            startMeetingBtn.getStyleClass().add("meeting-active");
        });

        scheduleBtn.setOnAction(event -> {
            System.out.println("📅 Schedule Meeting for Later clicked");
            // TODO: Open scheduling dialog
        });

        joinMeetingBtn.setOnAction(event -> {
            System.out.println("🚪 Join Existing Meeting clicked");
            // TODO: Open join meeting dialog
        });
    }

    private void setupRecentMeetings() {
        // Add sample recent meetings
        addRecentMeeting("Team Standup", "Yesterday, 2:30 PM", "45 minutes");
        addRecentMeeting("Client Presentation", "March 15, 10:00 AM", "1 hour 20 minutes");
        addRecentMeeting("Design Review", "March 14, 3:15 PM", "30 minutes");
    }

    private void addRecentMeeting(String title, String date, String duration) {
        HBox meetingItem = new HBox();
        meetingItem.setSpacing(16.0);
        meetingItem.setPadding(new Insets(16));
        meetingItem.getStyleClass().add("recent-meeting-item");

        // Meeting icon
        SVGPath meetingIcon = new SVGPath();
        meetingIcon.setContent("M8 5v14l11-7L8 5z");
        meetingIcon.getStyleClass().add("meeting-icon");

        // Meeting info
        VBox meetingInfo = new VBox();
        meetingInfo.setSpacing(4.0);
        
        Label titleLabel = new Label(title);
        titleLabel.getStyleClass().add("meeting-title");
        
        Label dateLabel = new Label(date);
        dateLabel.getStyleClass().add("meeting-date");
        
        Label durationLabel = new Label("Duration: " + duration);
        durationLabel.getStyleClass().add("meeting-duration");
        
        meetingInfo.getChildren().addAll(titleLabel, dateLabel, durationLabel);

        // Rejoin button
        JFXButton rejoinBtn = new JFXButton("Rejoin");
        rejoinBtn.getStyleClass().add("rejoin-button");
        rejoinBtn.setOnAction(event -> {
            System.out.println("🔄 Rejoining meeting: " + title);
        });

        meetingItem.getChildren().addAll(meetingIcon, meetingInfo, rejoinBtn);
        HBox.setHgrow(meetingInfo, javafx.scene.layout.Priority.ALWAYS);

        recentMeetingsContainer.getChildren().add(meetingItem);
    }
} 