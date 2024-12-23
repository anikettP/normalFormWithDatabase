package com.example.demo2.test;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckTableSchema {
    public static void main(String[] args) {
        // Database connection parameters
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        Connection connection = null;

        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Attempt to establish a connection
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Database connected successfully!");

                // Get database metadata
                DatabaseMetaData metaData = connection.getMetaData();

                // Query to get columns of the employee table
                ResultSet resultSet = metaData.getColumns(null, null, "employee", null);

                System.out.println("Schema of the 'employee' table:");
                while (resultSet.next()) {
                    String columnName = resultSet.getString("COLUMN_NAME");
                    String dataType = resultSet.getString("TYPE_NAME");
                    System.out.println(columnName + " - " + dataType);
                }
            } else {
                System.out.println("Failed to connect to the database.");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
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
