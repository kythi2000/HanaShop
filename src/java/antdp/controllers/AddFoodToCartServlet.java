/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.cart.CartObj;
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
@WebServlet(name = "AddFoodToCartServlet", urlPatterns = {"/AddFoodToCartServlet"})
public class AddFoodToCartServlet extends HttpServlet {

    private static final String GET_ALL_FOOD_SERVLET = "GetAllFoodServlet";
    private static final String SEARCH_SEVLET = "SearchServlet";
    private static final String ERROR_PAGE = "error.jsp";
    private static final Logger LOGGER = Logger.getLogger(AddFoodToCartServlet.class);
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

        String foodID = request.getParameter("txtFoodID");
        String url = ERROR_PAGE;
        String searchValue = request.getParameter("txtSearchValue");
        if(searchValue == null){
            url = GET_ALL_FOOD_SERVLET;
        }else{
            url = SEARCH_SEVLET;
        }
        try {
            FoodDAO dao = new FoodDAO();
            FoodDTO dto = dao.getFoodbyID(foodID);
            dto.setQuantity(1);
            
            HttpSession session = request.getSession();
            CartObj cartObj = (CartObj) session.getAttribute("Cart");
            RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("REGISTRATION");
            String username = null;
            if (registrationDTO != null) {
                username = registrationDTO.getUsername();
            }
            if (cartObj == null) {
                cartObj = new CartObj();
                if (username != null) {
                    cartObj.setCustomerName(username);
                }
            }
            cartObj.addToCart(dto);
            session.setAttribute("Cart", cartObj);
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
