package com.org.backend_nafinsa.servlets;

import com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean;
import com.google.gson.Gson;
import com.org.backend_nafinsa.dto.UsuarioToken;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.extern.slf4j.Slf4j;
import oracle.security.sso.enabler.SSOEnablerException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@WebServlet(urlPatterns = "/ejemplo")
public class ejemploLogin extends HttpServlet {
    Utilidades utl= new Utilidades();
    Gson gson = new Gson();
    /**
     *
     */
    com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean sso = new SSOEnablerJspBean();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        String nombre;
        if (cookies!= null){
            for (int i = 0; i < cookies.length; i++){
                System.out.println("************cookie");
                System.out.println("valor cooki:"+cookies[i].getValue());
                System.out.println("nombre cooki:"+cookies[i].getName());
                System.out.println("************cookie");
            }
        }


        System.out.println(request.getPathInfo());
        System.out.println(request.getServletPath());
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String htmlRespone = "<form action=\"ejemplo\" method=\"post\">  ";
        htmlRespone += "Name:<input type=\"text\" name=\"username\"/><br/><br/>  ";
        htmlRespone += "Password:<input type=\"password\" name=\"password\"/><br/><br/>  ";
        htmlRespone += "<input type=\"submit\" value=\"login\"/>";
        htmlRespone += "</form>";
        writer.println(htmlRespone);
        //request.getRequestDispatcher("/WEB-INF/jsp/loginejemplo.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        String ssoUserInfo = null;
        try {
            ssoUserInfo = sso.getSSOUserInfo(req, resp);
        } catch (SSOEnablerException e) {
            throw new RuntimeException(e);
        }
        if ("edu4java".equals(user) && "eli4java".equals(pass)) {
            response(resp, "login ok");
        } else {
            response(resp, "invalid login");
        }

        Cookie[] cookies = req.getCookies();
        String nombre;
        if(cookies!= null){
            for (int i = 0; i < cookies.length; i++){
                System.out.println("************cookie");
                System.out.println("valor cooki:"+cookies[i].getValue());
                System.out.println("nombre cooki:"+cookies[i].getName());
                System.out.println("************cookie");
            }
        }
    }

    private void response(HttpServletResponse resp, String msg)
            throws IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<t1>" + msg + "</t1>");
        out.println("</body>");
        out.println("</html>");
    }
}