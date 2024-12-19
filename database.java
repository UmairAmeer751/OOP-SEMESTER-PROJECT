package com.example.oopproj2;
import java.sql.*;
public class database {
    // Connect to the database
    public static Connection connectDB() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/comsats_admission", "root", "umair@2004");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    // Prepared query for login
    public static ResultSet executePreparedQuery(String email, String password) {
        String query = "SELECT * FROM students WHERE studentEmail = ? AND studentPassword = ?";
        try {
            Connection con = connectDB();
            if (con == null) {
                throw new RuntimeException("Database connection failed.");
            }
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            return pstmt.executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Database Error: " + e.getMessage(), e);
        }
    }
    // Execute queries for inserting or updating data
    public static void executeWriteQuery(String query) {
        try {
            Connection con = connectDB();
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
