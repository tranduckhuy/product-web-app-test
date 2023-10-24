package me.duchuy.productwebapp.model;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class User {
    
    private String id;
    private String email;
    private String password;
    private String name;
    private Timestamp created_at;

    public User() {
    }

    public User(String id, String email, String password, String name, Timestamp created_at) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    
    
}
