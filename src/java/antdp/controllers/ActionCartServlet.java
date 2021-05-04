/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.cart.CartObj;
import antdp.daos.FoodDAO;
import antdp.daos.OrderDAO;
import antdp.dtos.FoodDTO;
import antdp.dtos.OrderDTO;
import antdp.dtos.OrderDetailDTO;
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
@WebServlet(name = "ActionCartServlet", urlPatterns = {"/ActionCartServlet"})
public class ActionCartServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ActionCartServlet.class);

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

        String[] listQuantity = request.getParameterValues("txtQuantity");
        String btnAction = request.getParameter("btnAction");
        String[] listID = request.getParameterValues("txtFoodID");
        String url = "MainController?btnAction=ViewCart";
        if (btnAction == null) {
            btnAction = "";
        }
        try {
            HttpSession session = request.getSession();
            CartObj cartObj = (CartObj) session.getAttribute("Cart");
            FoodDAO foodDAO = new FoodDAO();
            if (btnAction.equals("Update Cart")) {
                for (int i = 0; i < listID.length; i++) {
                    cartObj.updateCart(listID[i], Integer.parseInt(listQuantity[i]));
                }
                session.setAttribute("Cart", cartObj);

            } else if (btnAction.equals("Order")) {
                boolean check = true;
                int index = 0;
                for (FoodDTO foodCart : cartObj.getCart().values()) {
                    FoodDTO foodDTO = foodDAO.getFoodbyID(foodCart.getFoodID());
                    if (Integer.parseInt(listQuantity[index]) > foodDTO.getQuantity()) {
                        check = false;
                    }
                    index++;
                }
                if (check) {
                    url = "MainController";
                    OrderDAO dao = new OrderDAO();
                    String lastID = dao.getLastID(cartObj.getCustomerName());
                    String orderID = "";            // orderId = OD-customerName-number
                    if (lastID == null) {
                        orderID = "OD-" + cartObj.getCustomerName() + "-1";
                    } else {
                        String[] tmp = lastID.split("-");
                        orderID = "OD-" + cartObj.getCustomerName() + "-" + (Integer.parseInt(tmp[2]) + 1);
                    }

                    OrderDTO orderDTO = new OrderDTO(orderID, cartObj.getCustomerName(), cartObj.getTotal());
                    if (dao.createOrder(orderDTO)) {         // nếu tạo bill thành công thì add bill detail
                        int count = 0;
                        for (FoodDTO foodCart : cartObj.getCart().values()) {
                            String orderDetailID = orderID + "-" + count;       // orderDetailID = orderID-number
                            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDetailID, orderID,
                                    foodCart.getFoodID(), foodCart.getQuantity(), foodCart.getPrice());
                            if (dao.createOrderDetail(orderDetailDTO)) {
                                FoodDTO foodDTO = foodDAO.getFoodbyID(foodCart.getFoodID());
                                int quantity = foodDTO.getQuantity() - foodCart.getQuantity();
                                if (quantity > 0) {     //quantity > 0, update lại số lượng food trong DB
                                    foodCart.setStatus(true);
                                    foodCart.setQuantity(quantity);
                                    foodDAO.update(foodCart);
                                } else {      // quantity = 0
                                    foodCart.setStatus(false);       // set ẩn food
                                    foodCart.setQuantity(quantity);  // update lại số lượng food trong DB
                                    foodDAO.update(foodCart);
                                }
                            }
                            count++;
                        }
                    }
                    session.removeAttribute("Cart");
                } else {
                    request.setAttribute("ERROR", "Food is out of stock.");
                }
            } else if (btnAction.equals("")) {
                url = "MainController";
                OrderDAO dao = new OrderDAO();
                String lastID = dao.getLastID(cartObj.getCustomerName());
                String orderID = "";            // orderId = OD-customerName-number
                if (lastID == null) {
                    orderID = "OD-" + cartObj.getCustomerName() + "-1";
                } else {
                    String[] tmp = lastID.split("-");
                    orderID = "OD-" + cartObj.getCustomerName() + "-" + (Integer.parseInt(tmp[2]) + 1);
                }

                OrderDTO orderDTO = new OrderDTO(orderID, cartObj.getCustomerName(), cartObj.getTotal());
                if (dao.createOrder(orderDTO)) {         // nếu tạo bill thành công thì add bill detail
                    int count = 0;
                    for (FoodDTO foodCart : cartObj.getCart().values()) {
                        String orderDetailID = orderID + "-" + count;       // orderDetailID = orderID-number
                        OrderDetailDTO orderDetailDTO = new OrderDetailDTO(orderDetailID, orderID,
                                foodCart.getFoodID(), foodCart.getQuantity(), foodCart.getPrice());
                        if (dao.createOrderDetail(orderDetailDTO)) {
                            FoodDTO foodDTO = foodDAO.getFoodbyID(foodCart.getFoodID());
                            int quantity = foodDTO.getQuantity() - foodCart.getQuantity();
                            if (quantity > 0) {     //quantity > 0, update lại số lượng food trong DB
                                foodCart.setStatus(true);
                                foodCart.setQuantity(quantity);
                                foodDAO.update(foodCart);
                            } else {      // quantity = 0
                                foodCart.setStatus(false);       // set ẩn food
                                foodCart.setQuantity(quantity);  // update lại số lượng food trong DB
                                foodDAO.update(foodCart);
                            }
                        }
                        count++;
                    }
                }
                session.removeAttribute("Cart");
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
