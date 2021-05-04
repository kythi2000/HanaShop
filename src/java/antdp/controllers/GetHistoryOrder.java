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
@WebServlet(name = "GetHistoryOrder", urlPatterns = {"/GetHistoryOrder"})
public class GetHistoryOrder extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(GetHistoryOrder.class);

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

        
        try {
            HistoryOrder historyOrder = new HistoryOrder();
            
            HttpSession session = request.getSession();
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
                List<FoodOrderDTO> listFood_Bill = new ArrayList<>();           //  new List List<FoodOrderDTO>
                for (OrderDetailDTO orderDetailDTO : listOrderDetail) {
                    FoodDTO foodDTO = orderDAO.getFoodbyID(orderDetailDTO.getFoodID());
                    float price = orderDetailDTO.getPrice();
                    int quantity = orderDetailDTO.getQuantity();
                    FoodOrderDTO foodOrderDTO = new FoodOrderDTO(foodDTO, total, price, quantity, dateOfCreate);
                    listFood_Bill.add(foodOrderDTO);
                }
                historyOrder.add(orderDTO.getOrderID(), listFood_Bill);
            }
            session.setAttribute("HistoryOrder", historyOrder);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        } finally {
            request.getRequestDispatcher("historyOrder.jsp").forward(request, response);
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
