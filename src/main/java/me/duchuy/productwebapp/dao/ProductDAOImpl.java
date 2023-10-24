
package me.duchuy.productwebapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.duchuy.productwebapp.model.Product;
import me.duchuy.productwebapp.util.DBUtil;

/**
 *
 * @author ADMIN
 */
public class ProductDAOImpl implements ProductDAO {

    @Override
    public int add(Product product) {
        int result = 0;

        String query = "INSERT INTO product (id, name, description, image, category, created_at) VALUES (?,?,?,?,?,?)";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, product.getId());
            ps.setString(2, product.getName());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getImage());
            ps.setString(5, product.getCategory());
            ps.setTimestamp(6, product.getCreated_at());
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public List<Product> getProducts(int start, int total) {
        List<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM product LIMIT " + (start - 1) + "," + total;

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setCreated_at(rs.getTimestamp("created_at"));

                productList.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    @Override
    public int delete(String productId) {
        int result = 0;

        String query = "DELETE FROM product WHERE id = ?";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, productId);
            result = ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    @Override
    public List<Product> getProductsByName(String searchName) {
        List<Product> productList = new ArrayList<>();

        String query = "SELECT * FROM product WHERE name LIKE ?";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            
            ps.setString(1, "%" + searchName + "%");
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product product = new Product();

                product.setId(rs.getString("id"));
                product.setName(rs.getString("name"));
                product.setDescription(rs.getString("description"));
                product.setImage(rs.getString("image"));
                product.setCategory(rs.getString("category"));
                product.setCreated_at(rs.getTimestamp("created_at"));

                productList.add(product);
            }

        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productList;
    }

    @Override
    public int getTotalNumProduct() {
        int total = 0;

        String query = "SELECT COUNT(*) AS total FROM product";

        try ( Connection conn = DBUtil.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }

}
