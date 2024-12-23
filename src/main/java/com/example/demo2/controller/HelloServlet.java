package com.example.demo2.controller;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    // Servlet initialization
    public void init() {
        message = "Hello World!";
    }

    // Handling HTTP GET requests
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        // Prepare the response
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><head><title>Hello Servlet</title></head><body>");
            out.println("<h1>" + message + "</h1>");
            out.println("<p>Welcome to the servlet demo!</p>");
            out.println("</body></html>");
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error writing response: " + e.getMessage());
        }
    }
    // Cleanup method (called when servlet is destroyed)
    public void destroy() {
        // Optional cleanup code, if needed
    }
}
