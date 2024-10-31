/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.productionplan;

import dal.ProductDBContext;
import dal.ProductionPlanDBContext;
import dal.ProductionPlanDetailDBContext;
import dal.ShiftDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.PlanDetail;
import model.Product;
import model.ProductionPlan;
import model.ProductionPlanHeader;
import model.Shift;
import java.sql.*;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class ProductionUpdatePlanScheduleController extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductionUpdatePlanScheduleController</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductionUpdatePlanScheduleController at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String idParam = request.getParameter("id");
        int planId = Integer.parseInt(idParam);
         
      ProductionPlanDetailDBContext pd = new ProductionPlanDetailDBContext();
        
        ProductionPlanDBContext dbContext = new ProductionPlanDBContext();
        ProductionPlan plan = dbContext.getProduct(planId);
        ArrayList<ProductionPlanHeader> ph = plan.getHeaders();
        
        
        List<java.util.Date> dates = null;
        dates = pd.getListDate(planId);
        

        request.setAttribute("dates", dates);
        request.setAttribute("header", ph);
        request.setAttribute("plan", plan);
        for(int i = 0; i<ph.size();i++){
            ProductionPlanHeader p = ph.get(i);
        }
        request.getRequestDispatcher("../plan/update.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

   

}
