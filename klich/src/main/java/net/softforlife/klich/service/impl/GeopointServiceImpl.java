package net.softforlife.klich.service.impl;

import java.util.List;

import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.persistence.DAO.GeopointDAO;
import net.softforlife.klich.persistence.DAO.StatusDAO;
import net.softforlife.klich.service.GeopointService;

import org.apache.log4j.Logger;

public class GeopointServiceImpl implements GeopointService {
	private GeopointDAO geopointDAO;
	private StatusDAO statusDAO;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public Geopoint loadGeopointById(Long id) {
		return geopointDAO.loadById(id);
	}
	
	public List<Geopoint> getAllGeopoints() {
		return geopointDAO.getAllGeoPoints();
	}

	public void setGeopointDAO(GeopointDAO geopointDAO) {
		if(geopointDAO == null) {
			logger.info("geopointDAO nos está viniendo a null");
		}
		
		this.geopointDAO = geopointDAO;
	}
	
	@Override
	public List<Geopoint> getGeopointsByTrack(Long trackId) {
		return geopointDAO.getGeopointsByTrackId(trackId);
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
