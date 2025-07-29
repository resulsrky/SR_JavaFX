package com.saferoom.controller.cards;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class ActionCardController {

    @FXML
    private VBox rootCard;

    @FXML
    private SVGPath iconPath;

    @FXML
    private Label titleLabel;

    @FXML
    private Label descriptionLabel;

    private EventHandler<ActionEvent> actionHandler;

    @FXML
    public void initialize() {
        // Make the card clickable
        rootCard.setOnMouseClicked(event -> {
            if (actionHandler != null) {
                actionHandler.handle(new ActionEvent(event.getSource(), event.getTarget()));
            }
        });
    }

    /**
     * This method is called by DashboardController to set up
     * each card's icon, title, and description.
     * @param svgContent SVG icon path data
     * @param title Card title
     * @param description Card description
     */
    public void setData(String svgContent, String title, String description) {
        iconPath.setContent(svgContent);
        titleLabel.setText(title);
        descriptionLabel.setText(description);
    }

    /**
     * Set the action handler for when the card is clicked
     * @param handler The action event handler
     */
    public void setOnAction(EventHandler<ActionEvent> handler) {
        this.actionHandler = handler;
    }
}
