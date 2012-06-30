package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Geopoint;


public interface GeopointDAO extends GenericDAO<Geopoint, Long> {

	public int deleteGeoPointById(Long id);
	
	public Geopoint getNewestGeoPoint();
	
	//public void replaceGeoPoint(Geopoint gp, Geopoint newgp);
	
	public List<Geopoint> getAllGeoPoints();
	
	public List<Geopoint> getGeopointsByTrackId(Long trackId);
}