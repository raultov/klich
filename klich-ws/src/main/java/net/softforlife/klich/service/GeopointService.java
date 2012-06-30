package net.softforlife.klich.service;

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
	
	public void prueba();
	
	public void prueba2();
	
	public Geopoint loadFromId(Integer id);
	
	public void persist(Geopoint geopoint);

}
