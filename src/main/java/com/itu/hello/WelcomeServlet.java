package com.itu.hello; 
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;


public class WelcomeServlet extends HttpServlet {


protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    try {
        RequestDispatcher dispatcher = req.getRequestDispatcher("welcome.html");
   
        dispatcher.forward(req, res);
    } catch (Exception e) {
        e.printStackTrace();
        throw new ServletException("Erreur lors de la redirection vers welcome.html", e);
    }
}
}
