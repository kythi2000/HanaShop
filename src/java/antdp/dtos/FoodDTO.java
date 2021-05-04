/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author HP 840 G2
 */
public class FoodDTO implements Serializable{
    private String foodID, name, description, image, category;
    private int quantity;
    private float price;
    private Date dateOfCreate;
    private boolean status;
    
    private int avaiableQuantity;

    public FoodDTO(String foodID, String name, String description, String image, String category, int quantity, float price, Date dateOfCreate) {
        this.foodID = foodID;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.dateOfCreate = dateOfCreate;
        this.avaiableQuantity = quantity;
    }

    public FoodDTO(String foodID, String name, String description, String image, String category, int quantity, float price) {
        this.foodID = foodID;
        this.name = name;
        this.description = description;
        this.image = image;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getAvaiableQuantity() {
        return avaiableQuantity;
    }

    public void setAvaiableQuantity(int avaiableQuantity) {
        this.avaiableQuantity = avaiableQuantity;
    }
    
    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
