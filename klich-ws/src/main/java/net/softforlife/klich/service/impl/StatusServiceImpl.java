package net.softforlife.klich.service.impl;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.softforlife.klich.service.StatusService;
import net.softforlife.klich.service.security.SecurityUser;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.context.SecurityContext;

@SuppressWarnings("rawtypes")
public class StatusServiceImpl implements StatusService, HttpSessionListener, ApplicationListener {
	/** El logger. */
	private Logger logger = Logger.getLogger(StatusServiceImpl.class);
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org
	 * .springframework.context.ApplicationEvent)
	 */
	public void onApplicationEvent(final ApplicationEvent e) {
		/*if (e instanceof ContextClosedEvent) {
			logger.debug("\n\t... LIBERANDO LLAMADAS ...");

			List<Calls> activeCallList = callsService.getActiveCallList();

			Iterator<Calls> it = activeCallList.iterator();

			while (it.hasNext()) {
				Calls call = it.next();
				cancelInterview(call);
				it.remove();
			}
		}
		*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
	 * .HttpSessionEvent)
	 */
	public void sessionCreated(HttpSessionEvent arg0) {
		// nothing
	}

	/**
	 * Este método escucha las sesiones finalizadas
	 */
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {

		/*
		 * Object o =
		 * sessionEvent.getSession().getAttribute(AuthenticationProcessingFilter
		 * .SPRING_SECURITY_LAST_USERNAME_KEY); logger.debug(" userService " +
		 * userService);
		 * 
		 * ApplicationContext applicationContext = WebApplicationContextUtils
		 * .getRequiredWebApplicationContext(sessionEvent.getSession()
		 * .getServletContext());
		 * 
		 * userService = (UserService)
		 * applicationContext.getBean("userService"); logger.debug(" " +
		 * userService.getUserInSession());
		 */

		SecurityContext scImpl = null;
		scImpl = (SecurityContext) sessionEvent.getSession().getAttribute(
				"SPRING_SECURITY_CONTEXT");
		Object obj = null;
		if ((scImpl != null)
				&& (scImpl.getAuthentication().getPrincipal() != null)) {
			obj = scImpl.getAuthentication().getPrincipal();

		}
		logger.debug("\n SPRING_SECURITY_CONTEXT " + scImpl
				+ "\n SECURITY USER: " + obj);

		if ((obj == null) || !(obj instanceof SecurityUser)) {
			logger.error("No existe llamada");
			return;
		}

		//Calls call = (Calls) ((SecurityUser) obj)
			//	.getProperty(CallsServiceImpl.SESSION_PROPERTY_CALLID);

		//cancelInterview(call);
	}

}
