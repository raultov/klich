package net.softforlife.klich.service.impl;

import net.softforlife.klich.model.Track;
import net.softforlife.klich.persistence.DAO.TrackDAO;
import net.softforlife.klich.service.TrackService;

public class TrackServiceImpl implements TrackService {
	private TrackDAO trackDAO;
	
	/**
	 * @param trackDAO the trackDAO to set
	 */
	public void setTrackDAO(TrackDAO trackDAO) {
		this.trackDAO = trackDAO;
	}

	/**
	 * @return the trackDAO
	 */
	public TrackDAO getTrackDAO() {
		return trackDAO;
	}


	public void updateTrack(Track track) {
        Track tmpTrack = trackDAO.loadById(track.getTrackId().intValue());
        
        if (tmpTrack != null) {
            track.setGeopointCollection(null);
            trackDAO.update(track);
        } else {
            this.trackDAO.persist(track);
        }
	}

	@Override
	public Track getTrackById(int trackId) {
		return trackDAO.loadById(trackId);
	}

	@Override
	public Track getNewestTrack() {
		return trackDAO.getNewestTrack();
	}
	
	
}
