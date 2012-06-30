package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Geopoint;


public interface GeopointDAO extends GenericDAO<Geopoint, Integer> {

	public int deleteGeoPointById(long id);
	
	public Geopoint getNewestGeopoint();
	
	public void replaceGeopoint(Geopoint gp, Geopoint newgp);
	
	public Long replaceLastGeopoint(Geopoint gp);
	
	public List<Geopoint> getAllGeoPoints();
	
	public void flushGeopoint();
}