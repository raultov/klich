package net.softforlife.klich.ws.service.impl;

import java.util.Collection;

import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.service.GeopointService;
import net.softforlife.klich.service.TrackService;
import net.softforlife.klich.ws.service.WSDataLoadService;

public class WSDataLoadServiceImpl implements WSDataLoadService {

	private TrackService trackService = null;
	
	private GeopointService geopointService = null;
	/**
	 * Método de carga de datos en Servidor. Recibe una track con todos los
	 * datos en objetos compuestos
	 */
	public void updateTrack(Track track) {
		Collection<Geopoint> geopoints = track.getGeopointCollection();

		if(geopoints != null) {
			trackService.updateTrack(track);
			
			//Track persistedTrack = trackService.getTrackFromId(track.getTrackId().intValue());
			
			for(Geopoint gp : geopoints) {
				//Geopoint persistedGeopoint = geopointSevice.loadFromId(Integer.valueOf(gp.getGeopointId().intValue()));
				geopointService.persist(gp);
			}
		}
	}
	
	@Override
	public void updateGeopoint(Geopoint geopoint) {
		if(geopoint != null) {
			geopointService.persist(geopoint);
		}
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
	/**
	 * @param geopointSevice the geopointSevice to set
	 */
	public void setGeopointService(GeopointService geopointService) {
		this.geopointService = geopointService;
	}
	/**
	 * @return the geopointSevice
	 */
	public GeopointService getGeopointService() {
		return geopointService;
	}


}
