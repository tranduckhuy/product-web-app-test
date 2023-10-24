package me.duchuy.productwebapp.dao;

import me.duchuy.productwebapp.model.User;

/**
 *
 * @author ADMIN
 */
public interface UserDAO {
    
    public String add(User user);
    
    public boolean isDuplicatedUser(String uEmail);
    
    public String isValidUser(String uEmail, String uPassword);
    
}
