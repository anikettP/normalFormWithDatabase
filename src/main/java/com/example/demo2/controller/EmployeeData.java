package com.example.demo2.controller;

import com.example.demo2.dao.PostgresDb;
import com.example.demo2.dto.Employee;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "employeeData", value = "/employeeData")
public class EmployeeData extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        PostgresDb db = new PostgresDb();

        // Retrieve employee data
        List<Employee> employeeList = db.getEmployeeData();

        // Debugging: Log the size of the employee list
        System.out.println("Number of employees retrieved: " + employeeList.size());

        // Generate HTML to display employee data
        out.println("<html><body>");
        out.println("<h2>Employee Data</h2>");
        out.println("<table border='1'>");
        out.println("<tr><th>Name</th><th>Email</th><th>Gender</th><th>Hobbies</th><th>Country</th><th>Message</th></tr>");

        for (Employee employee : employeeList) {
            System.out.println("Employee: " + employee.getName());
            out.println("<tr>");
            out.println("<td>" + employee.getName() + "</td>");
            out.println("<td>" + employee.getEmail() + "</td>");
            out.println("<td>" + employee.getGender() + "</td>");
            out.println("<td>" + employee.getHobbies() + "</td>");
            out.println("<td>" + employee.getCountry() + "</td>");
            out.println("<td>" + employee.getMessage() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body></html>");
    }
}
