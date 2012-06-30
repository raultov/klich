package net.softforlife.klich.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Entidad para el manejo de notificaciones emitidas por la aplicación
 * 
 * @author rtovar
 */

@Entity
@Table(name = "Notification")
@NamedQueries( {
		@NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
		@NamedQuery(name = "Notification.findByNotificationId", query = "SELECT n FROM Notification n WHERE n.notificationId = :notificationId"),
		@NamedQuery(name = "Notification.findByUser", query = "SELECT n FROM Notification n WHERE n.receiver = :user"),
		@NamedQuery(name = "Notification.findNewerNotificationByUser", query = "SELECT n FROM Notification n WHERE n.receiver = :user order by n.sendDate"),
		@NamedQuery(name = "Notification.findNewerNotificationByUserAndType", query = "SELECT n FROM Notification n WHERE n.receiver = :user and n.notificationType = :type order by n.sendDate"),
		@NamedQuery(name = "Notification.findNewerActiveNotificationsByUserAndType", query = "SELECT n FROM Notification n WHERE n.receiver = :user and n.notificationType = :type and n.active=true order by n.sendDate")
		})
public class Notification implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Basic(optional = false)
	@Column(name = "NOTIFICATION_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long notificationId;
	
	/** El valor literal. */
	@Column(name = "CODE")
    private String code;;
	
	/** El valor literal. */
	@Column(name = "SEND_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date sendDate;
	
	/** El valor literal. */
	@Column(name = "CONFIRMATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date confirmationDate;

	/** El valor literal */
	@Column(name = "ACTIVE")
	private Boolean active;
	
	@Column(name = "ATTEMPTS")
	private Integer attempts;
	
	/** Clave ajena a la tabla NOTIFICATION. */
	@JoinColumns({
		@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
		@JoinColumn(name = "LOGIN", referencedColumnName = "LOGIN")
	})
	@ManyToOne(optional = false)
	private Tuser receiver;
	
	@JoinColumn(name = "NOTIFICATION_TYPE", referencedColumnName = "NOTIFICATION_TYPE_ID")
	@OneToOne
	private NotificationType notificationType;

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Date getSendDate() {
		return sendDate;
	}

	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}

	public Date getConfirmationDate() {
		return confirmationDate;
	}

	public void setConfirmationDate(Date confirmationDate) {
		this.confirmationDate = confirmationDate;
	}

	public Tuser getReceiver() {
		return receiver;
	}

	public void setReceiver(Tuser receiver) {
		this.receiver = receiver;
	}

	public NotificationType getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(NotificationType notificationType) {
		this.notificationType = notificationType;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}
}
