/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.dtos;

import java.util.Date;

/**
 *
 * @author HP 840 G2
 */
public class FoodOrderDTO {
    private FoodDTO foodInOrder;
    private float total, price;
    private int quantity;
    private Date dateOfCreateOrder;

    public FoodOrderDTO(FoodDTO foodInOrder, float total, float price, int quantity, Date dateOfCreate) {
        this.foodInOrder = foodInOrder;
        this.total = total;
        this.price = price;
        this.quantity = quantity;
        this.dateOfCreateOrder = dateOfCreate;
    }

    public FoodDTO getFoodInOrder() {
        return foodInOrder;
    }

    public void setFoodInOrder(FoodDTO foodInOrder) {
        this.foodInOrder = foodInOrder;
    }

    public Date getDateOfCreateOrder() {
        return dateOfCreateOrder;
    }

    public void setDateOfCreateOrder(Date dateOfCreateOrder) {
        this.dateOfCreateOrder = dateOfCreateOrder;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
