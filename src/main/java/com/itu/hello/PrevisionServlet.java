package com.itu.hello; 
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;


public class PrevisionServlet extends HttpServlet {



protected void doPost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {


  PrintWriter out = res.getWriter();
  String libelle=req.getParameter("libelle");
  
  double montant=Double.parseDouble(req.getParameter("montant"));


  if(!libelle.equals("") && montant>=0){
      Prevision newPrevision=new Prevision(libelle,montant);
      newPrevision.save();
RequestDispatcher dispatcher = req.getRequestDispatcher("Form.html");
dispatcher.forward(req, res);

  }else{
    out.println("Erreur de lors de l'insertion");
  }




        }
  
protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

    try {
        RequestDispatcher dispatcher = req.getRequestDispatcher("Form.html");
        System.out.println("Forwarding to Form.html");
        dispatcher.forward(req, res);
    } catch (Exception e) {
        e.printStackTrace();
        throw new ServletException("Erreur lors de la redirection vers Form.html", e);
    }
}
}
