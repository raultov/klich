package net.softforlife.klich.model;

import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "TUSER_TROLE")
@NamedQueries( { @NamedQuery(name = "TuserTrole.findAll", query = "SELECT t FROM TuserTrole t") })
public class TuserTrole implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Column(name = "tuser_trole_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USERROLE_SEQ")
	@SequenceGenerator(name = "USERROLE_SEQ", sequenceName = "SESQ39_tuser_trole_id")
	private Integer tuserTroleId;
	
	/** Clave ajena a la tabla TROLE. */
	@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
	@ManyToOne(optional = false)
	private Trole roleId;
	
	/** Clave ajena a la tabla TUSER. 
	@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
	@ManyToOne(optional = false)
	private Tuser userId;*/
	
	@JoinColumns({
		@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
		@JoinColumn(name = "LOGIN", referencedColumnName = "LOGIN")
	})
	@ManyToOne(optional = false)
	private Tuser userId;
	
	
	
	/**
	 * Constructor de la clase TuserTrole.
	 */
	public TuserTrole() {
	}

	/**
	 * Constructor de la clase TuserTrole.
	 * 
	 * @param tuserTroleId the tuser trole id
	 */
	public TuserTrole(Integer tuserTroleId) {
		this.tuserTroleId = tuserTroleId;
	}
	
	/**
	 * @param tuserTroleId the tuserTroleId to set
	 */
	public void setTuserTroleId(Integer tuserTroleId) {
		this.tuserTroleId = tuserTroleId;
	}

	/**
	 * @return the tuserTroleId
	 */
	public Integer getTuserTroleId() {
		return tuserTroleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Trole roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the roleId
	 */
	public Trole getRoleId() {
		return roleId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Tuser userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	public Tuser getUserId() {
		return userId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (tuserTroleId != null ? tuserTroleId.hashCode() : 0);
		return hash;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof TuserTrole)) {
			return false;
		}
		TuserTrole other = (TuserTrole) object;
		if ((this.tuserTroleId == null && other.tuserTroleId != null)
				|| (this.tuserTroleId != null && !this.tuserTroleId
						.equals(other.tuserTroleId))) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "net.softforlife.klich.model.TuserTrole[tuserTroleId=" + tuserTroleId
				+ "]";
	}
}
