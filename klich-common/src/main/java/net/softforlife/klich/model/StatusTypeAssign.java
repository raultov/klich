package net.softforlife.klich.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Esta asociación entre tipo de estado y estado, define la funcionalidad
 * asociada al estado.
 * 
 * @author jmhidalgo
 */

@Entity
@Table(name = "status_type_assign")
@NamedQueries( {
		@NamedQuery(name = "StatusTypeAssign.findAll", query = "SELECT s FROM StatusTypeAssign s"),
		@NamedQuery(name = "StatusTypeAssign.findByStatusTypeAssignId", query = "SELECT s FROM StatusTypeAssign s WHERE s.statusTypeAssignId = :statusTypeAssignId") })
public class StatusTypeAssign implements Serializable {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Basic(optional = false)
	@Column(name = "STATUS_TYPE_ASSIGN_ID")
	private BigDecimal statusTypeAssignId;
	
	/** Clave ajena a la tabla STATUS. */
	@JoinColumn(name = "STATUS_ID", referencedColumnName = "STATUS_ID")
	@ManyToOne
	private Status statusId;
	
	/** Clave ajena a la tabla STATUS_TYPE. */
	@JoinColumn(name = "STATUS_TYPE_ID", referencedColumnName = "STATUS_TYPE_ID")
	@ManyToOne
	private StatusType statusTypeId;

	/**
	 * Constructor de la clase StatusTypeAssign.
	 */
	public StatusTypeAssign() {
	}

	/**
	 * Constructor de la clase StatusTypeAssign.
	 * 
	 * @param statusTypeAssignId el campo status type assign id
	 */
	public StatusTypeAssign(BigDecimal statusTypeAssignId) {
		this.statusTypeAssignId = statusTypeAssignId;
	}

	/**
	 * Devuelve el valor del campo StatusTypeAssignId.
	 * 
	 * @return Devuelve StatusTypeAssignId
	 */
	public BigDecimal getStatusTypeAssignId() {
		return statusTypeAssignId;
	}

	/**
	 * Establece el valor del campo StatusTypeAssignId.
	 * 
	 * @param statusTypeAssignId es el nuevo valor de StatusTypeAssignId
	 */
	public void setStatusTypeAssignId(BigDecimal statusTypeAssignId) {
		this.statusTypeAssignId = statusTypeAssignId;
	}

	/**
	 * Devuelve el valor del campo StatusId.
	 * 
	 * @return Devuelve StatusId
	 */
	public Status getStatusId() {
		return statusId;
	}

	/**
	 * Establece el valor del campo StatusId.
	 * 
	 * @param statusId es el nuevo valor de StatusId
	 */
	public void setStatusId(Status statusId) {
		this.statusId = statusId;
	}

	/**
	 * Devuelve el valor del campo StatusTypeId.
	 * 
	 * @return Devuelve StatusTypeId
	 */
	public StatusType getStatusTypeId() {
		return statusTypeId;
	}

	/**
	 * Establece el valor del campo StatusTypeId.
	 * 
	 * @param statusTypeId es el nuevo valor de StatusTypeId
	 */
	public void setStatusTypeId(StatusType statusTypeId) {
		this.statusTypeId = statusTypeId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (statusTypeAssignId != null ? statusTypeAssignId.hashCode() : 0);
		return hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in el campo case the id fields are
		// not set
		if (!(object instanceof StatusTypeAssign)) {
			return false;
		}
		StatusTypeAssign other = (StatusTypeAssign) object;
		if ((this.statusTypeAssignId == null && other.statusTypeAssignId != null)
				|| (this.statusTypeAssignId != null && !this.statusTypeAssignId
						.equals(other.statusTypeAssignId))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "es.ine.sigue.model.StatusTypeAssign[statusTypeAssignId="
				+ statusTypeAssignId + "]";
	}

}