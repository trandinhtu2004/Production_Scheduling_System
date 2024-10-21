/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.productionplan;

import controller.auth.BaseRBACController;
import dal.DepartmentDBContext;
import dal.ProductDBContext;
import dal.ProductionPlanDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ProductionPlan;
import java.sql.*;
import model.Department;
import model.Product;
import model.ProductionPlanHeader;
import model.authentication.User;

/**
 *
 * @author admin
 */
public class ProductionPlanCreateController extends BaseRBACController {

    @Override
    protected void doAuthorizedGet(HttpServletRequest request,
            HttpServletResponse response,
            User account) throws ServletException, IOException {
        DepartmentDBContext dbDept = new DepartmentDBContext();
        ProductDBContext dbProduct = new ProductDBContext();

        request.setAttribute("depts", dbDept.get("Production"));
        request.setAttribute("products", dbProduct.list());

        request.getRequestDispatcher("../plan/create.jsp").forward(request, response);
    }

    @Override
    protected void doAuthorizedPost(HttpServletRequest request, HttpServletResponse response,
            User account) throws ServletException, IOException {
        ProductionPlan plan = new ProductionPlan();
        plan.setName(request.getParameter("name"));
        if(plan.getName().isEmpty() || plan.getName() == ""){
            response.getWriter().println("plan must not null!");
            return;
        }
        
        try{
        plan.setStart(Date.valueOf(request.getParameter("from")));
        plan.setEnd(Date.valueOf(request.getParameter("to")));
        }catch(Exception e){
            response.getWriter().println("Invalid date!");
            return;
        }
        if (plan.getStart().after(plan.getEnd())) {
            response.getWriter().println("start date must before the end date");
            return; // Dừng thực hiện nếu có lỗi
        }

        Department d = new Department();
        d.setId(Integer.parseInt(request.getParameter("did")));

        plan.setDept(d);

        String[] pids = request.getParameterValues("pid");
        for (String pid : pids) {
            Product p = new Product();
            p.setId(Integer.parseInt(pid));

            ProductionPlanHeader header = new ProductionPlanHeader();
            header.setProduct(p);
            String raw_quantity = request.getParameter("quantity" + pid);
            String raw_effort = request.getParameter("effort" + pid);
            header.setQuantity(raw_quantity != null && raw_quantity.length() > 0 ? Integer.parseInt(raw_quantity) : 0);
            header.setEstimatedeffort(raw_effort != null && raw_effort.length() > 0 ? Float.parseFloat(raw_effort) : 0);

            if (header.getQuantity() > 0 && header.getEstimatedeffort() > 0) {
                plan.getHeaders().add(header);
            }
        }

        if (plan.getHeaders().size() > 0) {
            ProductionPlanDBContext db = new ProductionPlanDBContext();
            db.insert(plan);
            response.getWriter().println("your plan has been added!");
        } else {
            response.getWriter().println("your plan does not have any headers! it is not allowed!");
        }
    }

}
