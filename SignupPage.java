package com.example.oopproj2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class SignupPage extends Application {

    private Stage loginStage;

    public SignupPage(Stage loginStage) {
        this.loginStage = loginStage; // Reference to the login stage for navigation
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Sign Up Page");

        // Main container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #333333;");

        // Centered form layout
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(15);
        gridPane.setVgap(10);

        // Title
        Label titleLabel = new Label("Signup for an Account");
        titleLabel.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: white;");
        GridPane.setColumnSpan(titleLabel, 2);
        gridPane.add(titleLabel, 0, 0);

        // Name field
        Label nameLabel = new Label("Name:");
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        gridPane.add(nameLabel, 0, 1);
        TextField nameField = new TextField();
        styleTextField(nameField);
        gridPane.add(nameField, 1, 1);

        // Email field
        Label emailLabel = new Label("Email:");
        emailLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        gridPane.add(emailLabel, 0, 2);
        TextField emailField = new TextField();
        styleTextField(emailField);
        gridPane.add(emailField, 1, 2);

        // Password field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        gridPane.add(passwordLabel, 0, 3);
        PasswordField passwordField = new PasswordField();
        styleTextField(passwordField);
        gridPane.add(passwordField, 1, 3);

        // Date of Birth field
        Label dobLabel = new Label("Date of Birth:");
        dobLabel.setStyle("-fx-text-fill: white; -fx-font-size: 18px;");
        gridPane.add(dobLabel, 0, 4);
        HBox dobBox = new HBox(10);
        dobBox.setAlignment(Pos.CENTER_LEFT);
        ComboBox<String> dayComboBox = new ComboBox<>();
        ComboBox<String> monthComboBox = new ComboBox<>();
        ComboBox<String> yearComboBox = new ComboBox<>();

        for (int i = 1; i <= 31; i++) dayComboBox.getItems().add(String.valueOf(i));
        for (int i = 1; i <= 12; i++) monthComboBox.getItems().add(String.valueOf(i));
        for (int i = 2000; i <= 2026; i++) yearComboBox.getItems().add(String.valueOf(i));

        styleComboBox(dayComboBox);
        styleComboBox(monthComboBox);
        styleComboBox(yearComboBox);

        dobBox.getChildren().addAll(dayComboBox, monthComboBox, yearComboBox);
        gridPane.add(dobBox, 1, 4);

        // Buttons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button signUpButton = new Button("Sign Up");
        Button backButton = new Button("Back");
        styleButton(signUpButton);
        styleButton(backButton);

        // Sign up button action
        signUpButton.setOnAction(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();
            String day = dayComboBox.getValue();
            String month = monthComboBox.getValue();
            String year = yearComboBox.getValue();

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || day == null || month == null || year == null) {
                showAlert(Alert.AlertType.ERROR, "Error", "Please fill all the fields!");
            } else {
                try {
                    database.executeWriteQuery("INSERT INTO students (studentEmail, studentPassword) VALUES ('" + email + "', '" + password + "')");
                    showAlert(Alert.AlertType.INFORMATION, "Success", "Student Successfully Created.");
                    primaryStage.close();
                    loginStage.show(); // Show the login stage after signup
                } catch (Exception ex) {
                    showAlert(Alert.AlertType.ERROR, "Error", ex.getMessage());
                }
            }
        });

        // Back button action
        backButton.setOnAction(e -> {
            primaryStage.close();
            loginStage.show(); // Show the login stage when "Back" is clicked
        });

        buttonBox.getChildren().addAll(signUpButton, backButton);
        gridPane.add(buttonBox, 0, 5, 2, 1);

        root.setCenter(gridPane);
        Scene scene = new Scene(root, 1600, 1000);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    private void styleTextField(TextField textField) {
        textField.setStyle("-fx-font-size: 16px; -fx-background-color: #3c3c3c; -fx-text-fill: white; -fx-padding: 5px;");
        textField.setPrefHeight(40);
    }

    private void styleComboBox(ComboBox<String> comboBox) {
        comboBox.setStyle("-fx-font-size: 16px; -fx-background-color: #ffffff; -fx-text-fill: white;");

        comboBox.setOnAction(e -> {
            comboBox.setStyle("-fx-background-color: #ffffff; -fx-text-fill: white;"); // Set text color to white when selected
        });
    }

    private void styleButton(Button button) {
        button.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;");
        button.setOnMouseEntered(e->button.setStyle("-fx-background-color: red; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;"));
        button.setOnMouseExited(e->button.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-font-size: 16px; -fx-padding: 10px 20px;"));
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
