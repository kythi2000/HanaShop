/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.daos;

import antdp.dtos.RegistrationDTO;
import antdp.utilities.DBHelpers;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HP 840 G2
 */
public class RegistrationDAO {

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

    public String checkLogin(String username, String password) throws Exception {
        String role = "failed";
        String sql = "select Role from Registration where Username = ? and Password = ? and Status = 'true'";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, username);
            preS.setString(2, password);
            rs = preS.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }

    public RegistrationDTO getRegistrationByKey(String username) throws Exception {
        RegistrationDTO dto = null;
        String fullname, role;
        String sql = "select Fullname, Role from Registration where Username = ? and Status = 'true'";
        try {
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, username);
            rs = preS.executeQuery();
            if (rs.next()) {
                fullname = rs.getString("Fullname");
                role = rs.getString("Role");
                dto = new RegistrationDTO(username, fullname, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public boolean createAccountGoogle(String username) throws Exception {
        boolean check = false;
        try {
            String sql = "insert into Registration(Username, Password, Fullname, Role, Status) "
                    + "values(?,?,?,?,?)";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, username);
            preS.setString(2, "1");
            preS.setString(3, username);
            preS.setString(4, "user");
            preS.setBoolean(5, true);
            check = preS.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return check;
    }

    public boolean findAccount(String username) throws Exception {
        boolean check = false;
        try {
            String sql = "select Username from Registration where Username = ?";
            conn = DBHelpers.makeConnection();
            preS = conn.prepareStatement(sql);
            preS.setString(1, username);
            rs = preS.executeQuery();
            if (rs.next()) {
                check = true;
            }
        } finally {
            closeConnection();
        }
        return check;
    }

}
