package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.softforlife.klich.model.Notification;
import net.softforlife.klich.model.NotificationType;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.persistence.DAO.NotificationDAO;

@Repository
public class NotificationDAOJPA extends GenericDAOWithJPA<Notification, Long> implements NotificationDAO {
	
	@Override
	public List<Notification> loadAllNotificationsByUser(Tuser user) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("user", user);
		@SuppressWarnings("unchecked")
		List<Notification> notifications = getJpaTemplate().findByNamedQueryAndNamedParams(
				"Notification.findNewerNotificationByUser", params);


		return notifications;
	}
	
	@Override
	public List<Notification> loadAllNotificationsByUserAndType(Tuser user, NotificationType type) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("user", user);
		params.put("type", type);
		@SuppressWarnings("unchecked")
		List<Notification> notifications = getJpaTemplate().findByNamedQueryAndNamedParams(
				"Notification.findNewerNotificationByUserAndType", params);

		return notifications;
	}
	
	@Override
	public List<Notification> loadAllActiveNotificationsByUserAndType(Tuser user, NotificationType type) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("user", user);
		params.put("type", type);
		@SuppressWarnings("unchecked")
		List<Notification> notifications = getJpaTemplate().findByNamedQueryAndNamedParams(
				"Notification.findNewerActiveNotificationsByUserAndType", params);

		return notifications;
	}		

	@Override
	public Notification loadNewerNotificationByUser(Tuser user) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("user", user);
		@SuppressWarnings("unchecked")
		List<Notification> notifications = getJpaTemplate().findByNamedQueryAndNamedParams(
				"Notification.findNewerNotificationByUser", params);

		if (notifications.size() == 0) {
			return null;
		}

		return notifications.get(0);
	}

	@Override
	public Notification loadNewerNotificationByUserAndType(Tuser user, NotificationType type) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("user", user);
		params.put("type", type);
		@SuppressWarnings("unchecked")
		List<Notification> notifications = getJpaTemplate().findByNamedQueryAndNamedParams(
				"Notification.findNewerNotificationByUserAndType", params);

		if (notifications.size() == 0) {
			return null;
		}

		return notifications.get(0);
	}

	@Override
	public Notification loadNewerActiveNotificationByUserAndType(Tuser user, NotificationType type) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("user", user);
		params.put("type", type);
		@SuppressWarnings("unchecked")
		List<Notification> notifications = getJpaTemplate().findByNamedQueryAndNamedParams(
				"Notification.findNewerActiveNotificationsByUserAndType", params);

		if (notifications.size() == 0) {
			return null;
		}

		return notifications.get(0);
	}

}
