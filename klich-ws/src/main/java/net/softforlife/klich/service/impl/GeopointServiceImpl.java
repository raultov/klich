package net.softforlife.klich.service.impl;

import java.util.Date;
import java.util.List;

import net.softforlife.klich.enumeration.CLAVE_GEOPOINT;
import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Status;
import net.softforlife.klich.persistence.DAO.GeopointDAO;
import net.softforlife.klich.persistence.DAO.StatusDAO;
import net.softforlife.klich.service.GeopointService;

import org.apache.log4j.Logger;

public class GeopointServiceImpl implements GeopointService {
	private GeopointDAO geopointDAO;
	private StatusDAO statusDAO;
	
	private Logger logger = Logger.getLogger(this.getClass());
	


	public Geopoint loadFromId(Integer id) {
		return geopointDAO.loadById(id);
	}
	

	public void persist(Geopoint geopoint) {
		geopointDAO.persist(geopoint);
	}

	
	public void prueba() {
		Geopoint gp = new Geopoint();
				
		gp.setAccuracy(0.5f);
		gp.setDate(new Date());
		gp.setLatitude(23.997797979);
		gp.setLongitude(-3.445454543);
		gp.setTrackId(null);
		
		Status geoType = statusDAO.loadById(CLAVE_GEOPOINT.GPS.getId());
		gp.setTypeGeopoint(geoType);
		
		geopointDAO.persist(gp);
	}
	
	public void prueba2() {
		List<Geopoint> gps = geopointDAO.loadAll();
		
		logger.info("Vamos a imprimir los geopoints, hay: " + gps.size());
		
		for(Geopoint gp : gps) {
			logger.info("gp: " + gp.getTypeGeopoint());
		}
	}

	public void setGeopointDAO(GeopointDAO geopointDAO) {
		if(geopointDAO == null) {
			logger.info("geopointDAO nos está viniendo a null");
		}
		
		this.geopointDAO = geopointDAO;
		
		//prueba();
	}

	public GeopointDAO getGeopointDAO() {
		return geopointDAO;
	}

	/**
	 * @param statusDAO the statusDAO to set
	 */
	public void setStatusDAO(StatusDAO statusDAO) {
		this.statusDAO = statusDAO;
	}

	/**
	 * @return the statusDAO
	 */
	public StatusDAO getStatusDAO() {
		return statusDAO;
	}

}
