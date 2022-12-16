package com.org.backend_nafinsa.servlets;

import com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean;
import com.google.gson.Gson;
import com.org.backend_nafinsa.util.Utilidades;
import lombok.extern.slf4j.Slf4j;
import oracle.security.sso.enabler.SSOEnablerException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Slf4j
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@WebServlet(urlPatterns = "/sicader-api/logout/*")
public class LogoutServlet extends HttpServlet {

    Utilidades utl= new Utilidades();
    Gson gson = new Gson();

    com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean sso = new SSOEnablerJspBean();
    private static final long serialVersionUID = 1L;
    @SuppressWarnings("deprecation")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "*");
        PrintWriter writer = resp.getWriter();
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        try {
            /***eliminar cookies****************************************************************************/
            /*******************************************************************************/
            sso.removeJspAppCookies(req,resp);
            /*******************************************************************************/
            /*******************************************************************************/
            resp.sendError(HttpServletResponse.SC_OK,  "se eliminaron cookies");
        }catch (Exception e){
            log.error(e.toString());
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error al eliminar cookies");
        }
        //catch (SSOEnablerException e) {
        //    log.error(e.toString());
        //    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "{\"mensaje\":\"Error al eliminar cookies\"}");
        //}
    }

}
