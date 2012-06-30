package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Notification;
import net.softforlife.klich.model.NotificationType;
import net.softforlife.klich.model.Tuser;

/**
 * Interfaz de acceso a datos que encapsula las operaciones sobre las notificaciones de la aplicación
 * 
 * @author rtovar
 */
public interface NotificationDAO extends GenericDAO<Notification, Long> {

	public Notification loadNewerNotificationByUser(Tuser user);
	
	public Notification loadNewerNotificationByUserAndType(Tuser user, NotificationType type);

	public List<Notification> loadAllNotificationsByUser(Tuser user);

	public List<Notification> loadAllNotificationsByUserAndType(Tuser user, NotificationType type);

	public List<Notification> loadAllActiveNotificationsByUserAndType(Tuser user, NotificationType type);

	public Notification loadNewerActiveNotificationByUserAndType(Tuser user, NotificationType type);
}
