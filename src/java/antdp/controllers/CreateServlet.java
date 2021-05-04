/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.FoodDAO;
import antdp.dtos.FoodDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "CreateServlet", urlPatterns = {"/CreateServlet"})
public class CreateServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(CreateServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String name = request.getParameter("txtFoodName");
        String quantityString = request.getParameter("txtQuantity");
        String category = request.getParameter("txtCategory");
        String des = request.getParameter("txtDescription");
        String image = request.getParameter("txtImage");
        String priceString = request.getParameter("txtPrice");
        int quantity = Integer.parseInt(quantityString);
        float price = Float.parseFloat(priceString);

        String url = "error.jsp";
        try {
            FoodDAO dao = new FoodDAO();
            String foodID = "";
            String lassID = dao.getLastFoodID();
            if (lassID == null) {
                foodID = "F-" + "1";
            }else {
                String[] tmp = lassID.split("-");
                foodID = "F-" + (Integer.parseInt(tmp[1]) + 1);
            }
            FoodDTO dto = new FoodDTO(foodID, name, des, image, category, quantity, price);
            if(dao.create(dto)){
                url = "GetAllFoodServlet";
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally{
            response.sendRedirect(url);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
