package com.example.oopproj2;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;
public class Bs extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("BS Program");

        // Main container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #333333;"); // Charcoal background color

        // Title section
        Label titleLabel = new Label("Select Your Program");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        BorderPane.setMargin(titleLabel, new Insets(20));
        root.setTop(titleLabel);

        // Center layout for program buttons
        VBox centerBox = new VBox(30); // Vertical spacing of 30px
        centerBox.setAlignment(Pos.CENTER);

        // Create rows for buttons
        HBox row1 = new HBox(20); // Horizontal spacing of 20px
        row1.setAlignment(Pos.CENTER);
        Button csButton = createButton("Computer Science", e -> openSchoolingInfoPage(primaryStage));
        csButton.setOnMouseEntered(e->csButton.setStyle("-fx-background-colour: white -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));
        csButton.setOnMouseExited(e->csButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));


        Button seButton = createButton("Software Engineering", e -> openSchoolingInfoPage(primaryStage));
        seButton.setOnMouseEntered(e->seButton.setStyle("-fx-background-colour: white -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));
        seButton.setOnMouseExited(e->seButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));
        row1.getChildren().addAll(csButton, seButton);

        HBox row2 = new HBox(20); // Horizontal spacing of 20px
        row2.setAlignment(Pos.CENTER);
        Button eeButton = createButton("Electrical Engineering", e -> openSchoolingInfoPage(primaryStage));
        eeButton.setOnMouseEntered(e->eeButton.setStyle("-fx-background-colour: white -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));
        eeButton.setOnMouseExited(e->eeButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));

        Button meButton = createButton("Mechanical Engineering", e -> openSchoolingInfoPage(primaryStage));
        meButton.setOnMouseEntered(e->meButton.setStyle("-fx-background-colour: white -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));
        meButton.setOnMouseExited(e->meButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px"));
        row2.getChildren().addAll(eeButton, meButton);

        // Add rows to the center box
        centerBox.getChildren().addAll(row1, row2);

        // Navigation Buttons
        HBox navigationBox = new HBox(20);
        navigationBox.setAlignment(Pos.CENTER);
        navigationBox.setPadding(new Insets(20));
        Button backButton = createButton("Back", e -> {
            new Selection().start(new Stage());
            primaryStage.close();
        });
        backButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 16px;");
        backButton.setOnMouseEntered(e-> backButton.setStyle("-fx-background-color: red; -fx-text-fill: black; -fx-font-size: 16px;"));
        backButton.setOnMouseExited(e-> backButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 16px;"));

        navigationBox.getChildren().addAll(backButton);

        // Add all sections to root
        root.setCenter(centerBox);
        root.setBottom(navigationBox);

        // Scene setup
        Scene scene = new Scene(root, 1600, 1000);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Keep window maximized
        primaryStage.show();
    }

    private Button createButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px;");
        button.setPrefWidth(250); // Ensure consistent button width
        button.setPrefHeight(50); // Ensure consistent button height
        button.setOnAction(action);
        return button;
    }

    private void openSchoolingInfoPage(Stage primaryStage) {
        try {
            new SchoolingInfoPage().start(new Stage()); // Open the SchoolingInfoPage
            primaryStage.close(); // Close the current stage
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
