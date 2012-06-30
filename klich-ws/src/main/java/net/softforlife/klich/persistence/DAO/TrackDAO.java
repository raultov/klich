package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Track;

public interface TrackDAO extends GenericDAO<Track, Integer> {
	public int deleteTrackById(long id);
	
	public Track getNewestTrack();
	
	public void update(Track t);
	
	public void replaceTrack(Track t, Track newt);
	
	public List<Track> getAllTracks();
	
	public void flushTrack();
}

