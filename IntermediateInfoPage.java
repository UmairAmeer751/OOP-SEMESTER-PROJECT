package com.example.oopproj2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IntermediateInfoPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Intermediate Information");


        // Root pane with background color
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#333333"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Title
        Label titleLabel = new Label("Enter Intermediate Information");
        titleLabel.setFont(new Font("Arial", 24));
        titleLabel.setTextFill(Color.LIGHTGRAY);

        // GridPane for form layout
        GridPane formGrid = new GridPane();
        formGrid.setHgap(20);
        formGrid.setVgap(20);
        formGrid.setPadding(new Insets(40));
        formGrid.setAlignment(Pos.CENTER);

        // Fields
        Label yearLabel = createStyledLabel("Year of Study:");
        TextField yearField = createStyledTextField();

        Label fieldLabel = createStyledLabel("Field of Study:");
        ComboBox<String> fieldCombo = createStyledComboBox("Pre-Medical", "Pre-Engineering", "ICS");

        Label genderLabel = createStyledLabel("Gender:");
        ComboBox<String> genderCombo = createStyledComboBox("Male", "Female");

        Label marksLabel = createStyledLabel("Marks (out of 1200):");
        TextField marksField = createStyledTextField();

        Label gradeLabel = createStyledLabel("Grade:");
        TextField gradeField = createStyledTextField(); // Editable field
        gradeField.setEditable(true); // Update this line

        Label boardLabel = createStyledLabel("Exam Board Name:");
        TextField boardField = createStyledTextField();

        Label collegeLabel = createStyledLabel("College Name:");
        TextField collegeField = createStyledTextField();

        // Add components to the GridPane
        formGrid.add(yearLabel, 0, 0);
        formGrid.add(yearField, 1, 0);

        formGrid.add(fieldLabel, 0, 1);
        formGrid.add(fieldCombo, 1, 1);

        formGrid.add(genderLabel, 0, 2);
        formGrid.add(genderCombo, 1, 2);

        formGrid.add(marksLabel, 0, 3);
        formGrid.add(marksField, 1, 3);

        formGrid.add(gradeLabel, 0, 4);
        formGrid.add(gradeField, 1, 4);

        formGrid.add(boardLabel, 0, 5);
        formGrid.add(boardField, 1, 5);

        formGrid.add(collegeLabel, 0, 6);
        formGrid.add(collegeField, 1, 6);

        // Buttons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);

        Button backButton = createStyledButton("Back", e -> primaryStage.close());

        Button submitButton = createStyledButton("Submit", e -> {
            if (yearField.getText().isEmpty() || boardField.getText().isEmpty() || marksField.getText().isEmpty() || collegeField.getText().isEmpty()) {
                showAlert("Error", "Please fill in all required fields.");
            } else {
                new Main().start(new Stage()); // Open the Main page
                primaryStage.close(); // Close the current window
            }
        });

        buttonBox.getChildren().addAll(backButton, submitButton);

        // Add components to the root
        VBox layout = new VBox(30, titleLabel, formGrid, buttonBox);
        layout.setAlignment(Pos.CENTER);

        root.setCenter(layout);

        // Scene
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    // Helper to create labels
    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setFont(Font.font("Arial", 16));
        label.setTextFill(Color.LIGHTGRAY);
        return label;
    }

    // Helper to create text fields
    private TextField createStyledTextField() {
        TextField textField = new TextField();
        textField.setPrefWidth(300);
        return textField;
    }

    // Helper to create combo boxes
    private ComboBox<String> createStyledComboBox(String... items) {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll(items);
        comboBox.setPrefWidth(300);
        return comboBox;
    }

    // Helper to create buttons
    private Button createStyledButton(String text, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Button button = new Button(text);
        button.setStyle("""
                -fx-background-color: #f39c12;
                -fx-text-fill: black;
                -fx-font-size: 14px;
                -fx-font-weight: bold;
                -fx-padding: 8 15;
                -fx-background-radius: 5;
            """);
        button.setOnMouseEntered(e->button.setStyle("  -fx-background-color: red;\n" +
                "                -fx-text-fill: black;\n" +
                "                -fx-font-size: 14px;\n" +
                "                -fx-font-weight: bold;\n" +
                "                -fx-padding: 8 15;\n" +
                "                -fx-background-radius: 5;"));
        button.setOnMouseExited(e->button.setStyle("  -fx-background-color: #f39c12 ;\n" +
                "                -fx-text-fill: black;\n" +
                "                -fx-font-size: 14px;\n" +
                "                -fx-font-weight: bold;\n" +
                "                -fx-padding: 8 15;\n" +
                "                -fx-background-radius: 5;"));

        button.setOnAction(action);

        return button;
    }

    // Alert box for errors
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
