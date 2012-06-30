package net.softforlife.klich.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * Entidad para el manejo puntos geográficos
 * 
 * @author artovar
 */

@Entity
@Table(name = "track")
@NamedQueries( { @NamedQuery(name = "track.findAll", query = "SELECT t FROM Track t") })
public class Track implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	
	/** El identificador (Clave primaria). */
	@Id
	@Column(name = "TRACK_ID", nullable = false)
	@Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long trackId;
	
	/** El valor literal. */
	@Column(name = "DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date date;	
	
	@OneToMany(mappedBy = "trackId", fetch = FetchType.LAZY)
	@OrderBy(value = "date")
	private Collection<Geopoint> geopointCollection;	
	
	/** Clave ajena a la tabla DEVICE. */
	@JoinColumn(name = "DEVICE_ID", referencedColumnName = "DEVICE_ID")
	@ManyToOne
	private Device deviceId;

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setGeopointCollection(Collection<Geopoint> geopointCollection) {
		this.geopointCollection = geopointCollection;
	}

	public Collection<Geopoint> getGeopointCollection() {
		return geopointCollection;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (trackId != null ? trackId.hashCode() : 0);
		return hash;
	}	
	
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Track)) {
			return false;
		}
		Track other = (Track) object;
		if ((this.trackId == null && other.trackId != null)
				|| (this.trackId != null && !this.trackId.equals(other.trackId))) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("trackId=1");
		if(trackId != null) {
			sb.append(this.trackId.toString());
		}
		sb.append(",1");
		
		sb.append("date=1");
		if(date != null) {
			Long fecha = date.getTime();
			sb.append(fecha.toString());
		}
		sb.append(",1");
		
		sb.append("deviceId=1");
		if(deviceId != null) {
			sb.append(this.deviceId.getDeviceId().toString());
		}

		sb.append(",1");
		
		if(geopointCollection != null && geopointCollection.size() > 0) {
			for(Geopoint g : geopointCollection) {
				sb.append("geopoint=1");
				sb.append(g.toString());
				sb.append(",1");
			}
		}
		
		return sb.toString();
	}
	
	public static Track parse(String s) {
		String [] v = s.split(",1");
		
		Track result = new Track();
		result.geopointCollection = new HashSet<Geopoint>();
		
		for(String str : v) {
			String [] v2 = str.split("=1");
			
			if(v2.length == 2) {
				if(v2[0].equals("trackId")) {
					result.trackId = Long.parseLong(v2[1]);
				} else if(v2[0].equals("date")) {
					Long fecha = Long.parseLong(v2[1]);
					result.date = new Date(fecha.longValue());
				} else if(v2[0].equals("geopoint")) {
					Geopoint g = Geopoint.parse(v2[1]);
					result.geopointCollection.add(g);
					
				} else if(v2[0].equals("deviceId")) {
					result.deviceId = new Device();
					result.deviceId.setDeviceId(Long.parseLong(v2[1]));
				}
			}
		}
		
		return result;
	}

	/**
	 * @param trackId the trackId to set
	 */
	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	/**
	 * @return the trackId
	 */
	public Long getTrackId() {
		return trackId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Device deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceId
	 */
	public Device getDeviceId() {
		return deviceId;
	}	
}
