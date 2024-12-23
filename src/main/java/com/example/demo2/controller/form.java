package com.example.demo2.controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "form", value = "/form")
public class form extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello Worldwrwer 2!";
    }


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        String form = """
                <!DOCTYPE html>
                        <html lang="en">
                        <head>
                            <meta charset="UTF-8">
                            <title>Sample HTML Form</title>
                        </head>
                        <body>
                            <h2>Sample Form</h2>
                            <form action="formSubmit" method="post">
                                <!-- Your form fields here -->
                                <label for="name">Name:</label><br>
                                <input type="text" id="name" name="name" required><br><br>
                
                                <label for="email">Email:</label><br>
                                <input type="email" id="email" name="email" required><br><br>
                
                                <label for="password">Password:</label><br>
                                <input type="password" id="password" name="password" required><br><br>
                
                                <label for="gender">Gender:</label><br>
                                <input type="radio" id="male" name="gender" value="male">
                                <label for="male">Male</label><br>
                                <input type="radio" id="female" name="gender" value="female">
                                <label for="female">Female</label><br><br>
                
                                <label for="hobbies">Hobbies:</label><br>
                                <input type="checkbox" id="reading" name="hobbies" value="Reading">
                                <label for="reading">Reading</label><br>
                                <input type="checkbox" id="traveling" name="hobbies" value="Traveling">
                                <label for="traveling">Traveling</label><br>
                                <input type="checkbox" id="coding" name="hobbies" value="Coding">
                                <label for="coding">Coding</label><br><br>
                
                                <label for="country">Country:</label><br>
                                <select id="country" name="country" required>
                                    <option value="usa">USA</option>
                                    <option value="canada">Canada</option>
                                    <option value="india">India</option>
                                    <option value="other">Other</option>
                                </select><br><br>
                
                                <label for="message">Message:</label><br>
                                <textarea id="message" name="message" rows="4" cols="30" placeholder="Enter your message"></textarea><br><br>
                
                                <input type="submit" value="Submit">
                                <input type="reset" value="Reset">
                            </form>
                
                            <!-- Add the "Submitted Data" button -->
                            <form action="employeeData" method="get">
                                <input type="submit" value="Submitted Data">
                            </form>
                        </body>
                        </html>
                
                
                """;


        // Hello
        PrintWriter out = response.getWriter();
        out.println(form);
            }

    public void destroy() {
    }
}