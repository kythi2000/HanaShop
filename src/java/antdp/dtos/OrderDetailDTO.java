/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.io.Serializable;

/**
 *
 * @author HP 840 G2
 */
public class OrderDetailDTO implements Serializable{
    private String orderDetailID, orderID, foodID;
    private int quantity;
    private float price;

    public OrderDetailDTO(String orderDetailID, String orderID, String foodID, int quantity, float price) {
        this.orderDetailID = orderDetailID;
        this.orderID = orderID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
    }

    public OrderDetailDTO(String foodID, int quantity, float price) {
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
    }
    

    public String getOrderDetailID() {
        return orderDetailID;
    }

    public void setOrderDetailID(String orderDetailID) {
        this.orderDetailID = orderDetailID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
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
    
    
}
