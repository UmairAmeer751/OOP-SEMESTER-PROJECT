package com.example.oopproj2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ViewAllStudents extends Application {

    private TableView<Student> tableView;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("View Students");

        // Root Layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(15));
        root.setStyle("-fx-background-color: black;");

        // TableView setup
        tableView = new TableView<>();
        tableView.setStyle("-fx-background-color: orange; -fx-table-cell-border-color: orange;");
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        // Columns setup
        TableColumn<Student, String> nameColumn = createColumn("Student Name", "studentName");
        TableColumn<Student, String> numberColumn = createColumn("Student Number", "studentNumber");
        TableColumn<Student, String> emailColumn = createColumn("Student Email", "studentEmail");
        TableColumn<Student, String> cnicColumn = createColumn("Student CNIC", "studentCnic");
        TableColumn<Student, String> degreeColumn = createColumn("Student Degree", "studentDegree");

        // Add columns to TableView
        tableView.getColumns().addAll(nameColumn, numberColumn, emailColumn, cnicColumn, degreeColumn);

        // Fetch and display data
        fetchAndDisplayData("SELECT * FROM admission");

        root.setCenter(tableView);

        // Scene setup
        Scene scene = new Scene(root, 1000, 500, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TableColumn<Student, String> createColumn(String title, String property) {
        TableColumn<Student, String> column = new TableColumn<>(title);
        column.setCellValueFactory(new PropertyValueFactory<>(property));
        column.setStyle("-fx-alignment: CENTER; -fx-background-color: black; -fx-text-fill: white;");
        return column;
    }

    private void fetchAndDisplayData(String query) {
        tableView.getItems().clear();

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/comsats_admission", "root", "umair@2004");
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            while (rs.next()) {
                String studentName = rs.getString("studentName") != null ? rs.getString("studentName") : "N/A";
                String studentNumber = rs.getString("studentNumber");
                String studentEmail = rs.getString("studentEmail");
                String studentCnic = rs.getString("studentCNIC");
                String studentDegree = rs.getString("studentDeg");

                tableView.getItems().add(new Student(studentName, studentNumber, studentEmail, studentCnic, studentDegree));
            }
        } catch (SQLException e) {
            showAlert(Alert.AlertType.ERROR, "Database Error", "Error fetching data: " + e.getMessage());
            System.err.println("Error fetching data: " + e.getMessage());
        }
    }

    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Student Class for TableView
    public static class Student {
        private final String studentName;
        private final String studentNumber;
        private final String studentEmail;
        private final String studentCnic;
        private final String studentDegree;

        public Student(String studentName, String studentNumber, String studentEmail, String studentCnic, String studentDegree) {
            this.studentName = studentName;
            this.studentNumber = studentNumber;
            this.studentEmail = studentEmail;
            this.studentCnic = studentCnic;
            this.studentDegree = studentDegree;
        }

        public String getStudentName() {
            return studentName;
        }

        public String getStudentNumber() {
            return studentNumber;
        }

        public String getStudentEmail() {
            return studentEmail;
        }

        public String getStudentCnic() {
            return studentCnic;
        }

        public String getStudentDegree() {
            return studentDegree;
        }
    }

    // Database class to connect and fetch data from the database
    public static class Database {
        public static ResultSet executeReadQuery(String query) throws SQLException {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/comsats_admission", "root", "umair@2004");
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        }
    }
}
