package net.softforlife.klich.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-03-08T21:22:17.365+0100")
@StaticMetamodel(Notification.class)
public class Notification_ {
	public static volatile SingularAttribute<Notification, Long> notificationId;
	public static volatile SingularAttribute<Notification, String> code;
	public static volatile SingularAttribute<Notification, Date> sendDate;
	public static volatile SingularAttribute<Notification, Date> confirmationDate;
	public static volatile SingularAttribute<Notification, Boolean> active;
	public static volatile SingularAttribute<Notification, Integer> attempts;
	public static volatile SingularAttribute<Notification, Tuser> receiver;
	public static volatile SingularAttribute<Notification, NotificationType> notificationType;
}
