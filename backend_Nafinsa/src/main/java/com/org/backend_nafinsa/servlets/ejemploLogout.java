package com.org.backend_nafinsa.servlets;

import com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean;
import com.google.gson.Gson;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@WebServlet(urlPatterns = "/ejemploLogout")
public class ejemploLogout extends HttpServlet {
    Utilidades utl= new Utilidades();
    Gson gson = new Gson();
    /**
     *
     */
    SSOEnablerJspBean sso = new SSOEnablerJspBean();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session=  request.getSession();
        try {
            //EJEMPLO DE FLUJO MANUAL---------------------------------------------------------------
           // session.invalidate();
           // Cookie[] cookies = request.getCookies();
           // if (cookies != null)
           //     for (Cookie cookie : cookies) {
           //         cookie.setValue("");
           //         cookie.setPath("/");
           //         cookie.setMaxAge(0);
           //         response.addCookie(cookie);
           //     }
            //EJEMPLO DE FLUJO MANUAL---------------------------------------------------------------

            //EJEMPLO DE FLUJO REAL---------------------------------------------------------------
            sso.removeJspAppCookies(request,response);
            String logoutURL = sso.getSingleSignOffUrl(request);
            //EJEMPLO DE FLUJO REAL---------------------------------------------------------------
            String scheme = request.getScheme();
            String serverName = request.getServerName();
            int portNumber = request.getServerPort();
            String contextPath = request.getContextPath();
            response.sendRedirect("/ejemplo");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("************POSTTTTTTTTTTTTTT*");
        Cookie cookie = new Cookie("SSSSSSSSS", "SS");
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        Cookie name = new Cookie("username", req.getParameter("username"));
        resp.addCookie(cookie);
        /*
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
         */
        Cookie[] cookies = req.getCookies();
        String nombre;
        if(cookies!= null){
            for (int i = 0; i < cookies.length; i++){
                System.out.println("************cookie");
                System.out.println("valor cooki:"+cookies[i].getValue());
                System.out.println("nombre cooki:"+cookies[i].getName());
                System.out.println("************cookie");
            }
        }else {
            System.out.println("SIN COOKIESSSSSSSSSSSSSSSSSSSSSSSS");
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