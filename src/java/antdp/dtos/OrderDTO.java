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
public class OrderDTO implements Serializable{
    private String orderID, username;
    private Date dateOfCreate;
    private float total;
    private boolean status;

    public OrderDTO(String orderID, String username, Date dateOfCreate, float total) {
        this.orderID = orderID;
        this.username = username;
        this.dateOfCreate = dateOfCreate;
        this.total = total;
    }

    public OrderDTO(String orderID, String username, float total) {
        this.orderID = orderID;
        this.username = username;
        this.total = total;
    }

    public OrderDTO(String orderID, Date dateOfCreate, float total) {
        this.orderID = orderID;
        this.dateOfCreate = dateOfCreate;
        this.total = total;
    }

    
    
    
    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDateOfCreate() {
        return dateOfCreate;
    }

    public void setDateOfCreate(Date dateOfCreate) {
        this.dateOfCreate = dateOfCreate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
      
}
