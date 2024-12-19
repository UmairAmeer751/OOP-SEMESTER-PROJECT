package com.example.oopproj2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class submission extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Root layout
        StackPane root = new StackPane();
        root.setStyle("-fx-background-color: #333333;");

        // Create the VBox container
        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        // Title Label
        javafx.scene.control.Label titleLabel = new javafx.scene.control.Label("Are you sure to submit this application?");
        titleLabel.setFont(Font.font("Arial", 20));
        titleLabel.setTextFill(Color.WHITE);
        titleLabel.setStyle("-fx-font-weight: bold;");

        // "Yes" Button
        Button yesButton = new Button("Yes");
        styleButton(yesButton);
        yesButton.setOnAction(e -> {
            showAlert(AlertType.INFORMATION, "Submission", "Your application is submitted successfully.");
            new SelectionPage();  // Simulate redirection to Program Selection Page
            primaryStage.close();
        });

        // "No" Button
        Button noButton = new Button("No");
        styleButton(noButton);
        noButton.setOnAction(e -> {
            new MSProgramPage();  // Simulate redirection to MS Program Page
            primaryStage.close();
        });

        // Add the components to the VBox
        vbox.getChildren().addAll(titleLabel, yesButton, noButton);

        // Add VBox to root layout
        root.getChildren().add(vbox);

        // Scene setup
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setTitle("Confirmation");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Helper method to style buttons
    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-weight: bold; -fx-font-size: 16;");
        button.setMinWidth(120);
        button.setMinHeight(40);
    }

    // Show alert dialog
    private void showAlert(AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Placeholder for "SelectionPage"
    class SelectionPage {
        public SelectionPage() {
            showAlert(AlertType.INFORMATION, "Navigation", "Navigated to Program Selection Page.");
        }
    }

    // Placeholder for "MSProgramPage"
    class MSProgramPage {
        public MSProgramPage() {
            showAlert(AlertType.INFORMATION, "Navigation", "Navigated to MS Program Page.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
