
package me.duchuy.productwebapp.model;

import java.sql.Timestamp;

/**
 *
 * @author ADMIN
 */
public class Product {
    
    private String id;
    private String name;
    private String description;
    private String image;
    private String category;
    private Timestamp created_at;

    public Product() {
    }

    public Product(String id, String name, String description, String image, String category, Timestamp created_at) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.created_at = created_at;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Timestamp created_at) {
        this.created_at = created_at;
    }
    
}
