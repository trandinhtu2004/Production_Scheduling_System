/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.productionplan;

import controller.auth.BaseRBACController;
import dal.DepartmentDBContext;
import dal.ProductDBContext;
import dal.ProductionPlanDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.authentication.User;

/**
 *
 * @author ADMIN
 */
public class ProductionListPlanController extends BaseRBACController{

    @Override
    protected void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User account) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    protected void doAuthorizedGet(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
         DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductDBContext dbProduct = new ProductDBContext();
        ProductionPlanDBContext dbPlan = new ProductionPlanDBContext();
        
            request.setAttribute("plans", dbPlan.list());
        
        request.setAttribute("product", dbProduct.list());
        
        request.getRequestDispatcher("../plan/list.jsp").forward(request, response);
    }

   
//    @Override
//    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response, User account) throws ServletException, IOException {
//        
//    }
//
//    
    
}
