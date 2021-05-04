/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.OrderDAO;
import antdp.dtos.FoodDTO;
import antdp.dtos.FoodOrderDTO;
import antdp.dtos.HistoryOrder;
import antdp.dtos.OrderDTO;
import antdp.dtos.OrderDetailDTO;
import antdp.dtos.RegistrationDTO;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
@WebServlet(name = "SearchHistoryServlet", urlPatterns = {"/SearchHistoryServlet"})
public class SearchHistoryServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(SearchHistoryServlet.class);

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

        String name = request.getParameter("txtSearchName");
        String date = request.getParameter("txtSearchDate");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            HttpSession session = request.getSession();
            
            HistoryOrder historyOrder = new HistoryOrder();
            
            
            RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("REGISTRATION");
            String username = "guest";
            if (registrationDTO != null) {
                username = registrationDTO.getUsername();
            }
            OrderDAO orderDAO = new OrderDAO();
            List<OrderDTO> listBill = orderDAO.getOrderByUsername(username);  // get list order by username
            for (OrderDTO orderDTO : listBill) {    // chạy từng item trong bảng Order
                List<OrderDetailDTO> listOrderDetail = 
                        orderDAO.getOrderDetailByOrderID(orderDTO.getOrderID());   // get list order detail by orderID
                float total = orderDTO.getTotal();                                 // get total bảng order
                Date dateOfCreate = orderDTO.getDateOfCreate();                    // get date of create bảng orde
                List<FoodOrderDTO> listFood_Bill = new ArrayList<>();           //  new List các food trong bill
                for (OrderDetailDTO orderDetailDTO : listOrderDetail) {
                    FoodDTO foodDTO = orderDAO.getFoodbyID(orderDetailDTO.getFoodID());
                    float price = orderDetailDTO.getPrice();
                    int quantity = orderDetailDTO.getQuantity();
                    FoodOrderDTO foodOrderDTO = new FoodOrderDTO(foodDTO, total, price, quantity, dateOfCreate);
                    listFood_Bill.add(foodOrderDTO); // add food vào List<FoodOrderDTO> 
                }
                historyOrder.add(orderDTO.getOrderID(), listFood_Bill); // add List<FoodOrderDTO> vào key
            }
            
            // 
            HistoryOrder historyOrder_Search = historyOrder;
            List<String> listOrderId_remove = new ArrayList<>();

            for (String orderID : historyOrder_Search.getHistoryOrder().keySet()) {
                List<FoodOrderDTO> listFood_Bill = historyOrder_Search.getHistoryOrder().get(orderID);
                boolean check = false;
                for (FoodOrderDTO foodOrderDTO : listFood_Bill) {
                    String foodName = foodOrderDTO.getFoodInOrder().getName();
                    String dateOfCreate = sdf.format(foodOrderDTO.getDateOfCreateOrder());

                    if (foodName.contains(name.trim()) && dateOfCreate.equals(sdf.format(sdf.parse(date)))) {
                        check = true;
                    }
                }
                if (check == false) {
                    listOrderId_remove.add(orderID);
                }

            }
            for (String orderID : listOrderId_remove) {
                historyOrder_Search.remove(orderID);
            }
            session.setAttribute("HistoryOrder", historyOrder_Search);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            response.sendRedirect("historyOrder.jsp");
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
