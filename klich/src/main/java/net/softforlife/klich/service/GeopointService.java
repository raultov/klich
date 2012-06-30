package net.softforlife.klich.service;

import java.util.List;

import net.softforlife.klich.model.Geopoint;

import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * Interface del Servicio de Geopoint.
 * 
 * Define todas las operaciones que se pueden realizar sobre Geopoint
 * 
 * @author rtovar
 * 
 */
@Transactional
public interface GeopointService {
	
	public Geopoint loadGeopointById(Long id);
	
	public List<Geopoint> getAllGeopoints();
	
	public List<Geopoint> getGeopointsByTrack(Long trackId);

}
