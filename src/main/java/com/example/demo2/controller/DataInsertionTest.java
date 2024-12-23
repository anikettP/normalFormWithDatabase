package com.example.demo2.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataInsertionTest {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Attempt to establish a connection
            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Database connected successfully!");

                // SQL query for inserting data
                String sql = "INSERT INTO employee (name, email, password, gender, hobbies, country, message) VALUES (?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(sql);

                // Set query parameters
                statement.setString(1, "Test User");
                statement.setString(2, "testuser@example.com");
                statement.setString(3, "password123");
                statement.setString(4, "Male");
                statement.setString(5, "Reading, Traveling");
                statement.setString(6, "USA");
                statement.setString(7, "This is a test message.");

                // Execute the query
                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("Data inserted successfully!");
                } else {
                    System.out.println("Data failed to insert.");
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
