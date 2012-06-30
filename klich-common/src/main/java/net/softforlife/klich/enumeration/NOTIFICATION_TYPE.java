package net.softforlife.klich.enumeration;

import net.softforlife.klich.model.NotificationType;

public enum NOTIFICATION_TYPE implements GenericEnum<NotificationType> {
	RESET_PASS(1, "Notificación por correo para recordar clave", false),
	REGISTER_CONFIRM(2, "Notificación por correo para confirmar el registro", false);
	
	/** El id. */
	private int notificationTypeId;

	/** El code. */
	private String description;

	/** El internal. */
	private boolean internal;
	
	private NOTIFICATION_TYPE(int notificationTypeId, String description, boolean internal) {
		this.notificationTypeId = notificationTypeId;
		this.description = description;
		this.internal = internal;
	}

	@Override
	public String getCode() {
		return description;
	}

	@Override
	public int getId() {
		return notificationTypeId;
	}

	@Override
	public NotificationType getObject() {
		return new NotificationType(notificationTypeId, description, internal);
	}

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean internal) {
		this.internal = internal;
	}
	
	public static boolean isNotificationType(int id) {
		if (RESET_PASS.getId() == id) {
			return true;
		} else if (REGISTER_CONFIRM.getId() == id) {
			return true;
		}
		
		return false;
	}
	
	public static NotificationType getNotificationTypeById(int id) {
		if (RESET_PASS.getId() == id) {
			return RESET_PASS.getObject();
		} else if (REGISTER_CONFIRM.getId() == id) {
			return REGISTER_CONFIRM.getObject();
		}
		
		return null;
	}

}
