package me.duchuy.productwebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.duchuy.productwebapp.model.User;
import me.duchuy.productwebapp.util.DBUtil;

/**
 *
 * @author ADMIN
 */
public class UserDAOImpl implements UserDAO{

    @Override
    public String add(User user) {
        String status = "Sorry, the email is already associated with an account.";
        
        boolean isDuplicated = isDuplicatedUser(user.getEmail());
        
        if (isDuplicated) {
            return status;
        }
        
        String query = "INSERT INTO user (id, email, password, name, created_at) VALUES (?,?,?,?,?)";
        
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, user.getId());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getName());
            ps.setTimestamp(5, user.getCreated_at());
            int result = ps.executeUpdate();
            if (result > 0) {
                status = "Registration Successful!";
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return status;
    }
    
    @Override
    public boolean isDuplicatedUser(String email) {
        String query = "SELECT * FROM user WHERE email = ?";
        
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String isValidUser(String email, String password) {
        
        String name = null;
        
        String query = "SELECT name FROM user WHERE email = ? and password = ?";
        
        try (Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                name = rs.getString("name");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return name;
    }

    
    
}
