/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.FoodDTO;
import antdp.dtos.OrderDTO;
import antdp.dtos.OrderDetailDTO;
import antdp.utilities.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author HP 840 G2
 */
public class OrderDAO {

    private Connection conn = null;
    private PreparedStatement preS = null;
    private ResultSet rs = null;

    private void closeConnection() throws Exception {
        if (rs != null) {
            rs.close();
        }
        if (preS != null) {
            preS.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public String getLastID(String username) throws Exception{
        String lastID = null;
        try{
            String sql = "select OrderID from Orders "
                    + "where DateOfCreate = (select MAX(DateOfCreate) "
                    + "from Orders where Username = ?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, username);
            rs = preS.executeQuery();
            if(rs.next()){
                lastID = rs.getString("OrderID");
            }
        }finally{
            closeConnection();
        }
        return lastID;
    }
    
    public boolean createOrder(OrderDTO order) throws Exception{
        boolean check = false;
        try{
            String sql = "insert into Orders(OrderID, Username, Total, DateOfCreate, Status) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, order.getOrderID());
            preS.setString(2, order.getUsername());
            preS.setFloat(3, order.getTotal());
            preS.setTimestamp(4, new Timestamp(new Date().getTime()));
            preS.setBoolean(5, true);
            check = preS.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public boolean createOrderDetail(OrderDetailDTO orderDetail) throws Exception{
        boolean check = false;
        try{
            String sql = "insert into OrderDetail(OrderDetailID, OrderID, FoodID, Quantity, Price) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, orderDetail.getOrderDetailID());
            preS.setString(2, orderDetail.getOrderID());
            preS.setString(3, orderDetail.getFoodID());
            preS.setInt(4,orderDetail.getQuantity());
            preS.setFloat(5, orderDetail.getPrice());
            check = preS.executeUpdate() > 0;
        }finally{
            closeConnection();
        }
        return check;
    }
    
    public List<OrderDTO> getOrderByUsername(String username) throws Exception{
        List<OrderDTO> list = null;
        try{
            String sql = "select OrderID, Total, DateOfCreate "
                    + "from Orders "
                    + "where Username = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, username);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                String orderId = rs.getString("OrderID");
                float total = rs.getFloat("Total");
                Date date = rs.getTimestamp("DateOfCreate");
                OrderDTO orderDTO = new OrderDTO(orderId, date, total);
                list.add(orderDTO);
            }
        }finally{
            closeConnection();
        }
        return list;
    }

    public List<OrderDetailDTO> getOrderDetailByOrderID(String orderID) throws Exception{
        List<OrderDetailDTO> list = null;
        try{
            String sql = "select FoodID, Quantity, Price "
                    + "from OrderDetail "
                    + "where OrderID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, orderID);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                String foodID = rs.getString("FoodID");
                int quantity = rs.getInt("Quantity");
                float price = rs.getFloat("Price");
                OrderDetailDTO dto = new OrderDetailDTO(foodID, quantity, price);
                list.add(dto);
            }
        }finally{
            closeConnection();
        }
        return list;
    }
    
    public FoodDTO getFoodbyID(String foodID) throws Exception {
        String name, des, image, cate;
        int quantity;
        float price;
        Date date;
        FoodDTO dto = null;
        String sql = "select Name, Description, Quantity, Price, Image, Category, DateOfCreate "
                + "from Foods "
                + "where FoodID = ?";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, foodID);
            rs = preS.executeQuery();
            while (rs.next()) {
                name = rs.getString("Name");
                des = rs.getString("Description");
                quantity = rs.getInt("Quantity");
                price = rs.getFloat("Price");
                date = rs.getTimestamp("DateOfCreate");
                image = rs.getString("Image");
                cate = rs.getString("Category");
                dto = new FoodDTO(foodID, name, des, image, cate, quantity, price, date);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}
