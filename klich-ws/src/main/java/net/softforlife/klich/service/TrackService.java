/**
 * 
 */
package net.softforlife.klich.service;

import net.softforlife.klich.model.Track;

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

	/**
	 * Método para la actualziación de un track
	 * 
	 * @param track
	 */
	public void updateTrack(Track track);
	
	public Track getTrackById(int trackId);
	
	public Track getNewestTrack();
}
