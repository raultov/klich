package net.softforlife.klich.service.impl;

import java.util.List;

import net.softforlife.klich.enumeration.NOTIFICATION_TYPE;
import net.softforlife.klich.model.Notification;
import net.softforlife.klich.model.NotificationType;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.persistence.DAO.NotificationDAO;
import net.softforlife.klich.service.NotificationService;
import java.util.Random;
public class NotificationServiceImpl implements NotificationService {
	private static final int lengthCode = 50;
	
	private Random rand;

    /** El notification dao. */
    private NotificationDAO notificationDAO;
    
	@Override
	public void persist(Notification notification) {
		notificationDAO.persist(notification);
	}
	
	public void init() {
		rand = new Random();
	}
	
	@Override
	public String generateCode() {
		// Genera un código alfanúmerico aleatorio de 50 caracteres 
		
		String ret = "";
		int n = characters.length();
		
		for (int i = 0; i < lengthCode; i++) {
			ret += characters.charAt(rand.nextInt(n));
		}
		
		return ret;
	}

	public NotificationDAO getNotificationDAO() {
		return notificationDAO;
	}

	public void setNotificationDAO(NotificationDAO notificationDAO) {
		this.notificationDAO = notificationDAO;
	}

	@Override
	public boolean validateCode(final String code) {
		
		try {
			if (!code.isEmpty()) {
				
				if (code.length() != lengthCode) {
					return false;
				}
				
				for (int i=0; i < code.length(); i++) {
					char c = code.charAt(i);
					
					if (NotificationService.characters.indexOf(c) == -1) {
						return false;
					}
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean validateUser(final String user) {
		
		try {
			Integer.parseInt(user);
		} catch (Exception ex) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean validateType(String type) {
		try {
			int notificationType = Integer.parseInt(type);
			
			if (NOTIFICATION_TYPE.isNotificationType(notificationType)) {
				return true;
			}
			
		} catch (Exception ex) {
			return false;
		}
		
		return true;
	}

	@Override
	public Notification loadNewerNotificationByUser(Tuser user) {
		return notificationDAO.loadNewerNotificationByUser(user);
	}

	@Override
	public Notification loadNewerNotificationByUserAndType(Tuser user, NotificationType type) {
		return notificationDAO.loadNewerNotificationByUserAndType(user, type);
	}

	@Override
	public List<Notification> loadAllNotificationsByUser(Tuser user) {
		return notificationDAO.loadAllNotificationsByUser(user);
	}

	@Override
	public List<Notification> loadAllNotificationsByUserAndType(Tuser user, NotificationType type) {
		return notificationDAO.loadAllNotificationsByUserAndType(user, type);
	}

	@Override
	public List<Notification> loadAllActiveNotificationsByUserAndType(Tuser user, NotificationType type) {
		return notificationDAO.loadAllActiveNotificationsByUserAndType(user, type);
	}

	@Override
	public Notification loadNewerActiveNotificationByUserAndType(Tuser user, NotificationType type) {
		return notificationDAO.loadNewerActiveNotificationByUserAndType(user, type);
	}

}

















