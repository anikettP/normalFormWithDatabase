package com.example.demo2.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.example.demo2.dto.Employee;

public class PostgresDb {

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    // Method to establish a connection to the database
    public static Connection getConnection() {
        try {
            // Load PostgreSQL JDBC Driver
            Class.forName("org.postgresql.Driver");
            // Establish connection
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if connection fails
        }
    }

    // Method to close the connection
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to insert employee data into the database
    public boolean insertEmployeeData(String name, String email, String password, String gender, String hobbies, String country, String message) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = getConnection();
            if (connection == null) {
                System.err.println("Failed to establish a database connection.");
                return false;
            }

            // SQL query for inserting data
            String sql = "INSERT INTO employee (name, email, password, gender, hobbies, country, message) VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // Set query parameters
            statement.setString(1, name);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, gender);
            statement.setString(5, hobbies);
            statement.setString(6, country);
            statement.setString(7, message);

            // Execute the query
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0; // Return true if at least one row was inserted

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if an error occurs
        } finally {
            // Close resources in the correct order
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    closeConnection(connection);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // Method to retrieve employee data from the database
    public List<Employee> getEmployeeData() {
        List<Employee> employeeList = new ArrayList<>();

        String sql = "SELECT name, email, gender, hobbies, country, message FROM employee";

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Employee employee = new Employee();
                employee.setName(resultSet.getString("name"));
                employee.setEmail(resultSet.getString("email"));
                employee.setGender(resultSet.getString("gender"));
                employee.setHobbies(resultSet.getString("hobbies"));
                employee.setCountry(resultSet.getString("country"));
                employee.setMessage(resultSet.getString("message"));

                employeeList.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return employeeList;
    }
}
