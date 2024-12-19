package com.example.oopproj2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SchoolingInfoPage extends Application {

    private TextField applicantNameField, contactField, emailField, guardianNameField, guardianContactField,
            guardianEmailField, schoolNameField, cnicField;
    private ComboBox<String> dayComboBox, monthComboBox, yearComboBox, genderComboBox;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("School Info Page");
        primaryStage.setMaximized(true);

        // Root container
        BorderPane root = new BorderPane();
        root.setBackground(new Background(new BackgroundFill(Color.web("#333333"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Title
        Label titleLabel = new Label("Enter Schooling Information");
        titleLabel.setFont(Font.font("Arial", 30));
        titleLabel.setTextFill(Color.WHITE);

        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(20, 0, 20, 0));

        // Form layout
        GridPane formGrid = new GridPane();
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.setPadding(new Insets(20, 50, 20, 50));
        formGrid.setAlignment(Pos.TOP_CENTER);

        Font labelFont = Font.font("Arial", 18);
        Font fieldFont = Font.font("Arial", 16);

        applicantNameField = createLabeledField(formGrid, "Applicant Name:", 0, 0, labelFont, fieldFont);
        contactField = createLabeledField(formGrid, "Contact (11 digits):", 0, 1, labelFont, fieldFont);
        emailField = createLabeledField(formGrid, "Email:", 0, 2, labelFont, fieldFont);

        // Date of Birth
        Label dobLabel = createStyledLabel("Date of Birth:", labelFont);
        formGrid.add(dobLabel, 0, 3);

        dayComboBox = new ComboBox<>();
        for (int i = 1; i <= 31; i++) dayComboBox.getItems().add(String.format("%02d", i));
        dayComboBox.setPromptText("Day");

        monthComboBox = new ComboBox<>();
        monthComboBox.getItems().addAll("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        monthComboBox.setPromptText("Month");

        yearComboBox = new ComboBox<>();
        for (int i = 1980; i <= 2026; i++) yearComboBox.getItems().add(String.valueOf(i));
        yearComboBox.setPromptText("Year");

        HBox dobBox = new HBox(10, dayComboBox, monthComboBox, yearComboBox);
        formGrid.add(dobBox, 1, 3);

        genderComboBox = createLabeledDropdown(formGrid, "Gender:", 0, 4, labelFont, new String[]{"Male", "Female", "Other"});
        guardianNameField = createLabeledField(formGrid, "Guardian Name:", 0, 5, labelFont, fieldFont);
        guardianContactField = createLabeledField(formGrid, "Guardian Contact:", 0, 6, labelFont, fieldFont);
        guardianEmailField = createLabeledField(formGrid, "Guardian Email:", 0, 7, labelFont, fieldFont);
        schoolNameField = createLabeledField(formGrid, "School Name:", 0, 8, labelFont, fieldFont);
        cnicField = createLabeledField(formGrid, "Your CNIC:", 0, 9, labelFont, fieldFont);

        // Buttons
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setPadding(new Insets(20));

        Button backButton = createStyledButton("Back", "#FF8C00");
        Button nextButton = createStyledButton("Next", "#FF8C00");

        buttonBox.getChildren().addAll(backButton, nextButton);

        // Event Handlers
        backButton.setOnAction(e -> primaryStage.close());

        nextButton.setOnAction(e -> {

            String query = "INSERT INTO admission (studentName, studentNumber, studentEmail, studentCNIC, studentDeg) " +
                    "VALUES ('" + applicantNameField.getText() + "', '" + contactField.getText() + "', '" + emailField.getText() + "', '" + cnicField.getText() + "', '" + "Bachelors" + "');";

            database.executeWriteQuery(query);


            new IntermediateInfoPage().start(new Stage());
            primaryStage.close();
        });

        // Final layout
        VBox mainLayout = new VBox(10, titleBox, formGrid, buttonBox);
        mainLayout.setAlignment(Pos.CENTER);
        root.setCenter(mainLayout);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TextField createLabeledField(GridPane grid, String labelText, int col, int row, Font labelFont, Font fieldFont) {
        Label label = createStyledLabel(labelText, labelFont);
        TextField field = new TextField();
        field.setFont(fieldFont);
        grid.add(label, col, row);
        grid.add(field, col + 1, row);
        return field;
    }

    private ComboBox<String> createLabeledDropdown(GridPane grid, String labelText, int col, int row, Font labelFont, String[] options) {
        Label label = createStyledLabel(labelText, labelFont);
        ComboBox<String> dropdown = new ComboBox<>();
        dropdown.getItems().addAll(options);
        dropdown.setPromptText("Select");
        grid.add(label, col, row);
        grid.add(dropdown, col + 1, row);
        return dropdown;
    }

    private Label createStyledLabel(String text, Font font) {
        Label label = new Label(text);
        label.setFont(font);
        label.setTextFill(Color.WHITE);
        return label;
    }

    private Button createStyledButton(String text, String color) {
        Button button = new Button(text);
        button.setFont(Font.font("Arial", 16));
        button.setStyle("-fx-background-color: " + color + "; -fx-text-fill: black;");
        button.setOnMouseEntered(e->button.setStyle("-fx-background-color: red ; -fx-text-fill: black;"));
        button.setOnMouseExited(e->button.setStyle("-fx-background-color: orange ; -fx-text-fill: black;"));


        return button;
    }

    public static void main(String[] args) {
        launch(args);
    }
}