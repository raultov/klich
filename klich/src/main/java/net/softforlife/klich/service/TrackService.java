/**
 * 
 */
package net.softforlife.klich.service;

import java.util.List;

import net.softforlife.klich.model.Track;

import org.primefaces.model.map.LatLng;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Interface del Servicio de Track.
 * 
 * Define todas las operaciones que se pueden realizar sobre Track
 * 
 * @author rtovar
 * 
 */
@Transactional
public interface TrackService {
	public List<Track> getListAllTracks();
	
	public Track getTrackById(Long trackId);
	
	public Track getNewestTrack();
	
	public LatLng getAverageCenter(Track track);
	
	public LatLng getMedianCenter(Track track);
}
