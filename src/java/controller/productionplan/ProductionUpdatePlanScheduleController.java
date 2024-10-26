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
import java.util.List;
import model.PlanDetail;
import model.Product;
import model.ProductionPlan;
import model.Shift;

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

        ProductionPlanDBContext planDB = new ProductionPlanDBContext();
        ProductionPlanDetailDBContext planDetailDB = new ProductionPlanDetailDBContext();
        ShiftDBContext shiftDB = new ShiftDBContext();

        // Retrieve plan by ID
        ProductionPlan plan = planDB.getPlanById(planId);
        ProductDBContext product = new ProductDBContext();
        // Retrieve PlanDetail and Shift information
        List<PlanDetail> planDetails = planDetailDB.getPlanDetailsByPlanId(planId);
        List<Shift> shifts = shiftDB.getShiftsByPlanId(planId);

        // Set attributes for the JSP
        request.setAttribute("plan", plan);
        request.setAttribute("planDetails", planDetails);
        request.setAttribute("shifts", shifts);

        // Forward to update.jsp to show the tables and shifts
        request.getRequestDispatcher("/plan/update.jsp").forward(request, response);
    } 

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
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
