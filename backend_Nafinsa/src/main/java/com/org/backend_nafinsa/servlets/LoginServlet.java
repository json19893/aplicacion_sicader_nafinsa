package com.org.backend_nafinsa.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean;
import com.google.gson.Gson;
import com.org.backend_nafinsa.dto.UsuarioToken;
import com.org.backend_nafinsa.util.Utilidades;

import lombok.extern.slf4j.Slf4j;
import oracle.security.sso.enabler.SSOEnablerException;

@Slf4j
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@WebServlet(urlPatterns = "/sicader-api/init/*")
public class LoginServlet extends HttpServlet {

	Utilidades utl= new Utilidades();
	Gson gson = new Gson();
	/**
	 * 
	 */
	com.chermansolutions.oracle.sso.partnerapp.beans.SSOEnablerJspBean sso = new SSOEnablerJspBean();
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("deprecation")
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter writer = resp.getWriter();
		
		resp.setCharacterEncoding("UTF-8");

		//try {
		    resp.setContentType("text/html");
			log.info((String) req.getParameter("usuario"));
			log.info((String) req.getParameter("password"));
			//String ssoUserInfo = sso.getSSOUserInfo(req, resp);
			//log.info("ssoUserInfo:: " + ssoUserInfo);
			//if (!ssoUserInfo.equals(null)) {
			if (req.getParameter("usuario").equals("jsalgado")&&req.getParameter("password").equals("12345") ) {
			//sso.setPartnerAppCookie(req, resp);
				 String token = utl.getJWTToken(req.getParameter("usuario"));
			        UsuarioToken user = new UsuarioToken();
			        user.setUsuario(req.getParameter("usuario"));
			        user.setToken(token);
				

				writer.print(this.gson.toJson(user));

			    resp.sendError(HttpServletResponse.SC_OK, this.gson.toJson(user));
	
			}else {

				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales no validas");
				
			}
		
		/*} catch (SSOEnablerException e) {
			log.error("Error al consumir el servlet: " + e);
			e.printStackTrace();
			writer.println("error "+e);
			resp.setStatus(500);
			
		}*/

		
	}
	


}
