/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdp.controllers;

import antdp.daos.RegistrationDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import stackjava.com.accessgoogle.common.GooglePojo;
import stackjava.com.accessgoogle.common.GoogleUtils;

/**
 *
 * @author Admin
 */
@WebServlet(name = "LoginGoogleServlet", urlPatterns = {"/LoginGoogleServlet"})
public class LoginGoogleServlet extends HttpServlet {

    
    private static final Logger LOGGER = Logger.getLogger(LoginGoogleServlet.class);
    private static final long serialVersionUID = 1L;

    public LoginGoogleServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code == null || code.isEmpty()) {
            RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
            dis.forward(request, response);
        } else {
            String accessToken = GoogleUtils.getToken(code);
            GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
            request.setAttribute("id", googlePojo.getId());
            String email = googlePojo.getEmail();
            request.setAttribute("link", googlePojo.getLink());
            request.setAttribute("nameF", googlePojo.getFamily_name());
            request.setAttribute("nameG", googlePojo.getGiven_name());
            try {
                HttpSession session = request.getSession();
                RegistrationDAO dao = new RegistrationDAO();
                if (email != null) {
                    if (dao.findAccount(email)) {
                        session.setAttribute("REGISTRATION", dao.getRegistrationByKey(email));
                    } else {
                        if (dao.createAccountGoogle(email)) {
                            session.setAttribute("REGISTRATION", dao.getRegistrationByKey(email));
                        }
                    }
                }
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            } finally {
                response.sendRedirect("GetAllFoodServlet");
            }

        }
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
        doGet(request, response);
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
