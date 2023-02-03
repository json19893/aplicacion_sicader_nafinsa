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
@WebServlet(urlPatterns = "/init")
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
        System.out.println("************GETTTTTTTTTTTTT*");
        System.out.println("************request.getProtocol():"+request.getProtocol());
        System.out.println("https://"+ request.getServerName()+":"+request.getServerPort()+"/sicader-api/success");


        Cookie[] cookies = request.getCookies();
        String nombre;
        if (cookies!= null){
            for (int i = 0; i < cookies.length; i++){
                System.out.println("************cookie");
                System.out.println("valor cooki:"+cookies[i].getValue());
                System.out.println("nombre cooki:"+cookies[i].getName());
                System.out.println("************cookie");
            }
            response.sendRedirect("https://"+ request.getServerName()+":"+request.getServerPort()+"/sicader-api/success");
        }
        System.out.println("******************************************************************");
        System.out.println("request.getContextPath():"+ request.getContextPath());
        System.out.println("request.getServerName():"+ request.getServerName());
        System.out.println("request.getRemoteHost():"+ request.getRemoteHost());
        System.out.println("request.getPathInfo():"+ request.getPathInfo());
        System.out.println("request.getServletPath():"+ request.getServletPath());
        System.out.println(request.getPathInfo());
        System.out.println(request.getServletPath());
        System.out.println(request.getContextPath()+ request.getServerPort() );
        System.out.println("******************************************************************");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String htmlRespone = "        <!DOCTYPE html>\r\n"
                + "        <html>\r\n"
                + "          <head>\r\n"
                + "            <style>\r\n"
                + "        		body {\r\n"
                + "        		  background-color: #000;\r\n"
                + "        		  font-family: \"Roboto\"\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.container {\r\n"
                + "        		  position: relative;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.container::before {\r\n"
                + "        		  position: absolute;\r\n"
                + "        		  top: 0;\r\n"
                + "        		  left: 0;\r\n"
                + "        		  bottom: 0;\r\n"
                + "        		  right: 0;\r\n"
                + "        		  background: url(https://media.istockphoto.com/photos/technical-financial-graph-on-technology-abstract-background-picture-id639666654?k=20&m=639666654&s=612x612&w=0&h=1xl981xcYy5J4LFQHaWRV50cDp8V1sbERtmfokDqMTw=);\r\n"
                + "        		  opacity: .4;\r\n"
                + "        		  content: \"\";\r\n"
                + "        		  z-index: -1;\r\n"
                + "        		  background-position: center center;\r\n"
                + "        		  background-size: cover;\r\n"
                + "        		  background-repeat: no-repeat;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.titulo {\r\n"
                + "        		  font-family: Roboto;\r\n"
                + "        		  font-weight: bold;\r\n"
                + "        		  font-size: 50px;\r\n"
                + "        		  align-items: center;\r\n"
                + "        		  position: relative;\r\n"
                + "        		  color: #FFFFFF;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.subtitulo {\r\n"
                + "        		  font-family: Roboto;\r\n"
                + "        		  font-size: 32px;\r\n"
                + "        		  text-align: left;\r\n"
                + "        		  color: #FFFFFF;\r\n"
                + "        		  position: relative;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.bienvenida {\r\n"
                + "        		  font-family: Roboto;\r\n"
                + "        		  font-size: 40px;\r\n"
                + "        		  font-weight: bold;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.listo {\r\n"
                + "        		  font-size: 18px;\r\n"
                + "        		  font-weight: bold;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.copyRigh {\r\n"
                + "        		  font-family: Roboto;\r\n"
                + "        		  font-size: 11px;\r\n"
                + "        		  position: relative;\r\n"
                + "        		  text-align: center;\r\n"
                + "        		  color: #FFFFFF;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "\r\n"
                + "        		table {\r\n"
                + "        		  width: 100%;\r\n"
                + "        		  border-spacing: 10px;\r\n"
                + "        		  color: #eeeeee;\r\n"
                + "        		  height: 100vh\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		tr.first {\r\n"
                + "        		  height: 20%\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		tr.second {\r\n"
                + "        		  height: 80%\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		td.first {\r\n"
                + "        		  align: left;\r\n"
                + "        		  width: 40%\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		td.second {\r\n"
                + "        		  width: 15%\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		td.third {\r\n"
                + "        		  align: left;\r\n"
                + "        		  width: 45%\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		table,\r\n"
                + "        		th,\r\n"
                + "        		td {}\r\n"
                + "\r\n"
                + "        		th,\r\n"
                + "        		td {\r\n"
                + "        		  padding: 10px;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.bt1 {\r\n"
                + "        		  padding: 10px 50px;\r\n"
                + "        		  margin: 6px 4px;\r\n"
                + "        		  cursor: pointer;\r\n"
                + "        		  text-transform: uppercase;\r\n"
                + "        		  text-align: center;\r\n"
                + "        		  position: relative;\r\n"
                + "        		  color: #eeeeee;\r\n"
                + "        		  background-color: #000000;\r\n"
                + "        		}\r\n"
                + "\r\n"
                + "        		.bt1:hover {\r\n"
                + "        		  opacity: 0.5;\r\n"
                + "        		}\r\n"
                + ""
                + "            </style>\r\n"
                + "          </head>\r\n"
                + "          <body>\r\n"
                + "            <div class=\"container\">\r\n"
                + "              <form action=\"ejemplo\" method=\"post\">\r\n"
                + "                <table>\r\n"
                + "                  <tr class=\"first\">\r\n"
                + "                    <td class=\"first\"><img width=\"150px\" height=\"150px\" alt=\"logo\" src=\"https://www.nafin.com/portalnf/images/nafin-logo.png\"></img></td>\r\n"
                + "                    <td class=\"second\"></td>\r\n"
                + "                    <td class=\"third\">\r\n"
                + "                      <p class=\"bienvenida\">HOLA DE NUEVO</p><br>\r\n"
                + "                      <p class=\"listo\">INGRESA LOS DATOS PARA INICIAR SESIÓN</p>\r\n"
                + "                    </td>\r\n"
                + "                  </tr>\r\n"
                + "                  <tr class=\"second\">\r\n"
                + "                    <td valign=\"top\" class=\"first\">\r\n"
                + "                      <p class=\"subtitulo\">SISTEMA DE CONCILIACIÓN AUTOMÁTICA DE DERIVADOS</p>\r\n"
                + "                    </td>\r\n"
                + "                    <td class=\"second\" </td>\r\n"
                + "                    <td valign=\"top\" class=\"third\">\r\n"
                + "                      <form action=\"ejemplo\" method=\"post\">\r\n"
                + "                        Usuario:<br><input type=\"text\" name=\"username\" /><br /><br />\r\n"
                + "                        Contraseña:<br><input type=\"password\" name=\"password\" /><br /><br />\r\n"
                + "                        <button class=\"bt1\" type=\"submit\">Acceder</button>\r\n"
                + "                      </form>\r\n"
                + "                    </td>\r\n"
                + "                  </tr>\r\n"
                + "                </table>\r\n"
                + "            </div>\r\n"
                + "          </body>\r\n"
                + "        </html>";
        writer.println(htmlRespone);
        //request.getRequestDispatcher("/WEB-INF/jsp/loginejemplo.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        System.out.println("************POSTTTTTTTTTTTTTT*");
        String userName = req.getParameter("username");
        String pass = req.getParameter("password");
        /*
        HttpSession session=  req.getSession();
        session.setAttribute("username", userName);
         */
        //EJEMPLO DE FLUJO MANUAL---------------------------------------------------------------
        //Cookie cookie = new Cookie("SSSSSSSSS", "SS");
        //Cookie name = new Cookie("username", req.getParameter("username"));
        //resp.addCookie(cookie);
        //EJEMPLO DE FLUJO MANUAL---------------------------------------------------------------

        //EJEMPLO DE FLUJO REAL---------------------------------------------------------------

        String ssoUserInfo = null;
        try {
            ssoUserInfo = sso.getSSOUserInfo(req, resp);
        } catch (SSOEnablerException e) {
            throw new RuntimeException(e);
        }
        //EJEMPLO DE FLUJO REAL---------------------------------------------------------------

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