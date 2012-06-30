package net.softforlife.klich.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * Tuser forma cada usuario de la aplicación.
 * 
 * @author rtovar
 */

@Entity
@Table(name = "TUSER")
@NamedQueries( { @NamedQuery(name = "Tuser.findByLogin", query = "SELECT t FROM Tuser t WHERE t.userId.login = :login") })
public class Tuser implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). 
	@Id
	@Column(name = "user_id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "SESQ38_user_id")
	private Integer userId;
	

	@Id
	@Column(name = "LOGIN", nullable = false)
	private String login;
*/
	
    @EmbeddedId
    private TuserPK userId = new TuserPK(); 
	
	/** El password. */
	@Column(name = "PASSWORD")
	private String password;

	/** El recovery. */
	@Column(name = "RECOVERY")
	private String recovery;

	/** Si esta activo. */
	@Column(name = "ACTIVE")
	private Boolean active;

	/** El email. */
	@Column(name = "EMAIL")
	private String email;

	/** El telefono. */
	@Column(name = "PHONE")
	private String phone;

	/** El nombre. */
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;

	/** El idx. */
	@Column(name = "idx")
	private Integer idx;

	/** La fecha de creacion. */
	@Column(name = "CREATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate;
	
	/** La fecha de nacimiento. */
	@Column(name = "BIRTH_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date birthDate;
	
	/** El sexo **/
	@Column(name = "SEX")
	private Integer sex;
	
	/** Clave ajena a la tabla TUSER_TROLE. */
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
	private Collection<TuserTrole> tuserTroleCollection;
	
	
	/** Clave ajena a la tabla DEVICE. */
	@OneToMany(mappedBy = "userId", cascade = CascadeType.ALL)
	private Collection<Device> deviceCollection;
	
	/** Clave ajena a la tabla NOTIFICATION. */
	@OneToMany(mappedBy = "receiver", cascade = CascadeType.ALL)
	private Collection<Notification> notificationCollection;		
	
	
    public TuserPK getUserId() {
        if (this.userId == null) {
            this.userId = new TuserPK();        
        }
        
        return userId;
    }

    public void setUserId(final TuserPK userId) {
        this.userId = userId;
    }
	
/*
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
*/
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRecovery() {
		return recovery;
	}

	public void setRecovery(String recovery) {
		this.recovery = recovery;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdx() {
		return idx;
	}

	public void setIdx(Integer idx) {
		this.idx = idx;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @param userId the userId to set
	 */
	/*
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * @return the userId
	 */
	/*
	public Integer getUserId() {
		return userId;
	}
*/
	/**
	 * @param tuserTroleCollection the tuserTroleCollection to set
	 */
	public void setTuserTroleCollection(Collection<TuserTrole> tuserTroleCollection) {
		this.tuserTroleCollection = tuserTroleCollection;
	}

	/**
	 * @return the tuserTroleCollection
	 */
	public Collection<TuserTrole> getTuserTroleCollection() {
		return tuserTroleCollection;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (userId != null ? userId.hashCode() : 0);
		return hash;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("userId=3");
		if(this.userId != null) {
			if (this.userId.getUserId() != null) {
				sb.append(this.userId.getUserId().toString());
			} else {
				sb.append("");
			}
		}
		sb.append(",3");
		
		sb.append("login=3");
		if(this.userId != null) {
			if (this.userId.getLogin() != null) {
				sb.append(this.userId.getLogin().toString());
			} else {
				sb.append("");
			}
		}
		sb.append(",3");
		
		sb.append("password=3");
		if(password != null) {
			sb.append(this.password.toString());
		}
		sb.append(",3");
		
		sb.append("recovery=3");
		if(recovery != null) {
			sb.append(this.recovery.toString());
		}
		sb.append(",3");
		
		sb.append("active=3");
		if(active != null) {
			sb.append(this.active.toString());
		}
		sb.append(",3");		
		
		sb.append("email=3");
		if(email != null) {
			sb.append(this.email.toString());
		}
		sb.append(",3");
		
		sb.append("phone=3");
		if(phone != null) {
			sb.append(this.phone.toString());
		}
		sb.append(",3");
		
		sb.append("name=3");
		if(name != null) {
			sb.append(this.name.toString());
		}
		sb.append(",3");
		
		sb.append("surname=3");
		if(name != null) {
			sb.append(this.surname.toString());
		}
		sb.append(",3");		
		
		sb.append("idx=3");
		if(idx != null) {
			sb.append(this.idx.toString());
		}
		sb.append(",3");
		
		sb.append("creationDate=3");
		if(creationDate != null) {
			Long fecha = this.creationDate.getTime();
			sb.append(fecha.toString());
		}
		sb.append(",3");
		
		sb.append("birthDate=3");
		if(birthDate != null) {
			Long fecha = this.birthDate.getTime();
			sb.append(fecha.toString());
		}
		sb.append(",3");	
		
		sb.append("sex=3");
		if(sex != null) {
			sb.append(this.sex.toString());
		}
		sb.append(",3");		
		
		sb.append("tuserTroleCollection=3");
		if(idx != null) {
			//sb.append(this.idx.toString());
		}
		sb.append(",3");		
		
		if(deviceCollection != null && deviceCollection.size() > 0) {
			for(Device d : deviceCollection) {
				sb.append("device=3");
				sb.append(d.toString());
				sb.append(",3");
			}
		}
		
		return sb.toString();
	}
	
	public static Tuser parse(String s) {
		String [] v = s.split(",3");
		
		Tuser result = new Tuser();
		result.deviceCollection = new HashSet<Device>();
		result.userId = new TuserPK();
		
		for(String str : v) {
			String [] v2 = str.split("=3");
			
			if(v2.length == 2) {
				if(v2[0].equals("userId")) {
					result.userId.setUserId(Integer.parseInt(v2[1]));
				} else if(v2[0].equals("login")) {
					result.userId.setLogin(v2[1]);
				} else if (v2[0].equals("password")) { 
					result.password = v2[1];
				} else if (v2[0].equals("recovery")) { 
					result.recovery = v2[1];					
				} else if(v2[0].equals("active")) {
					result.active = Boolean.parseBoolean(v2[1]);
				} else if (v2[0].equals("email")) { 
					result.email = v2[1];
				} else if (v2[0].equals("phone")) { 
					result.phone = v2[1];	
				} else if (v2[0].equals("name")) { 
					result.name = v2[1];
				} else if (v2[0].equals("surname")) { 
					result.surname = v2[1];
				} else if (v2[0].equals("idx")) { 
					result.idx = Integer.parseInt(v2[1]);
				} else if (v2[0].equals("creationDate")) { 
					Long fecha = Long.parseLong(v2[1]);
					result.creationDate = new Date(fecha.longValue());
				} else if (v2[0].equals("birthDate")) { 
					Long fecha = Long.parseLong(v2[1]);
					result.birthDate = new Date(fecha.longValue());	
				} else if(v2[0].equals("sex")) {
						result.sex = Integer.parseInt(v2[1]);					
				} else if (v2[0].equals("tuserTroleCollection")) { 
					result.tuserTroleCollection = null;
				} else if (v2[0].equals("device")) { 
					Device d = Device.parse(v2[1]);
					result.deviceCollection.add(d);
				}
			}
		}
		
		return result;
	}		

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in el campo case el campo id
		// fields are
		// not set
		if (!(object instanceof Tuser)) {
			return false;
		}
		Tuser other = (Tuser) object;
		if ((this.userId == null && other.userId != null)
				|| (this.userId != null && !this.userId.equals(other.userId))) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	/*
	@Override
	public String toString() {
		return "net.softforlife.klich.model.Tuser[userId=" + userId + "]";
	}
	*/

	/**
	 * @param deviceCollection the deviceCollection to set
	 */
	public void setDeviceCollection(Collection<Device> deviceCollection) {
		this.deviceCollection = deviceCollection;
	}

	/**
	 * @return the deviceCollection
	 */
	public Collection<Device> getDeviceCollection() {
		return deviceCollection;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Collection<Notification> getNotificationCollection() {
		return notificationCollection;
	}

	public void setNotificationCollection(Collection<Notification> notificationCollection) {
		this.notificationCollection = notificationCollection;
	}	
	
}

