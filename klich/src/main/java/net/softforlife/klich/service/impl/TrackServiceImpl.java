package net.softforlife.klich.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.primefaces.model.map.LatLng;

import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.persistence.DAO.TrackDAO;
import net.softforlife.klich.service.TrackService;
/**
 * 
 * @author raul
 *
 */

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

	@Override
	public List<Track> getListAllTracks() {
		return trackDAO.getAllTracks();
	}

	@Override
	public Track getTrackById(Long trackId) {
		return trackDAO.loadById(trackId);
	}

	@Override
	public Track getNewestTrack() {
		return trackDAO.getNewestTrack();
	}

	public LatLng getAverageCenter(Track track) {
		
		LatLng center = null;
		
		double sumLat = 0.0, sumLng = 0.0;

		if ((track != null) && (track.getGeopointCollection() != null)) {
			for (Geopoint geopoint : track.getGeopointCollection()) {
				sumLng += geopoint.getLongitude();
				sumLat += geopoint.getLatitude();
			}
		}
		
		// Calculamos la media de las latitudes y las longitudes
		if ((track != null) && (track.getGeopointCollection() != null) && (track.getGeopointCollection().size() > 0)) {
			center = new LatLng(sumLat / track.getGeopointCollection().size(), sumLng / track.getGeopointCollection().size());
		}
		
		return center;
	}
	
	public LatLng getMedianCenter(Track track) {
		
		LatLng center = null;
		
		List <Double> latitudes = new ArrayList<Double>();
		List <Double> longitudes = new ArrayList<Double>();
		
		for (Geopoint geopoint : track.getGeopointCollection()) {
			longitudes.add(geopoint.getLongitude());
			latitudes.add(geopoint.getLatitude());
		}
		
		// Calculamos la mediana de las latitudes y las longitudes
		if ((track.getGeopointCollection() != null) && (track.getGeopointCollection().size() > 0)) {
			Collections.sort(latitudes);
			Collections.sort(longitudes);
			
			int length = latitudes.size();
			
			if ((length % 2) == 0) {
				double latMed = (latitudes.get(length/2) + latitudes.get((length/2) - 1)) / 2.0;
				double logMed = (longitudes.get(length/2) + longitudes.get((length/2) - 1)) / 2.0;
				
				center = new LatLng(latMed, logMed);
			} else {
				center = new LatLng(latitudes.get(length/2), longitudes.get(length/2));
			}
		}
		
		return center;
	}
		
	
	public Double getRadio(Track track, LatLng center) {
		double max = -1.0;
		
		for (Geopoint geopoint : track.getGeopointCollection()) {
			double distance = Math.hypot(geopoint.getLatitude() - center.getLat(), geopoint.getLongitude() - center.getLng());
			
			if (distance > max) {
				max = distance;
			}
		}
		
		return max;
	}

};




































