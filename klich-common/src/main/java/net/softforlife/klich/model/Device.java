package net.softforlife.klich.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * Entidad para el manejo de cada dispositivo de usuario.
 * 
 * @author rtovar
 */

@Entity
@Table(name = "Device")
@NamedQueries( {
		@NamedQuery(name = "Device.findAll", query = "SELECT t FROM Device t"),
		@NamedQuery(name = "Device.findByDeviceId", query = "SELECT t FROM Device t WHERE t.deviceId = :deviceId"),
		@NamedQuery(name = "Device.findByDeviceModel", query = "SELECT t FROM Device t WHERE t.model = :model")
		})
public class Device implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Basic(optional = false)
	@Column(name = "DEVICE_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long deviceId;
	
	/** El modelo del device. */
	@Column(name = "MODEL")
	private String model;
	
	/** El brand del device. */
	@Column(name = "BRAND")
	private String brand;	
	
	/** El type del device. */
	@Column(name = "TYPE")
	private String type;	
	
	/** Clave ajena a la tabla TRACK. */
	@OneToMany(mappedBy = "deviceId", cascade = CascadeType.ALL)
	private Collection<Track> trackCollection;
	
	/** Clave ajena a la tabla DEVICE. */
	@JoinColumns({
		@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
		@JoinColumn(name = "LOGIN", referencedColumnName = "LOGIN")
	})
	@ManyToOne(optional = false)
	private Tuser userId;
	

	/**
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the model
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param trackCollection the trackCollection to set
	 */
	public void setTrackCollection(Collection<Track> trackCollection) {
		this.trackCollection = trackCollection;
	}

	/**
	 * @return the trackCollection
	 */
	public Collection<Track> getTrackCollection() {
		return trackCollection;
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

	/**
	 * @param brand the brand to set
	 */
	public void setBrand(String brand) {
		this.brand = brand;
	}

	/**
	 * @return the brand
	 */
	public String getBrand() {
		return brand;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("deviceId=2");
		if(deviceId != null) {
			sb.append(this.deviceId.toString());
		}
		sb.append(",2");
		
		sb.append("model=2");
		if(model != null) {
			sb.append(model.toString());
		}
		sb.append(",2");
		
		sb.append("brand=2");
		if(brand != null) {
			sb.append(this.brand.toString());
		}
		sb.append(",2");
		
		sb.append("type=2");
		if(type != null) {
			sb.append(this.type.toString());
		}
		sb.append(",2");
		
		sb.append("userId=2");
		if(userId != null) {
			if (userId.getUserId() != null) {
				sb.append(userId.getUserId().toString());
			} else {
				sb.append("");
			}
		}
		sb.append(",2");		
		
		
		if(trackCollection != null && trackCollection.size() > 0) {
			for(Track t : trackCollection) {
				sb.append("track=2");
				sb.append(t.toString());
				sb.append(",2");
			}
		}
		
		return sb.toString();
	}	
	
	public static Device parse(String s) {
		String [] v = s.split(",2");
		
		Device result = new Device();
		result.trackCollection = new HashSet<Track>();
		
		for(String str : v) {
			String [] v2 = str.split("=2");
			
			if(v2.length == 2) {
				if(v2[0].equals("deviceId")) {
					result.deviceId = Long.parseLong(v2[1]);
				} else if(v2[0].equals("model")) {
					result.model = v2[1];
				} else if (v2[0].equals("brand")) { 
					result.brand = v2[1];
				} else if (v2[0].equals("type")) { 
					result.type = v2[1];					
				} else if(v2[0].equals("track")) {
					Track t = Track.parse(v2[1]);
					result.trackCollection.add(t);
				} else if(v2[0].equals("userId")) {
					result.userId = new Tuser();
					TuserPK userPK = new TuserPK();
					userPK.setUserId(Integer.parseInt(v2[1]));
					result.userId.setUserId(userPK);
				}
			}
		}
		
		return result;
	}	
	
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Device)) {
			return false;
		}
		Device other = (Device) object;
		if ((this.deviceId == null && other.deviceId != null)
				|| (this.deviceId != null && !this.deviceId.equals(other.deviceId))) {
			return false;
		}
		
		return true;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (deviceId != null ? deviceId.hashCode() : 0);
		return hash;
	}	
	
	
}
