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
@WebServlet(urlPatterns = "/init")
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
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.addHeader("Access-Control-Allow-Origin", "*");
		PrintWriter writer = resp.getWriter();
		resp.setCharacterEncoding("UTF-8");
		try {
		    resp.setContentType("text/html");
			log.info((String) req.getParameter("usuario"));
			log.info((String) req.getParameter("password"));
			/* jcarias*/
			log.info("jcarias userName:"+sso.getSSOUserClientInfo(req,resp));
			/* termina jcarias*/
			String ssoUserInfo = null;
				
			log.info("ssoUserInfo:: " + ssoUserInfo);
			/***HABILITAR EL ACCESO POR USUARIO Y PASS******************************************************************************/
			/***********************************************************************************************************************/
			if(req.getParameter("usuario")!=null) {
				ssoUserInfo = req.getParameter("usuario");
			}else {
				ssoUserInfo = sso.getSSOUserInfo(req, resp);
			}
			log.info("ssoUserInfoxx:: " + ssoUserInfo);
			
			if (!ssoUserInfo.equals(null)) {
			/***********************************************************************************************************************/
			//if (req.getParameter("usuario").equals("jsalgado")&&req.getParameter("password").equals("12345") ) {
			   // resp.sendError(HttpServletResponse.SC_OK, "Usuario firmado en Nafin");
				log.info("Entro ssoUserInfo:: " + ssoUserInfo);
				String token = utl.getJWTToken(ssoUserInfo);
				log.info("Entro token:: " + token);
	            UsuarioToken user = new UsuarioToken();
	            user.setUsuario(ssoUserInfo);
	            user.setToken(token);
	            log.info("gson:: " + this.gson.toJson(user));
	            writer.print(this.gson.toJson(user));
	            //resp.sendError(HttpServletResponse.SC_OK, this.gson.toJson(user));
			//}else {
			    //resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales no validas");
			//}
			}else {
				resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales no validas");
			}
			
		} catch (SSOEnablerException e) {
			log.error(e.toString());
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales no validas");
		}catch (Exception e){
			log.error(e.toString());
			resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Credenciales no validas");
		}
	}
}
