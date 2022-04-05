/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package filters;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.http.HttpRequest;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author peony
 */
public class AdminFilter implements Filter {
    
  

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest =(HttpServletRequest) request;
        HttpServletRequest httpResponse =(HttpServletRequest) response;
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        HttpSession session = httpRequest.getSession();
        
        AccountService as = new AccountService();
        User user = as.login(email, password);
        session.setAttribute("email", email);
        
        if (user.getRole().getRoleId() == 1 || user != null) {
            httpResponse.sendRedirect("nadmin");
          
        } else {
            httpResponse.sendRedirect("notes");
            
        }
         chain.doFilter(request, response);
    }
        
       
        
       
    }



    /**
     * Destroy method for this filter
     */
    public void destroy() {        
    }

    /**
     * Init method for this filter
     */
    public void init(FilterConfig filterConfig) {        
 
    }



}
