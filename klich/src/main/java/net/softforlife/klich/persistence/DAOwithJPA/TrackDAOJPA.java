package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.softforlife.klich.model.Track;
import net.softforlife.klich.persistence.DAO.TrackDAO;

public class TrackDAOJPA extends GenericDAOWithJPA<Track, Long> implements TrackDAO {
	
	@Override
	public int deleteTrackById(long id) {
		String sql = "DELETE from Track t WHERE t.TRACK_ID = :tID";
		Query query = getTransactionalEntityManager().createQuery(sql);
		query.setParameter("tID", id);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Track> getAllTracks() {
		String queryString = "SELECT t FROM Track t order by t.date";
		List<Track> res = getJpaTemplate().find(queryString);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Track getNewestTrack() {
		Track tr = null;
		Map<String, Object> params = new HashMap<String, Object>(0);
		
		String queryString = "SELECT t from Track t where t.date = (select MAX(date) from Track)";
		List<Track> res = getJpaTemplate().findByNamedParams(queryString, params);
		if ((res != null) && (res.size() > 0))
			tr = (Track) res.get(0);
		
		return tr;
	}

	@Override
	public void replaceTrack(Track t, Track newt) {
        EntityManager em = getTransactionalEntityManager();
        Query query = em.createQuery("UPDATE Track t SET t.TRACK_DATE = :new_date WHERE t.TRACK_ID = :tID");
        query.setParameter("tID", t.getTrackId());
        query.setParameter("new_date", newt.getDate());
        query.executeUpdate();	
	}

}