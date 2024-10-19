/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.util.ArrayList;
import model.authentication.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.authentication.Feature;
import model.authentication.Role;

/**
 *
 * @author admin
 */
public class UserDBContext extends DBContext<User> {

    public ArrayList<Role> getRoles(String username) {
        PreparedStatement stm = null;
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "SELECT * FROM [Users] u \n"
                    + "  INNER JOIN UserRoles ur ON u.[uid] = ur.[uid]\n"
                    + "  INNER JOIN [Roles] r ON r.rid = ur.rid\n"
                    + "  INNER JOIN RoleFeatures rf ON rf.rid = r.rid\n"
                    + "  INNER JOIN Features f ON f.fid = rf.fid\n"
                    + "WHERE u.username = ? ";

            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            Role crole = new Role();
            crole.setId(-1);
            while (rs.next()) {
                int rid = rs.getInt("rid");
                if (rid != crole.getId()) {
                    crole = new Role();
                    crole.setId(rid);
                    crole.setName(rs.getString("rname"));
                    roles.add(crole);
                }

                Feature f = new Feature();
                f.setId(rs.getInt("fid"));
                f.setName(rs.getString("fname"));
                f.setUrl(rs.getString("url"));

                f.setRoles(roles);
                crole.getFeatures().add(f);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return roles;
    }

    public User get(String username, String password) {
        User user = null;
        PreparedStatement stm = null;
        try {
            String sql = "SELECT * FROM [Users]\n"
                    + "WHERE [username] = ? AND [password] = ?";
            stm = connection.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setEmail(rs.getString("email"));
                user.setUsername(username);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                stm.close();
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(UserDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return user;
    }

    @Override
    public void insert(User model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<User> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
