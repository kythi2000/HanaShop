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
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {
    
    private static final Logger LOGGER = Logger.getLogger(SearchServlet.class);

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
        
        HttpSession session = request.getSession();
        String url = "search.jsp";
        RegistrationDTO registrationDTO = (RegistrationDTO) session.getAttribute("REGISTRATION");
        if(registrationDTO != null){
            if(registrationDTO.getRole().equals("admin")){                
                url = "admin.jsp";
            }
        }
                
        String name = request.getParameter("txtName");
        String fromPrice = request.getParameter("txtFromPrice");
        String toPrice = request.getParameter("txtToPrice");
        String category = request.getParameter("txtCategory");
        String indexString = request.getParameter("index");
        
        
        int index = 0;
        if(indexString == null || indexString.equals("")){
            index = 1;
        }else{
            index = Integer.parseInt(indexString);
        }
        
        try {
            FoodDAO dao = new FoodDAO();
            int count = dao.countFoodByName(name, fromPrice, toPrice, category);
            int pageSize = 3;
            int endPage = 0;
            endPage = count / pageSize;
            if(count % pageSize != 0){
                endPage ++;
            }
            request.setAttribute("endPage", endPage);                      
            List<FoodDTO> list = dao.find(name, fromPrice, toPrice, category, index);
                    
            request.setAttribute("LISTFOOD", list);
        }catch(Exception e){
            LOGGER.error(e.getMessage());
        }finally{
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
