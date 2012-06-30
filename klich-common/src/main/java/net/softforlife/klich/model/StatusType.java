package net.softforlife.klich.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Los estados de la aplicación se pueden categorizar en función de sus
 * propiedades asociadas a la lógica de negocio.
 * 
 * @author jmhidalgo
 */

@Entity
@Table(name = "status_type")
@NamedQueries( {
		@NamedQuery(name = "StatusType.findAll", query = "SELECT s FROM StatusType s"),
		@NamedQuery(name = "StatusType.findByStatusTypeId", query = "SELECT s FROM StatusType s WHERE s.statusTypeId = :statusTypeId"),
		@NamedQuery(name = "StatusType.findByShortDescription", query = "SELECT s FROM StatusType s WHERE s.shortDescription = :shortDescription"),
		@NamedQuery(name = "StatusType.findByDescription", query = "SELECT s FROM StatusType s WHERE s.description = :description") })
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class StatusType implements Serializable {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Basic(optional = false)
	@Column(name = "STATUS_TYPE_ID")
	private BigDecimal statusTypeId;
	
	/** El codigo interno. */
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;
	
	/** La description. */
	@Column(name = "DESCRIPTION")
	private String description;
	
	/** Clave ajena a la tabla STATUS_TYPE_ASSIGN. */
	@OneToMany(mappedBy = "statusTypeId")
	private Collection<StatusTypeAssign> statusTypeAssignCollection;

	/**
	 * Constructor de la clase StatusType.
	 */
	public StatusType() {
	}

	/**
	 * Constructor de la clase StatusType.
	 * 
	 * @param statusTypeId el campo status type id
	 */
	public StatusType(BigDecimal statusTypeId) {
		this.statusTypeId = statusTypeId;
	}

	/**
	 * Devuelve el valor del campo StatusTypeId.
	 * 
	 * @return Devuelve StatusTypeId
	 */
	public BigDecimal getStatusTypeId() {
		return statusTypeId;
	}

	/**
	 * Establece el valor del campo StatusTypeId.
	 * 
	 * @param statusTypeId es el nuevo valor de StatusTypeId
	 */
	public void setStatusTypeId(BigDecimal statusTypeId) {
		this.statusTypeId = statusTypeId;
	}

	/**
	 * Devuelve el valor del campo ShortDescription.
	 * 
	 * @return Devuelve ShortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * Establece el valor del campo ShortDescription.
	 * 
	 * @param shortDescription es el nuevo valor de ShortDescription
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * Devuelve el valor del campo Description.
	 * 
	 * @return Devuelve Description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Establece el valor del campo Description.
	 * 
	 * @param description es el nuevo valor de Description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Devuelve el valor del campo StatusTypeAssignCollection.
	 * 
	 * @return Devuelve StatusTypeAssignCollection
	 */
	public Collection<StatusTypeAssign> getStatusTypeAssignCollection() {
		return statusTypeAssignCollection;
	}

	/**
	 * Establece el valor del campo StatusTypeAssignCollection.
	 * 
	 * @param statusTypeAssignCollection es el nuevo valor de StatusTypeAssignCollection
	 */
	public void setStatusTypeAssignCollection(
			Collection<StatusTypeAssign> statusTypeAssignCollection) {
		this.statusTypeAssignCollection = statusTypeAssignCollection;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (statusTypeId != null ? statusTypeId.hashCode() : 0);
		return hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in el campo case the id fields are
		// not set
		if (!(object instanceof StatusType)) {
			return false;
		}
		StatusType other = (StatusType) object;
		if ((this.statusTypeId == null && other.statusTypeId != null)
				|| (this.statusTypeId != null && !this.statusTypeId
						.equals(other.statusTypeId))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "es.ine.sigue.model.StatusType[statusTypeId=" + statusTypeId
				+ "]";
	}

}
