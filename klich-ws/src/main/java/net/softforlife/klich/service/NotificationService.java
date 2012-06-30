package net.softforlife.klich.service;


import java.util.List;

import net.softforlife.klich.model.Notification;
import net.softforlife.klich.model.NotificationType;
import net.softforlife.klich.model.Tuser;

import org.springframework.transaction.annotation.Transactional;

/**
 * Interface de servicio de notificaciones.<br>
 * Define las operaciones que se realizan sobre las notificaciones del sistema
 * @author rtovar
 */
public interface NotificationService {
	public static final String characters = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ_"; 
	
    /**
     * Almacena en base de datos un objeto notification (Notification).
     * @param notification El objeto notification a guardar en el sistema.
     */
    @Transactional
    public void persist(Notification notification);
    
    public boolean validateCode(final String code);
    
    public boolean validateUser(final String user);
    
    public boolean validateType(final String type);
    
    public String generateCode();
    
    @Transactional
	public Notification loadNewerNotificationByUser(Tuser user);
	
    @Transactional
	public Notification loadNewerNotificationByUserAndType(Tuser user, NotificationType type);

    @Transactional
	public List<Notification> loadAllNotificationsByUser(Tuser user);

    @Transactional
	public List<Notification> loadAllNotificationsByUserAndType(Tuser user, NotificationType type);

    @Transactional
	public List<Notification> loadAllActiveNotificationsByUserAndType(Tuser user, NotificationType type);

    @Transactional
	public Notification loadNewerActiveNotificationByUserAndType(Tuser user, NotificationType type);
}
