package net.softforlife.klich.JSF.web.beans;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.softforlife.klich.service.UserService;


// TODO: Auto-generated Javadoc
/**
 * Bean para manejar las peticiones de logout. Su reponsabilidad es comprobar
 * que el estado de la sesión permite deslogarse
 * 
 * @author rtovar
 * 
 */

public class LogoutBean {

	/**
	 * Realiza el logout sino se encuentra ninguna llamada asignada.
	 * 
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	
	/** El user service. */
	private UserService userService;


	public String logout() throws ServletException, IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();

		String path = ((HttpServletRequest) context.getRequest()).getContextPath();
			
		((HttpServletResponse) context.getResponse()).sendRedirect(path	+ "/j_spring_security_logout");
			
		// It's OK to return null here because Faces is just going to exit.
		return null;
	}
	
	/**
	 * Devuelve el valor del campo UserName.
	 * 
	 * @return Devuelve UserName
	 */
	public String getUserName() {
		return userService.getUserInSession().getUserId().getLogin();
	}


	public UserService getUserService() {
		return userService;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}