package net.softforlife.klich.JSF.web.beans;

import net.softforlife.klich.service.GeopointService;
import net.softforlife.klich.service.TrackService;

import org.apache.log4j.Logger;

public class MapsBean {
	private GeopointService geopointService;
	private TrackService trackService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	public String getPrueba() {
		if(geopointService == null) {
			return "mierda";
		}
		
		geopointService.prueba();
		
		geopointService.prueba2();
		
		return "desde los Padules";
	}

	public void setGeopointService(GeopointService geopointService) {
		if(geopointService == null) {
			logger.info("El geopointService nos está llegando a null");
		}
		
		this.geopointService = geopointService;
	}

	public GeopointService getGeopointService() {
		return geopointService;
	}

	/**
	 * @param trackService the trackService to set
	 */
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	/**
	 * @return the trackService
	 */
	public TrackService getTrackService() {
		return trackService;
	}
}
