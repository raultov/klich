package net.softforlife.klich.JSF.web.util;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import javax.faces.application.Application;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;


// TODO: Auto-generated Javadoc
/**
 * Clase de utilidades accedidas de forma estática
 * 
 * @author rtovar
 * 
 */

public class JsfUtil {

	/**
	 * Acceso al entorno de ejecución externo
	 * 
	 * @return
	 */

	public static ExternalContext getExternalContext() {
		return FacesContext.getCurrentInstance().getExternalContext();
	}

	/**
	 * Obtiene un parámetro de la request actual parseado a String
	 * 
	 * @param name
	 * @return
	 */

	public static String getStringParam(String name) {
		Object o = getParam(name);
		String msg = null;
		if (o != null) {
			msg = o.toString();
		}
		return msg;
	}

	/**
	 * 
	 * Devuelve un parametro como Objeto
	 * 
	 * @param name
	 * @return
	 */

	@SuppressWarnings("rawtypes")
	public static Object getParam(String name) {
		ExternalContext context = getExternalContext();
		Map requestParams = context.getRequestParameterMap();
		return requestParams.get(name);
	}

	/**
	 * Devuelve el primer parametro que su nombre coincida con la expresión
	 * regular pasada. Esto nos permite pasar parámetros a partir del nombre de
	 * los botones que ejecutan la aplicación, util si se procura no usar
	 * enlaces jsf ya que no funcionan sin javascript.
	 * 
	 * @param regex
	 * @return
	 */


	@SuppressWarnings("rawtypes")
	public static String getParamNameByRegex(String regex) {
		ExternalContext context = getExternalContext();
		Map requestParams = context.getRequestParameterMap();

		for (Object key : requestParams.keySet()) {
			if (key.toString().contains(regex)) {
				return key.toString();
			}
		}
		return null;
	}

	/**
	 * calcular el tamaño de un objeto en bytes
	 * 
	 * @param obj
	 * @return
	 * @throws java.io.IOException
	 */
	public static int getSize(Object obj) throws java.io.IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.flush();
		oos.close();
		bos.close();
		byte[] data = bos.toByteArray();
		return data.length;
	}

	/**
	 * Redirecciona a la pagina especificada.
	 * 
	 * @param jsfOutcome
	 *            Un outcome del faces-config.xml.
	 */
	public static void redirect(String jsfOutcome) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Application application = facesContext.getApplication();
		NavigationHandler navigationHandler = application
				.getNavigationHandler();
		navigationHandler.handleNavigation(facesContext, null, jsfOutcome);
		facesContext.renderResponse();
	}
}