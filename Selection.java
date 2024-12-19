package com.example.oopproj2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Selection extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Program Selection");

        // Layout configuration
        VBox layout = new VBox(30); // Vertical spacing of 30px
        layout.setAlignment(Pos.CENTER);
        layout.setStyle("-fx-background-color: #333333;"); // Original charcoal background

        // Title Label
        Label titleLabel = new Label("Select Your Program");
        titleLabel.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: white;");

        // Buttons with the original theme
        Button bsButton = new Button("BS Program");
        bsButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px;");
        bsButton.setOnMouseEntered(e->bsButton.setStyle("-fx-background-color: white; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px;"));
        bsButton.setOnMouseExited(e->bsButton.setStyle("-fx-background-color: gray; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px;"));
        bsButton.setOnAction(e -> {
            primaryStage.close();
            new Bs().start(new Stage()); // Navigates to the BS program page
        });

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px;");
        backButton.setOnMouseEntered(e->backButton.setStyle("-fx-background-color: red; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px;"));
        backButton.setOnMouseExited(e->backButton.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 18px; -fx-padding: 10px 20px;"));
        backButton.setOnAction(e -> {
            primaryStage.close();
            new LoginPage().start(new Stage()); // Navigates to the login page
        });

        // Adding content to layout
        layout.getChildren().addAll(titleLabel, bsButton, backButton);

        // Scene setup
        Scene scene = new Scene(layout, 1600, 1000); // Maintain the original size
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true); // Keep window maximized
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
