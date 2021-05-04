/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HP 840 G2
 */
public class MainController extends HttpServlet {

    private final String GET_ALL_FOOD = "GetAllFoodServlet";
    private final String SEARCH_SERVLET = "SearchServlet";
    private final String LOGIN_SERVLET = "LoginServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String DELETE_SERVLET = "DeleteServlet";
    private final String UPDATE_SERVLET = "update.jsp";
    private final String CREATE_SERVLET = "CreateServlet";
    private final String ADD_TO_CART_SERVLET = "AddFoodToCartServlet";
    private final String ACTION_CART_SERVLET = "ActionCartServlet";
    private final String REMOVE_FOOD_FROM_CART_SERVLET = "RemoveFoodFromCartServlet";
    private final String VIEW_CART = "viewCart.jsp";
    private final String VIEW_HISTORY = "GetHistoryOrder";
    private final String SEARCH_HISTORY_SERVLET = "SearchHistoryServlet";

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

        String url = GET_ALL_FOOD;
        String btnAction = request.getParameter("btnAction");
        try {
            if (btnAction.equals("Login")) {
                url = LOGIN_SERVLET;
            } else if (btnAction.equals("Search")) {
                url = SEARCH_SERVLET;
            } else if (btnAction.equals("Update")) {
                url = UPDATE_SERVLET;
            } else if (btnAction.equals("Delete")) {
                url = DELETE_SERVLET;
            } else if (btnAction.equals("Create")) {
                url = CREATE_SERVLET;
            } else if (btnAction.equals("Add")) {
                url = ADD_TO_CART_SERVLET;
            } else if (btnAction.equals("Update Cart") || btnAction.equals("Order")) {
                url = ACTION_CART_SERVLET;
            } else if (btnAction.equals("Remove Cart")) {
                url = REMOVE_FOOD_FROM_CART_SERVLET;
            } else if (btnAction.equals("Logout")) {
                url = LOGOUT_SERVLET;
            } else if (btnAction.equals("ViewCart")) {
                url = VIEW_CART;
            } else if (btnAction.equals("History")) {
                url = VIEW_HISTORY;
            } else if (btnAction.equals("SearchHistory")) {
                url = SEARCH_HISTORY_SERVLET;
            }
        } catch (Exception e) {
            log("Error at MainController:" + e.getMessage());
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
