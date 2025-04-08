package com.itu.hello; 
import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.RequestDispatcher;


public class DepenseFormServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
            PrintWriter out = res.getWriter();
        // Retrieve all departments as a List<BaseObject> and cast to List<Dept>
     


         List<Prevision> PrevisionList=Prevision.findAll();

        if(PrevisionList==null){
            out.println("erreur lors de recuperation des prevision");
        }else{
        
            req.setAttribute("PrevisionList",PrevisionList);
          
 RequestDispatcher dispatcher = req.getRequestDispatcher("DepenseForm.jsp");
        dispatcher.forward(req, res);
        }





        }

       
    }

