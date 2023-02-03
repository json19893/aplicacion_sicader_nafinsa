package com.org.backend_nafinsa.servlets;

import com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean;
import com.google.gson.Gson;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.client.RestTemplate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@Slf4j
//@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@WebServlet(urlPatterns = "/success")
public class ejemploSuccess extends HttpServlet {
    Utilidades utl= new Utilidades();
    Gson gson = new Gson();
    /**
     *
     */
    SSOEnablerJspBean sso = new SSOEnablerJspBean();
    private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("************GETTTTTTTTTTTTT* ejemploSuccess");
        HttpSession sesion= request.getSession();
        String user = "";
        try{
        user =request.getSession().getAttribute("username").toString();
        }catch (Exception e){
            System.out.println("************errrorr:"+e);
        }
        String pass = request.getParameter("password");
        Cookie[] cookies = request.getCookies();
        System.out.println("************user:"+user);
        System.out.println("************pass:"+pass);
        String nombre;
        System.out.println("request.getContextPath():"+ request.getContextPath());
        System.out.println("request.getServerName():"+ request.getServerName());
        System.out.println("request.getServerName():"+ request.getServerPort());
        System.out.println("request.getRemoteHost():"+ request.getRemoteHost());
        System.out.println("request.getPathInfo():"+ request.getPathInfo());
        System.out.println("request.getServletPath():"+ request.getServletPath());
        System.out.println("************Sesion**************************************************");
        Enumeration nombresSesion = sesion.getAttributeNames();
        while (nombresSesion.hasMoreElements()) {
            String nombreSesion = nombresSesion.nextElement().toString();
            Object valor = sesion.getAttribute(nombreSesion);
            System.out.println("nombre sesion:"+ nombreSesion);
            System.out.println("valor sesion:"+ valor);
        }
        System.out.println("************Sesion**************************************************");
        if (cookies!= null){
            for (int i = 0; i < cookies.length; i++){
                System.out.println("************cookie");
                System.out.println("valor cooki:"+cookies[i].getValue());
                System.out.println("nombre cooki:"+cookies[i].getName());
                System.out.println("************cookie");
            }
            try {
                //EJEMPLO DE FLUJO REAL---------------------------------------------------------------
                sso.setPartnerAppCookie(request, response);
                //EJEMPLO DE FLUJO REAL---------------------------------------------------------------
                String token = utl.getJWTToken(request.getParameter("usuario"));
                String urlEnvio= "https://"+request.getServerName()+":/"+request.getServerPort()+"/sicader/login?token="+token+"&user="+user;
                //EJEMPLO DE FLUJO MANUAL---------------------------------------------------------------
                //response.sendRedirect("http://localhost:3000?token="+token+"&user="+user);
                //EJEMPLO DE FLUJO REAL---------------------------------------------------------------
                response.sendRedirect(urlEnvio);
            } catch(Exception e) {
                System.out.println("************cookie errorrrrrrrrrrrr");
                try {
                    throw new Exception("El tiempo para indicar el usuario y contraseña ha sido excedido. Cierre esta ventanae intente nuevamente");
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }

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