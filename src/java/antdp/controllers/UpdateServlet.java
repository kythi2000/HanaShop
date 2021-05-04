/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.FoodDAO;
import antdp.dtos.FoodDTO;
import antdp.dtos.RegistrationDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author HP 840 G2
 */
@WebServlet(name = "UpdateServlet", urlPatterns = {"/UpdateServlet"})
public class UpdateServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(UpdateServlet.class);
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

        String id = request.getParameter("txtFoodID");
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
            FoodDTO foodDTO = new FoodDTO(id, name, des, image, category, quantity, price);
            foodDTO.setStatus(true);
            if (dao.update(foodDTO)) {               
                url = "GetAllFoodServlet";
                HttpSession session = request.getSession();
                RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("REGISTRATION");
                String username = registrationDTO.getUsername();
                String updationID = "";
                String lassID = dao.getLastUpdationID();
                if (lassID == null) {
                    updationID = "Updation-" + "1";
                } else {
                    String[] tmp = lassID.split("-");
                    updationID = "Updation-" + (Integer.parseInt(tmp[1]) + 1);
                }
                dao.createUpdation(updationID, username, id, "Update");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
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
