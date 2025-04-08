 package com.itu.hello; 
import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;


public class DepenseServlet extends HttpServlet {

protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    PrintWriter out = res.getWriter();
    
   
    double somme=Double.parseDouble(req.getParameter("somme"));
    
    String idPv = req.getParameter("idPv");
    int prevision = (idPv != null && !idPv.isEmpty()) ? Integer.parseInt(idPv) : -1; // Handle default value

    
    if (somme >=0 && prevision != -1) {
       
        Depense depense = new Depense(prevision,somme);
        if(Prevision.check(somme,prevision)==true){
         depense.save(); 
        res.sendRedirect("Formdepense");
        }else{
            out.println("impossible, vous n'avez plus assez de prevision");
        }
      
        }
     
   
}

protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
  PrintWriter out = res.getWriter();



      


    List<Depense> EtatList = Depense.Etat();  
    req.setAttribute("EtatList", EtatList);  
    RequestDispatcher dispatcher = req.getRequestDispatcher("Etat.jsp");
    dispatcher.forward(req, res);
}
}



