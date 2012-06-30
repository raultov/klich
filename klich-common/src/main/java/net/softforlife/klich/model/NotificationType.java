package net.softforlife.klich.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Clase para la encapsulación de los tipos de notificaciones
 * 
 * @author rtovar
 */

@Entity
@Table(name = "Notification_Type")
@NamedQueries( { @NamedQuery(name = "NotificationType.findAll", query = "SELECT n FROM NotificationType n") })
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class NotificationType implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Basic(optional = false)
	@Column(name = "NOTIFICATION_TYPE_ID")
	private Integer notificationTypeId;
	
	/** Description. */
	@Column(name = "DESCRIPTION")
	private String description;
	
	/** Si es de uso interno en la aplicacion. */
	@Basic(optional = false)
	@Column(name = "INTERNAL")
	private boolean internal;
	
	public NotificationType() {
		
	}
	
	public NotificationType(Integer id, String desc, Boolean intern) {
		this.notificationTypeId = id;
		this.description = desc;
		this.internal = intern;
	}

	public Integer getNotificationTypeId() {
		return notificationTypeId;
	}

	public void setNotificationTypeId(Integer notificationTypeId) {
		this.notificationTypeId = notificationTypeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isInternal() {
		return internal;
	}

	public void setInternal(boolean internal) {
		this.internal = internal;
	}
}
