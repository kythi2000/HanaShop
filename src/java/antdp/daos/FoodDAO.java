/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.CategoryDTO;
import antdp.dtos.FoodDTO;
import antdp.utilities.DBHelpers;
import java.io.Serializable;
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
public class FoodDAO implements Serializable {

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

    public List<FoodDTO> getAllFood(int index) throws Exception {
        List<FoodDTO> list = null;
        String name, foodID, des, image, cate;
        int quantity;
        float price;
        Date date;
        FoodDTO dto = null;
        list = new ArrayList<>();
        String sql = "with listSearch as(select ROW_NUMBER() over (order by DateOfCreate asc) as r, * "
                + "from Foods where Status = 'true')"
                + "select FoodID, Name, Description, Quantity, Price, Image, Category, DateOfCreate "
                + "from listSearch "
                + "where Status = 'true' and r between ?*3 - 2 and ?*3";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setInt(1, index);
            preS.setInt(2, index);
            rs = preS.executeQuery();
            while (rs.next()) {
                name = rs.getString("Name");
                foodID = rs.getString("FoodID");
                des = rs.getString("Description");
                quantity = rs.getInt("Quantity");
                price = rs.getFloat("Price");
                date = rs.getTimestamp("DateOfCreate");
                image = rs.getString("Image");
                cate = rs.getString("Category");
                dto = new FoodDTO(foodID, name, des, image, cate, quantity, price, date);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countAllFood() throws Exception {
        int count = 0;
        String sql = "select count(*) from Foods where Status = 'true'";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }
    
    public List<CategoryDTO> getAllCategory() throws Exception{
        List<CategoryDTO> list = null;
        String cate, des;
        try{
            String sql = "select Category, Description from Category";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            list = new ArrayList<>();
            while(rs.next()){
                cate = rs.getString("Category");
                des = rs.getString("Description");
                CategoryDTO dTO = new CategoryDTO(cate, des);
                list.add(dTO);
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
                + "where Status = 'true' and FoodID = ?";
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

    public List<FoodDTO> find(String name, String fromPriceString,
            String toPriceString, String category, int index) throws Exception {
        List<FoodDTO> list = null;
        String foodName, foodID, des, image, cate;
        int quantity;
        float price;
        Date date;
        FoodDTO dto = null;
        list = new ArrayList<>();

        if(category.equals("Choose category")){
            category = "";
        }
        String sql = "";

        try {
            if (!fromPriceString.equals("") && !toPriceString.equals("")) {
                float fromPrice = Float.parseFloat(fromPriceString);
                float toPrice = Float.parseFloat(toPriceString);
                sql = "with listSearch as(select ROW_NUMBER() over (order by DateOfCreate asc) as r, * "
                        + "from Foods "
                        + "where Status = 'true' and Name like ? and Category like ? and Price between ? and ?) "
                        + "select FoodID, Name, Description, Quantity, Price, Image, Category, DateOfCreate "
                        + "from listSearch "
                        + "where Status = 'true' and r between ?*3 - 2 and ?*3";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                preS.setFloat(3, fromPrice);
                preS.setFloat(4, toPrice);
                preS.setInt(5, index);
                preS.setInt(6, index);
                rs = preS.executeQuery();
                while (rs.next()) {
                    foodName = rs.getString("Name");
                    foodID = rs.getString("FoodID");
                    des = rs.getString("Description");
                    quantity = rs.getInt("Quantity");
                    price = rs.getFloat("Price");
                    date = rs.getTimestamp("DateOfCreate");
                    image = rs.getString("Image");
                    cate = rs.getString("Category");
                    dto = new FoodDTO(foodID, foodName, des, image, cate, quantity, price, date);
                    list.add(dto);
                }

            } else if (!fromPriceString.equals("") && toPriceString.equals("")) {
                float fromPrice = Float.parseFloat(fromPriceString);
                sql = "with listSearch as(select ROW_NUMBER() over (order by DateOfCreate asc) as r, * "
                        + "from Foods "
                        + "where Status = 'true' and Name like ? and Category like ? and Price > ?) "
                        + "select FoodID, Name, Description, Quantity, Price, Image, Category, DateOfCreate "
                        + "from listSearch "
                        + "where Status = 'true' and r between ?*3 - 2 and ?*3";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                preS.setFloat(3, fromPrice);
                preS.setInt(4, index);
                preS.setInt(5, index);
                rs = preS.executeQuery();
                while (rs.next()) {
                    foodName = rs.getString("Name");
                    foodID = rs.getString("FoodID");
                    des = rs.getString("Description");
                    quantity = rs.getInt("Quantity");
                    price = rs.getFloat("Price");
                    date = rs.getTimestamp("DateOfCreate");
                    image = rs.getString("Image");
                    cate = rs.getString("Category");
                    dto = new FoodDTO(foodID, foodName, des, image, cate, quantity, price, date);
                    list.add(dto);
                }
            } else if (fromPriceString.equals("") && !toPriceString.equals("")) {
                float toPrice = Float.parseFloat(toPriceString);
                sql = "with listSearch as(select ROW_NUMBER() over (order by DateOfCreate asc) as r, * "
                        + "from Foods "
                        + "where Status = 'true' and Name like ? and Category like ? and Price < ?) "
                        + "select FoodID, Name, Description, Quantity, Price, Image, Category, DateOfCreate "
                        + "from listSearch "
                        + "where Status = 'true' and r between ?*3 - 2 and ?*3";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                preS.setFloat(3, toPrice);
                preS.setInt(4, index);
                preS.setInt(5, index);
                rs = preS.executeQuery();
                while (rs.next()) {
                    foodName = rs.getString("Name");
                    foodID = rs.getString("FoodID");
                    des = rs.getString("Description");
                    quantity = rs.getInt("Quantity");
                    price = rs.getFloat("Price");
                    date = rs.getTimestamp("DateOfCreate");
                    image = rs.getString("Image");
                    cate = rs.getString("Category");
                    dto = new FoodDTO(foodID, foodName, des, image, cate, quantity, price, date);
                    list.add(dto);
                }
            } else if (fromPriceString.equals("") && toPriceString.equals("")) {
                sql = "with listSearch as(select ROW_NUMBER() over (order by DateOfCreate asc) as r, * "
                        + "from Foods "
                        + "where Status = 'true' and Name like ? and Category like ?) "
                        + "select FoodID, Name, Description, Quantity, Price, Image, Category, DateOfCreate "
                        + "from listSearch "
                        + "where Status = 'true' and r between ?*3 - 2 and ?*3";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                preS.setInt(3, index);
                preS.setInt(4, index);
                rs = preS.executeQuery();
                while (rs.next()) {
                    foodName = rs.getString("Name");
                    foodID = rs.getString("FoodID");
                    des = rs.getString("Description");
                    quantity = rs.getInt("Quantity");
                    price = rs.getFloat("Price");
                    date = rs.getTimestamp("DateOfCreate");
                    image = rs.getString("Image");
                    cate = rs.getString("Category");
                    dto = new FoodDTO(foodID, foodName, des, image, cate, quantity, price, date);
                    list.add(dto);
                }
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public int countFoodByName(String name, String fromPriceString, String toPriceString, String category) throws Exception {
        int count = 0;
        String sql = "";
        if(category.equals("Choose category")){
            category = "";
        }
        try {
            
            if (!fromPriceString.equals("") && !toPriceString.equals("")) {
                float fromPrice = Float.parseFloat(fromPriceString);
                float toPrice = Float.parseFloat(toPriceString);
                sql = "select count(*) from Foods where Status = 'true' "
                        + "and Name like ? and Category like ? and Price between ? and ?";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                preS.setFloat(3, fromPrice);
                preS.setFloat(4, toPrice);
                rs = preS.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }

            } else if (!fromPriceString.equals("") && toPriceString.equals("")) {
                float fromPrice = Float.parseFloat(fromPriceString);
                sql = "select count(*) from Foods where Status = 'true' "
                        + "and Name like ? and Category like ? and Price > ?";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                preS.setFloat(3, fromPrice);
                rs = preS.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } else if (fromPriceString.equals("") && !toPriceString.equals("")) {
                float toPrice = Float.parseFloat(toPriceString);
                sql = "select count(*) from Foods where Status = 'true' "
                        + "and Name like ? and Category like ? and Price < ?";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                preS.setFloat(3, toPrice);
                rs = preS.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            } else if (fromPriceString.equals("") && toPriceString.equals("")) {
                sql = "select count(*) from Foods where Status = 'true' "
                        + "and Name like ? and Category like ?";

                conn = DBHelpers.makeConnection();
                preS = conn.prepareStatement(sql);
                preS.setString(1, "%" + name + "%");
                preS.setString(2, "%" + category + "%");
                rs = preS.executeQuery();
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public int countFoodByCategory(String category) throws Exception {
        int count = 0;
        String sql = "select count(*) from Foods where Category = ? and Status = 'true'";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, category);
            rs = preS.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
        } finally {
            closeConnection();
        }
        return count;
    }

    public List<FoodDTO> getFoodsByCategory(String cate, int index) throws Exception {
        List<FoodDTO> list = null;
        String name, foodID, des, image;
        int quantity;
        float price;
        Date date;
        FoodDTO dto = null;
        list = new ArrayList<>();
        String sql = "with listSearch as(select ROW_NUMBER() over (order by DateOfCreate desc) as r, * "
                + "from Foods "
                + "where Category = ?) "
                + "select FoodID, Name, Description, Quantity, Price, Image, Category, DateOfCreate "
                + "from listSearch "
                + "where Status = 'true' and r between ?*2 - 1 and ?*2";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, cate);
            preS.setInt(2, index);
            preS.setInt(3, index);
            rs = preS.executeQuery();
            while (rs.next()) {
                name = rs.getString("Name");
                foodID = rs.getString("FoodID");
                des = rs.getString("Description");
                quantity = rs.getInt("Quantity");
                price = rs.getFloat("Price");
                date = rs.getTimestamp("DateOfCreate");
                image = rs.getString("Image");
                cate = rs.getString("Category");
                dto = new FoodDTO(foodID, name, des, image, cate, quantity, price, date);
                list.add(dto);
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public boolean create(FoodDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Foods(FoodID, Name, Description, Quantity, Price, "
                    + "Image, Category, DateOfCreate, Status) "
                    + "values(?,?,?,?,?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, dto.getFoodID());
            preS.setString(2, dto.getName());
            preS.setString(3, dto.getDescription());
            preS.setInt(4, dto.getQuantity());
            preS.setFloat(5, dto.getPrice());
            preS.setString(6, dto.getImage());
            preS.setString(7, dto.getCategory());
            preS.setTimestamp(8, new Timestamp(new Date().getTime()));
            preS.setBoolean(9, true);
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean update(FoodDTO dto) throws Exception {
        boolean check = false;
        try {
            String sql = "update Foods set Name = ?, Description = ?, Quantity = ?, "
                    + "Price = ?, Image = ?, Category = ?, Status = ? "
                    + "where FoodID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);

            preS.setString(1, dto.getName());
            preS.setString(2, dto.getDescription());
            preS.setInt(3, dto.getQuantity());
            preS.setFloat(4, dto.getPrice());
            preS.setString(5, dto.getImage());
            preS.setString(6, dto.getCategory());
            preS.setBoolean(7, dto.isStatus());
            preS.setString(8, dto.getFoodID());
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean delete(String id) throws Exception {
        boolean check = false;
        try {
            String sql = "update Foods set Status = 'false' "
                    + "where FoodID = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, id);
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String getLastFoodID() throws Exception {
        String foodID = null;
        String sql = "select FoodID from Foods "
                + "where DateOfCreate = (select MAX(DateOfCreate) "
                + "from Foods)";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            if (rs.next()) {
                foodID = rs.getString("FoodID");
            }
        } finally {
            closeConnection();
        }
        return foodID;
    }

    public boolean createUpdation(String id, String username, String foodID, String action) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Updation(UpdationID, Username, FoodID, DateOfCreate, Action) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, id);
            preS.setString(2, username);
            preS.setString(3, foodID);
            preS.setTimestamp(4, new Timestamp(new Date().getTime()));
            preS.setString(5, action);
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public String getLastUpdationID() throws Exception {
        String updationID = null;
        String sql = "select UpdationID from Updation "
                + "where DateOfCreate = (select MAX(DateOfCreate) "
                + "from Updation)";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            rs = preS.executeQuery();
            if (rs.next()) {
                updationID = rs.getString("UpdationID");
            }
        } finally {
            closeConnection();
        }
        return updationID;
    }
}
