/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.cart;

import antdp.dtos.FoodDTO;
import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author HP 840 G2
 */
public class CartObj implements Serializable{
    private String customerName;
    private HashMap<String, FoodDTO> cart;

    public CartObj() {
        this.customerName = "guest";
        this.cart = new HashMap<>();
    }

    public CartObj(String customerName, HashMap<String, FoodDTO> cart) {
        this.customerName = customerName;
        this.cart = cart;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public HashMap<String, FoodDTO> getCart() {
        return cart;
    }

    public void setCart(HashMap<String, FoodDTO> cart) {
        this.cart = cart;
    }
    
    public void addToCart(FoodDTO dto) throws Exception{
        if(this.cart.containsKey(dto.getFoodID())){
            int newQuantity = this.cart.get(dto.getFoodID()).getQuantity() + 1;
            this.cart.get(dto.getFoodID()).setQuantity(newQuantity);
        }else {
            this.cart.put(dto.getFoodID(), dto);
        }       
    }
    
    public void remove(String id) throws Exception{
        if(this.cart.containsKey(id)){
            this.cart.remove(id);
        }
    }
    public float getTotal() throws Exception{
        float result = 0;
        for(FoodDTO dTO: this.cart.values()){
            result += dTO.getQuantity() * dTO.getPrice();
        }
        return result;
    }
    public void updateCart(String id, int quantity) throws Exception{
        if(this.cart.containsKey(id)){
            this.cart.get(id).setQuantity(quantity);
        }
    }
    
}
