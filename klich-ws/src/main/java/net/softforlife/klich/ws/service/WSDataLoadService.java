package net.softforlife.klich.ws.service;

import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Track;

import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz para la carga de datos desde el dispositivo remoto y desde servidor
 * 
 * @author rtovar
 */
@Transactional
public interface WSDataLoadService {
	
	/**
	 * Actualización de datos en servidor o movil
         * usado por la carga/descarga
	 * 
	 * @param track
	 */
	
	public void updateTrack(Track track);

	/**
	 * Actualización de datos en servidor o movil
         * usado por la carga/descarga
	 * 
	 * @param geopoint
	 */
	
	public void updateGeopoint(Geopoint geopoint);
}
