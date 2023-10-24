package me.duchuy.productwebapp.dao;

import java.util.List;
import me.duchuy.productwebapp.model.Product;

/**
 *
 * @author ADMIN
 */
public interface ProductDAO {
    
    public int add(Product product);
    
    public List<Product> getProducts(int start, int total);
    
    public List<Product> getProductsByName(String searchName);
    
    public int delete(String productId);
    
    public int getTotalNumArticle();
}
