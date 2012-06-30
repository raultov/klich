package net.softforlife.klich.model;

import java.io.Serializable;
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
 * Clase para la encapsulación de todos los estados posibles de la aplicación en
 * toda la funcionalidad.
 * 
 * @author rtovar
 */

@Entity
@Table(name = "status")
@NamedQueries( { @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s") })
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Status implements Serializable {
	
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Basic(optional = false)
	@Column(name = "STATUS_ID")
	private Integer statusId;
	
	/** Codigo corto. */
	@Column(name = "SHORT_DESCRIPTION")
	private String shortDescription;
	
	/** Description. */
	@Column(name = "DESCRIPTION")
	private String description;
	
	/** Si es de uso interno en la aplicacion. */
	@Basic(optional = false)
	@Column(name = "INTERNAL")
	private boolean internal;
	
	/** Si es un estado final. */
	@Basic(optional = false)
	@Column(name = "FINAL")
	private boolean finalState;

	/** Clave ajena a la tabla STATUS_TYPE. */
	@OneToMany(mappedBy = "statusId")
	private Collection<StatusTypeAssign> statusTypeAssignCollection;

	/**
	 * Constructor de la clase Status.
	 */
	public Status() {
	}

	/**
	 * Constructor de la clase Status.
	 * 
	 * @param statusId el campo status id
	 */
	public Status(Integer statusId) {
		this.statusId = statusId;
	}

	/**
	 * Constructor de la clase Status.
	 * 
	 * @param statusId el campo status id
	 * @param shortDescription el campo short description
	 * @param internal el campo internal
	 * @param finalState el campo final state
	 */
	public Status(Integer statusId, String shortDescription, boolean internal,
			boolean finalState) {
		this.statusId = statusId;
		this.shortDescription = shortDescription;
		this.internal = internal;
		this.finalState = finalState;
	}

	/**
	 * Devuelve el valor del campo StatusId.
	 * 
	 * @return Devuelve StatusId
	 */
	public Integer getStatusId() {
		return statusId;
	}

	/**
	 * Establece el valor del campo StatusId.
	 * 
	 * @param statusId es el nuevo valor de StatusId
	 */
	public void setStatusId(Integer statusId) {
		this.statusId = statusId;
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
	 * Devuelve el valor del campo Internal.
	 * 
	 * @return Devuelve Internal
	 */
	public boolean getInternal() {
		return internal;
	}

	/**
	 * Establece el valor del campo Internal.
	 * 
	 * @param internal es el nuevo valor de Internal
	 */
	public void setInternal(boolean internal) {
		this.internal = internal;
	}

	/**
	 * Devuelve el valor del campo FinalState.
	 * 
	 * @return Devuelve FinalState
	 */
	public boolean getFinalState() {
		return finalState;
	}

	/**
	 * Establece el valor del campo FinalState.
	 * 
	 * @param finalState es el nuevo valor de FinalState
	 */
	public void setFinalState(boolean finalState) {
		this.finalState = finalState;
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
		hash += (statusId != null ? statusId.hashCode() : 0);
		return hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in el campo case the id fields are
		// not set
		if (!(object instanceof Status)) {
			return false;
		}
		Status other = (Status) object;
		if ((this.statusId == null && other.statusId != null)
				|| (this.statusId != null && !this.statusId
						.equals(other.statusId))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "es.ine.sigue.model.Status[statusId=" + statusId + "]";
	}
	
	public static String parseToString(Status s) {
		String str = "" + s.statusId;
		
		return str;
	}

}