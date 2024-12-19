package com.example.oopproj2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Log in Page");
        primaryStage.setMaximized(true);

        // Main container
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #333333;");

        // Center layout for login form
        VBox formBox = new VBox(20);
        formBox.setPadding(new Insets(50));
        formBox.setAlignment(Pos.CENTER);

        // Title Label
        Label titleLabel = new Label("Login to Your Account");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        titleLabel.setTextFill(Color.WHITE);

        // Email field
        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        emailLabel.setTextFill(Color.WHITE);

        TextField emailField = new TextField();
        styleTextField(emailField);

        // Password field
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        passwordLabel.setTextFill(Color.WHITE);

        PasswordField passwordField = new PasswordField();
        styleTextField(passwordField);

        // Login button
        Button loginButton = new Button("Login");
        styleButton(loginButton);
        loginButton.setOnAction(e -> {
            String email = emailField.getText().trim();
            String password = passwordField.getText().trim();

            // Validate inputs
            if (email.isEmpty() || password.isEmpty()) {
                showAlert("Error", "Please enter both email and password!", Alert.AlertType.ERROR);
            } else {
                try {
                    // Use the PreparedStatement method from the database class
                    ResultSet rs = database.executePreparedQuery(email, password);

                    if (rs != null && rs.next()) { // User found
                        showAlert("Success", "Login Successful.", Alert.AlertType.INFORMATION);
                        primaryStage.close();
                        new Selection().start(new Stage()); // Navigate to the Selection page
                    } else { // User not found
                        showAlert("Error", "Invalid Credentials.", Alert.AlertType.WARNING);
                    }

                } catch (RuntimeException | SQLException error) {
                    showAlert("Error", "Database Error: " + error.getMessage(), Alert.AlertType.ERROR);
                }
            }
        });

        // Signup button
        Button signupButton = new Button("Signup");
        styleButton(signupButton);
        signupButton.setOnAction(e -> {
            primaryStage.hide(); // Hide login stage
            new SignupPage(primaryStage).start(new Stage()); // Navigate to signup page
        });

        HBox buttonBox = new HBox(20, loginButton, signupButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Add components to the layout
        formBox.getChildren().addAll(titleLabel, emailLabel, emailField, passwordLabel, passwordField, buttonBox);
        root.setCenter(formBox);

        // Set up the scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleTextField(TextField textField) {
        textField.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        textField.setStyle("-fx-background-color: #444; -fx-text-fill: white; -fx-prompt-text-fill: #888; -fx-border-color: #555; -fx-border-radius: 5px; -fx-padding: 8px;");
        textField.setPrefSize(240, 30);
    }

    private void styleButton(Button button) {
        button.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        button.setStyle("-fx-background-color: orange; -fx-text-fill: black; -fx-border-color: black; -fx-border-radius: 5px; -fx-padding: 10 20;");
        button.setOnMouseEntered(e ->button.setPrefSize(10,20));
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: red; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: orange; -fx-text-fill: black;"));
    }

    private void showAlert(String title, String content, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
