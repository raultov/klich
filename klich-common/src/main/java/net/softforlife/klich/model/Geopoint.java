package net.softforlife.klich.model;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import net.softforlife.klich.enumeration.CLAVE_GEOPOINT;

/**
 * Entidad para el manejo puntos geográficos
 * 
 * @author artovar
 */
@Entity
@Table(name = "geopoint")
@NamedQueries( { @NamedQuery(name = "Geopoint.findAll", query = "SELECT g FROM Geopoint g") })
public class Geopoint implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** El identificador (Clave primaria). */
	@Id
	@Column(name = "GEOPOINT_ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@SequenceGenerator(name = "GEOPOINT_SEQ", sequenceName = "SEQ_GEOPOINT_ID")	
    private Long geopointId;
	
	/** El valor literal. */
	@Column(name = "GEOPOINT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
    private Date date;
	
	/** El valor literal. */
	@Column(name = "longitude")
	private Double longitude;
	
	/** El valor literal. */
	@Column(name = "latitude")	
	private Double latitude;
	
	/** Clave ajena a la tabla STATUS para la clave de GEOPOINT */
	@JoinColumn(name = "TYPE_GEOPOINT", referencedColumnName = "STATUS_ID")
	@ManyToOne
	private Status typeGeopoint;
	
	/** El valor literal. */
	@Column(name = "accuracy")	
	private Float accuracy;
	
	/** Clave ajena a la tabla QUESTION. */
	@JoinColumn(name = "TRACK_ID", referencedColumnName = "TRACK_ID")
	@ManyToOne
	private Track trackId;	
    
	public Geopoint() {
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Date getDate() {
		return date;
	}

	public void setLongitude(Double longitude) {
		//this.longitude = Double.valueOf(longitude);
		this.longitude = longitude;
	}

	public Double getLongitude() {
		//return String.valueOf(longitude);
		return longitude;
	}

	public void setLatitude(Double latitude) {
		//this.latitude = Double.valueOf(latitude);
		this.latitude = latitude;
	}

	public Double getLatitude() {
		//return String.valueOf(latitude);
		return latitude;
	}


	public void setAccuracy(Float accuracy) {
		//this.accuracy = Float.valueOf(accuracy);
		this.accuracy = accuracy;
	}

	public Float getAccuracy() {
		//return String.valueOf(accuracy);
		return accuracy;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += (geopointId != null ? geopointId.hashCode() : 0);
		return hash;
	}	
	
	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Geopoint)) {
			return false;
		}
		Geopoint other = (Geopoint) object;
		if ((this.geopointId == null && other.geopointId != null)
				|| (this.geopointId != null && !this.geopointId.equals(other.geopointId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		
		sb.append("geopointId=0");
		if(geopointId != null) {
			sb.append(this.geopointId.toString());
		}
		sb.append(",0");
		
		sb.append("date=0");
		if(date != null) {
			Long fecha = date.getTime();
			sb.append(fecha.toString());
		}
		sb.append(",0");
		
		sb.append("accuracy=0");
		if(accuracy != null) {
			sb.append(this.accuracy.toString());
		}
		sb.append(",0");
		
		sb.append("latitude=0");
		if(latitude != null) {
			sb.append(this.latitude.toString());
		}
		sb.append(",0");

		sb.append("longitude=0");
		if(longitude != null) {
			sb.append(this.longitude.toString());
		}
		sb.append(",0");
		
		sb.append("typeGeopoint=0");
		if(typeGeopoint != null) {
			sb.append(this.typeGeopoint.getStatusId().toString());
		}
		sb.append(",0");
		
		sb.append("trackId=0");
		if(trackId != null) {
			sb.append(this.trackId.getTrackId().toString());
		}

		sb.append(",0");
		
		return sb.toString();
	}
	
	public static Geopoint parse(String s) {
		String [] v = s.split(",0");
		
		Geopoint result = new Geopoint();
		
		for(String str : v) {
			String [] v2 = str.split("=0");
			
			if(v2.length == 2) {
				if(v2[0].equals("geopointId")) {
					result.geopointId = Long.parseLong(v2[1]);
				} else if(v2[0].equals("date")) {
					Long fecha = Long.parseLong(v2[1]);
					result.date = new Date(fecha.longValue());
				} else if(v2[0].equals("accuracy")) {
					result.accuracy = Float.valueOf(v2[1]);
				} else if(v2[0].equals("latitude")) {
					result.latitude = Double.parseDouble(v2[1]);
				} else if(v2[0].equals("longitude")) {
					result.longitude = Double.parseDouble(v2[1]);
				} else if(v2[0].equals("typeGeopoint")) {
					result.typeGeopoint = CLAVE_GEOPOINT.parse(v2[1]);
					
				} else if(v2[0].equals("trackId")) {
					result.trackId = new Track();
					result.trackId.setTrackId(Long.parseLong(v2[1]));
				}
			}
		}
		
		return result;
	}


	/**
	 * @param geopointId the geopointId to set
	 */
	public void setGeopointId(Long geopointId) {
		this.geopointId = geopointId;
	}

	/**
	 * @return the geopointId
	 */
	public Long getGeopointId() {
		return geopointId;
	}

	/**
	 * @param trackId the trackId to set
	 */
	public void setTrackId(Track trackId) {
		this.trackId = trackId;
	}

	/**
	 * @return the trackId
	 */
	public Track getTrackId() {
		return trackId;
	}

	/**
	 * @param typeGeopoint the typeGeopoint to set
	 */
	public void setTypeGeopoint(Status typeGeopoint) {
		this.typeGeopoint = typeGeopoint;
	}

	/**
	 * @return the typeGeopoint
	 */
	public Status getTypeGeopoint() {
		return typeGeopoint;
	}		
};



