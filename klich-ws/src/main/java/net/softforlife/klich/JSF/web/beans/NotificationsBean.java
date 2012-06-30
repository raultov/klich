package net.softforlife.klich.JSF.web.beans;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import net.softforlife.klich.JSF.web.util.JsfUtil;
import net.softforlife.klich.enumeration.NOTIFICATION_TYPE;
import net.softforlife.klich.model.Notification;
import net.softforlife.klich.model.NotificationType;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.service.NotificationService;
import net.softforlife.klich.service.UserService;

public class NotificationsBean {
	
	private static final int OP_OK = 1;
	private static final int OP_ERROR = 2;
	
	private NotificationService notificationService;
	private UserService userService;
	
	private Integer attemptsMax;
	
	private String errorMessage;
	
	private String propertiesFile;
	private ResourceBundle myResources;
	
	private int processState = OP_OK;

	public void getProcess() {
		
		Locale lCastellano = new Locale("ES","es");
		ResourceBundle myResources = ResourceBundle.getBundle(propertiesFile, lCastellano);

		final String user = (String) JsfUtil.getParam("user");
		final String code = (String) JsfUtil.getParam("code");
		final String type = (String) JsfUtil.getParam("type");
		
		if (!notificationService.validateCode(code)) {
			// El código no es correcto
			processState = OP_ERROR;
			errorMessage = myResources.getString("notifications.error.incorrect.code");
		}
		
		if (!notificationService.validateUser(user)) {
			// El usuario no es correcto
			processState = OP_ERROR;
			errorMessage = myResources.getString("notifications.error.incorrect.user");
		}
		
		if (!notificationService.validateType(type)) {
			// El tipo de notificación no es correcta
			processState = OP_ERROR;
			errorMessage = myResources.getString("notifications.error.incorrect.user");
		}
		
		Tuser receiver = userService.getUserById(Integer.parseInt(user));
		
		NotificationType notificationType = NOTIFICATION_TYPE.getNotificationTypeById(Integer.parseInt(type));
		
		
		
		if (receiver != null) {
			List<Notification> notifications = notificationService.loadAllActiveNotificationsByUserAndType(receiver, notificationType);
			
			if ((notifications == null) || (notifications.size() == 0)) {
				// Este usuario no tiene notificaciones pendientes
				processState = OP_ERROR;
				errorMessage = myResources.getString("notifications.error.no.pending.notifications");
			} else {
			
				Notification notification = null;
				
				for (Notification n : notifications) {
					if (n.getCode().equals(code)) {
						notification = n;
						break;
					}
				}
			
				if (notification != null) {
					int attempts = notification.getAttempts();
					
					if (attempts < attemptsMax) {
						notification.setAttempts(notification.getAttempts() + 1);
						notification.setActive(false);
						notification.setConfirmationDate(new Date());

						notificationService.persist(notification);
						
						// TODO - Notificación de reseteo de contraseña
						// Hay que mostrar la pantalla para resetear contraseña
						processState = OP_OK;
						
					} else {
						// El usuario ha superado el número máximo de intentos para validar una notificacion
						// Puede tratarse de un ataque
						
						processState = OP_ERROR;
						errorMessage = myResources.getString("notifications.error.max.attempts");
						
						notification.setActive(false);
						notificationService.persist(notification);
					}
				} else {
					// El codigo no es correcto
					processState = OP_ERROR;
					errorMessage = myResources.getString("notifications.error.code.no.exists");
				}
			}
		} else {
			// El usuario no existe
			processState = OP_ERROR;
			errorMessage = myResources.getString("notifications.error.user.no.exists");
		}
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setAttemptsMax(Integer attemptsMax) {
		this.attemptsMax = attemptsMax;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setPropertiesFile(String propertiesFile) {
		this.propertiesFile = propertiesFile;
	}
}
