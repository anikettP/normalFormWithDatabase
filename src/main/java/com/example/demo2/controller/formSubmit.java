package com.example.demo2.controller;

import com.example.demo2.dao.PostgresDb;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "formSubmit", value = "/formSubmit")
public class formSubmit extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Retrieve form data
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String[] hobbiesArray = request.getParameterValues("hobbies");
        String country = request.getParameter("country");
        String message = request.getParameter("message");

        // Convert hobbies array to a single string
        String hobbies = (hobbiesArray != null) ? String.join(", ", hobbiesArray) : "";

        // Debugging: Log form data
        System.out.println("Form submitted");
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Gender: " + gender);
        System.out.println("Hobbies: " + hobbies);
        System.out.println("Country: " + country);
        System.out.println("Message: " + message);

        // Validate form data (basic validation)
        if (name == null || email == null || password == null || gender == null || country == null) {
            System.out.println("Error: Missing required fields!");
            out.println("<h2>Error: Missing required fields!</h2>");
            out.println("<a href='form'>Back to Form</a>");
            return;
        }

        PostgresDb postgresDb = new PostgresDb();

        try {
            System.out.println("Attempting to insert data into database...");
            boolean insertStatus = postgresDb.insertEmployeeData(name, email, password, gender, hobbies, country, message);
            if (insertStatus) {
                System.out.println("Data inserted successfully!");
                out.println("Data inserted successfully!");
            } else {
                System.out.println("Data failed to insert.");
                out.println("Data failed to insert.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();  // Log the stack trace for debugging
            out.println("An error occurred: " + e.getMessage());
        }

        // Provide a link to go back to the form
        out.println("<a href='form'>Back to Form</a>");
    }
}
