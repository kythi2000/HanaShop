package antdp.filters;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import antdp.dtos.RegistrationDTO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class AuthenticatedFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //kiểm tra session
        //lay session
        String url = "notfound.jsp";
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession(false);
        // lấy attribute
        //dòng này khi hết session thì ra lỗi cần fix
        RegistrationDTO account = (RegistrationDTO) session.getAttribute("REGISTRATION");
        //admin
        //lay duong dan request
        String uri = req.getRequestURI();
        //lay vi tri dung
        int lastIndexOf = uri.lastIndexOf("/");
        //lay ten resource
        String resource = uri.substring(lastIndexOf + 1);

        //----------------LIST ACTIVITIES ADMIN ROLE----------------------------------------
        List<String> resource_allow_Admin = new ArrayList<>();
        //
        resource_allow_Admin.add("admin.jsp");
        resource_allow_Admin.add("create.jsp");
        resource_allow_Admin.add("update.jsp");
        //----------------LIST ACTIVITIES Customer ROLE--------------------------------------
        List<String> resource_allow_Customer = new ArrayList<>();
        //
        resource_allow_Customer.add("search.jsp");
        resource_allow_Customer.add("viewCart.jsp");
        resource_allow_Customer.add("historyOrder");
        

        //----------------ADMIN ROLE----------------------------------------
        if (account != null && account.getRole().equals("admin")) {
            if (resource_allow_Admin.contains(resource)) {
                //request
                chain.doFilter(request, response);
                //response
            } //neu sai role thi ra login
            else {
                req.getRequestDispatcher(url).forward(request, response);
            }
        }

        //----------------Customer ROLE--------------------------------------
        if (account != null && account.getRole().equals("user")) {
            if (resource_allow_Customer.contains(resource)) {
                //request
                chain.doFilter(request, response);
                //response
            } //neu sai role thi ra login
            else {
                req.getRequestDispatcher(url).forward(request, response);
            }
        }
        List<String> resource_allow_All = new ArrayList<>();
        resource_allow_All.add("search.jsp");
        resource_allow_All.add("login.jsp");
        if (account == null) {
            if(resource_allow_All.contains(resource)){
                chain.doFilter(request, response);
            }else{
                res.sendRedirect(url);
            }
        }

    }

    @Override
    public void destroy() {

    }

}
