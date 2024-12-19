package com.example.oopproj2;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.Optional;
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Admission Portal");
        primaryStage.setMaximized(true); // Full screen

        // Load background image
        Image backgroundImage = new Image(getClass().getResource("/welcome.jpg").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setPreserveRatio(true); // Preserve aspect ratio
        backgroundImageView.setFitWidth(primaryStage.getWidth()); // Set image width to full screen
        backgroundImageView.setFitHeight(primaryStage.getHeight()); // Set image height to full screen

        // Welcome Text
        Text welcomeText = new Text("WELCOME TO COMSATS LAHORE.");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 80));
        welcomeText.setFill(Color.BLACK); // Changed text color to black
        welcomeText.setStroke(Color.WHITE);
        welcomeText.setStrokeWidth(1.75);

        // Quote Text
        Text quoteText = new Text("The future belongs to those who believe in the beauty of their dreams.");
        quoteText.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        quoteText.setFill(Color.BLUE); // Changed text color to black
        quoteText.setStroke(Color.WHITE);
        quoteText.setStrokeWidth(1.5);

        // "Proceed Further" Button
        Button nextButton = new Button("Proceed Further");
        styleButton(nextButton);
        nextButton.setOnAction(event -> {
            primaryStage.close(); // Close the current main stage
            new LoginPage().start(new Stage()); // Launch the LoginPage
        });

        // Admin Login Button
        Button adminButton = new Button("Admin Login");
        styleButton(adminButton);
        adminButton.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Authentication");
            dialog.setHeaderText("Admin Login");
            dialog.setContentText("Enter the Admin Password:");

            Optional<String> inputPassword = dialog.showAndWait();

            String correctPassword = "admin123"; // Correct password to verify

            if (inputPassword.isPresent() && inputPassword.get().equals(correctPassword)) {
                new ViewAllStudents().start(new Stage()); // Redirect to admin view
            } else {
                showAlert("Error", "Incorrect Password! Access Denied.");
            }
        });

        // Arrange Buttons in HBox
        HBox buttonBox = new HBox(20, adminButton, nextButton);
        buttonBox.setStyle("-fx-background-color: #333333;"); // Charcoal background color
        buttonBox.setAlignment(Pos.CENTER);

        // Arrange All Elements in a BorderPane
        BorderPane root = new BorderPane();
        root.setTop(new StackPane(welcomeText));
        root.setCenter(new StackPane(quoteText));
        root.setBottom(buttonBox);

        // StackPane to Layer Background and Content
        StackPane stackPane = new StackPane(backgroundImageView, root);

        // Create Scene and Set it to Stage
        Scene scene = new Scene(stackPane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void styleButton(Button button) {
        button.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        button.setStyle("-fx-background-color: #FFA500; -fx-text-fill: black; -fx-padding: 10 20; -fx-border-color: black; -fx-border-width: 2;");
        button.setOnMouseEntered(e -> button.setStyle("-fx-background-color: red; -fx-text-fill: black;"));
        button.setOnMouseExited(e -> button.setStyle("-fx-background-color: #FFA500; -fx-text-fill: black;"));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
