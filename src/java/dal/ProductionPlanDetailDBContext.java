/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;


import java.util.ArrayList;
import java.util.List;
import model.PlanDetail;
import java.sql.*;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;
import model.ProductionPlan;
import model.*;

/**
 *
 * @author Admin
 */
public class ProductionPlanDetailDBContext extends DBContext<PlanDetail>{
   
    
  public List<java.util.Date> getDate(int planId) {
    List<java.util.Date> dates = new ArrayList<>();
    try {
        String sqlPlanDates = "SELECT startdate, enddate FROM Plans WHERE plid = ?";
        PreparedStatement stmDates = connection.prepareStatement(sqlPlanDates);
        stmDates.setInt(1, planId);
        ResultSet rsDates = stmDates.executeQuery();

        if (rsDates.next()) {
            java.sql.Date sqlStartDate = rsDates.getDate("startdate");
            java.sql.Date sqlEndDate = rsDates.getDate("enddate");
            
            // Convert java.sql.Date to java.util.Date
            Date startDate = new Date(sqlStartDate.getTime());
            Date endDate = new Date(sqlEndDate.getTime());

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(startDate);

            while (!calendar.getTime().after(endDate)) {
                dates.add(calendar.getTime());
                calendar.add(Calendar.DATE, 1);
            }
        }
    } catch (SQLException ex) {
        java.util.logging.Logger.getLogger(ProductionPlanDetailDBContext.class.getName())
            .log(java.util.logging.Level.SEVERE, null, ex);
    }
    return dates;
}

  public void insertAndUpdateDetailShift(PlanDetail model) {
        try {
            connection.setAutoCommit(false);

            // Check if the record exists
            String sqlSelectDetail = "SELECT * FROM PlanDetails WHERE pdid = ?";
            PreparedStatement selectStmt = connection.prepareStatement(sqlSelectDetail);
            selectStmt.setInt(1, model.getPdid());
            ResultSet rs = selectStmt.executeQuery();

            if (rs.next()) {
                // Record exists, update it
                String sqlUpdateDetail = "UPDATE PlanDetails SET phid = ?, sid = ?, date = ?, quantity = ? WHERE pdid = ?";
                PreparedStatement updateStmt = connection.prepareStatement(sqlUpdateDetail);
                updateStmt.setInt(1, model.getHeader().getId());
                updateStmt.setInt(2, model.getSid());
                updateStmt.setDate(3, model.getDate());
                updateStmt.setInt(4, model.getQuantityDay());
                updateStmt.setInt(5, model.getPdid());
                updateStmt.executeUpdate();
            } else {
                // Record does not exist, insert it
                String sqlInsertPlanDetail = "INSERT INTO PlanDetails (pdid, phid, sid, date, quantity) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = connection.prepareStatement(sqlInsertPlanDetail);
                insertStmt.setInt(1, model.getPdid());
                insertStmt.setInt(2, model.getHeader().getId());
                insertStmt.setInt(3, model.getSid());
                insertStmt.setDate(4, model.getDate());
                insertStmt.setInt(5, model.getQuantityDay());
                insertStmt.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(ProductionPlanDetailDBContext.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            
        }
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
