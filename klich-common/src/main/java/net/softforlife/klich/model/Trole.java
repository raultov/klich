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
 * Encapsula cada Rol definido en la aplicación.
 * 
 * @author rtovar
 */

@Entity
@Table(name = "TROLE")
@NamedQueries( { @NamedQuery(name = "Trole.findAll", query = "SELECT t FROM Trole t") })
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
public class Trole implements Serializable  {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Basic(optional = false)
	@Column(name = "ROLE_ID")
	private Integer roleId;
	
	/** El nombre. */
	@Column(name = "LKEY")
	private String lkey;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "roleId") private
	 * Collection<TuserTrole> tuserTroleCollection;
	 */

	/**
	 * Constructor de la clase Trole.
	 */
	public Trole() {
	}

	/**
	 * Constructor de la clase Trole.
	 * 
	 * @param roleId the role id
	 * @param lkey the lkey
	 */
	public Trole(Integer roleId, String lkey) {
		this.roleId = roleId;
		this.setLkey(lkey);
	}	
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (roleId != null ? roleId.hashCode() : 0);
		return hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Trole)) {
			return false;
		}
		Trole other = (Trole) object;
		if ((this.roleId == null && other.roleId != null)
				|| (this.roleId != null && !this.roleId.equals(other.roleId))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "net.softforlife.klich.model.Trole[roleId=" + roleId + "]";
	}
	
	

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleId
	 */
	public Integer getRoleId() {
		return roleId;
	}

	/**
	 * @param lkey the lkey to set
	 */
	public void setLkey(String lkey) {
		this.lkey = lkey;
	}

	/**
	 * @return the lkey
	 */
	public String getLkey() {
		return lkey;
	}
}
