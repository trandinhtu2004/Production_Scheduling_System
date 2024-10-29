/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import dal.DBContext;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductionPlan;
import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import model.Department;
import model.Product;
import model.ProductionPlanHeader;

/**
 *
 * @author admin
 */
public class ProductionPlanDBContext extends DBContext<ProductionPlan> {

    @Override
    public void insert(ProductionPlan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plans]\n"
                    + "           ([plname]\n"
                    + "           ,[startdate]\n"
                    + "           ,[enddate]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setString(1, model.getName());
            stm_insert_plan.setDate(2, model.getStart());
            stm_insert_plan.setDate(3, model.getEnd());
            stm_insert_plan.setInt(4, model.getDept().getId());
            stm_insert_plan.executeUpdate();

            String sql_select_plan = "SELECT @@IDENTITY as plid";
            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("plid"));
            }

            String sql_insert_header = "INSERT INTO [PlanHeaders]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            for (ProductionPlanHeader header : model.getHeaders()) {
                PreparedStatement stm_insert_header = connection.prepareStatement(sql_insert_header);
                stm_insert_header.setInt(1, model.getId());
                stm_insert_header.setInt(2, header.getProduct().getId());
                stm_insert_header.setInt(3, header.getQuantity());
                stm_insert_header.setFloat(4, header.getEstimatedeffort());
                stm_insert_header.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public ArrayList<ProductionPlan> list() {
       ArrayList<ProductionPlan> plans = new ArrayList<>();
        HashMap<Integer, ProductionPlan> planMap = new HashMap<>();
        
        String sql = "SELECT pl.plid, pl.plname, pl.startdate, pl.enddate, d.dname, "
                   + "p.pid, p.pname, ph.quantity, ph.estimatedeffort "
                   + "FROM Plans pl "
                   + "INNER JOIN PlanHeaders ph ON ph.plid = pl.plid "
                   + "INNER JOIN Departments d ON d.did = pl.did "
                   + "INNER JOIN Products p ON p.pid = ph.pid";
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            stm = connection.prepareStatement(sql);
            rs = stm.executeQuery();
            
            while (rs.next()) {
                int planId = rs.getInt("plid");
                
                // If the plan is not in the map, create a new one
                ProductionPlan plan = planMap.get(planId);
                if (plan == null) {
                    plan = new ProductionPlan();
                    plan.setId(planId);
                    plan.setName(rs.getString("plname"));
                    plan.setStart(rs.getDate("startdate"));
                    plan.setEnd(rs.getDate("enddate"));

                    Department d = new Department();
                    d.setName(rs.getString("dname"));
                    plan.setDept(d);
                    
                    planMap.put(planId, plan);
                }

                // Create a product and plan header
                Product p = new Product();
                p.setId(rs.getInt("pid"));
                p.setName(rs.getString("pname"));

                ProductionPlanHeader header = new ProductionPlanHeader();
                header.setProduct(p);
                header.setQuantity(rs.getInt("quantity"));
                header.setEstimatedeffort(rs.getFloat("estimatedeffort"));

                // Add header to the plan
                plan.getHeaders().add(header);
            }

            // Convert the map values to a list
            
            

            // Convert the map values to a list
            plans = new ArrayList<>(planMap.values());

        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(ProductDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //return the arrayslist.
        return plans;

    }
    
    
    public ProductionPlan getProduct(int planId){
 
    ProductionPlan plan = new ProductionPlan();
    String sql = "SELECT pl.plid, pl.plname, pl.startdate, pl.enddate, d.dname, "
               + "p.pid, p.pname, ph.quantity, ph.estimatedeffort "
               + "FROM Plans pl "
               + "INNER JOIN PlanHeaders ph ON ph.plid = pl.plid "
               + "INNER JOIN Departments d ON d.did = pl.did "
               + "INNER JOIN Products p ON p.pid = ph.pid "
               + "WHERE pl.plid = ?";
    PreparedStatement stm = null;
    ResultSet rs = null;

    try {
        stm = connection.prepareStatement(sql);
        stm.setInt(1, planId);
        rs = stm.executeQuery();

        if (rs.next()) {
            plan.setId(rs.getInt("plid"));
            plan.setName(rs.getString("plname"));
            plan.setStart(rs.getDate("startdate"));
            plan.setEnd(rs.getDate("enddate"));

            Department dept = new Department();
            dept.setName(rs.getString("dname"));
            plan.setDept(dept);

            do {
                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));

                ProductionPlanHeader header = new ProductionPlanHeader();
                header.setProduct(product);
                header.setQuantity(rs.getInt("quantity"));
                header.setEstimatedeffort(rs.getFloat("estimatedeffort"));

                plan.getHeaders().add(header);
                plan.setPheaders(header);
            } while (rs.next());
            
        }
    } catch (SQLException ex) {
      Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);

    } finally {
        try {
            stm.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(ProductionPlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    return plan;
}
    
}
