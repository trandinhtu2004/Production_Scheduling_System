/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.PlanDetail;
import java.sql.*;
import model.Product;
import model.ProductionPlan;

/**
 *
 * @author Admin
 */
public class ProductionPlanDetailDBContext extends DBContext<PlanDetail>{
    
    
    
     public List<PlanDetail> getPlanDetailsByPlanId(int planId) {
        List<PlanDetail> planDetails = new ArrayList<>();
        String sql = "SELECT pl.plid, pl.plname, pl.startdate, pl.enddate, d.dname, "
                   + "p.pid, p.pname, ph.quantity, ph.estimatedeffort "
                   + "FROM Plans pl "
                   + "INNER JOIN PlanHeaders ph ON ph.plid = pl.plid "
                   + "INNER JOIN Departments d ON d.did = pl.did "
                   + "INNER JOIN Products p ON p.pid = ph.pid";
        
        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, planId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PlanDetail planDetail = new PlanDetail();
                planDetail.setPdid(rs.getInt("did"));
                ProductionPlan productionPlan = new ProductionPlan();
                productionPlan.setId(rs.getInt("plid"));
                planDetail.setProductionPlan(productionPlan);

                Product product = new Product();
                product.setId(rs.getInt("pid"));
                product.setName(rs.getString("pname"));
                planDetail.setProduct(product);

                planDetail.setQuantity(rs.getInt("quantity"));
                planDetail.setTotalActualQuantity(rs.getInt("totalActualQuantity"));

                planDetails.add(planDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planDetails;
    }

    @Override
    public ArrayList<PlanDetail> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(PlanDetail model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
