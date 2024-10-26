/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import java.util.List;
import model.Shift;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class ShiftDBContext extends DBContext<Shift>{
    
    
    public List<Shift> getShiftsByPlanId(int planId) {
        List<Shift> shifts = new ArrayList<>();
        String sql = "" +
                     "WHERE pd.plid = ?";

        try (PreparedStatement stm = connection.prepareStatement(sql)) {
            stm.setInt(1, planId);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Shift shift = new Shift();
                shift.setSid(rs.getInt("sid"));
                shift.setSname(rs.getString("sname"));
                shift.setStarttime(rs.getString("starttime"));  // Get start time as String
                shift.setEndtime(rs.getString("endtime"));      // Get end time as String
                shift.setDate(rs.getDate("date"));              // Date remains as Date

                shifts.add(shift);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return shifts;
    }

    @Override
    public ArrayList<Shift> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Shift model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
