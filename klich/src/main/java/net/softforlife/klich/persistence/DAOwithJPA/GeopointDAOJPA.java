package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.persistence.DAO.GeopointDAO;

public class GeopointDAOJPA extends GenericDAOWithJPA<Geopoint, Long> implements GeopointDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Geopoint> getAllGeoPoints() {
		String queryString = "SELECT g FROM Geopoint g order by g.date";
		List<Geopoint> res = getJpaTemplate().find(queryString);
		return res;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Geopoint getNewestGeoPoint() {
		Geopoint gp = null;
		Map<String, Object> params = new HashMap<String, Object>(0);
		
		String queryString = "SELECT g from Geopoint g where GEOPOINT_DATE = (select MAX(GEOPOINT_DATE) from geopoints)";
		List<Geopoint> res = getJpaTemplate().findByNamedParams(queryString, params);
		
		if ((res != null) && (res.size() > 0)) {
			gp = (Geopoint) res.get(0);
		}
		
		return gp;
	}
/*
	@Override
	public void replaceGeoPoint(Geopoint gp, Geopoint newgp) {
        EntityManager em = getTransactionalEntityManager();
        Query query = em.createQuery("UPDATE Geopoint g SET g.GEOPOINT_DATE = :new_date, g.longitude = :new_long, " +
        		"g.latitude = :new_lat, g.type = :new_type, g.accuracy = :new_acc WHERE g.GEOPOINT_ID = :gID");
        query.setParameter("gID", gp.getGeopointId());
        query.setParameter("new_date", newgp.getDate());
        query.setParameter("new_long", newgp.getLongitude());
        query.setParameter("new_lat", newgp.getLatitude());
        query.setParameter("new_type", newgp.getType());
        query.setParameter("new_acc", newgp.getAccuracy());
        query.executeUpdate();		
	}
*/
	@Override
	public void delete(Geopoint entity) {
		String sql = "DELETE from Geopoint g WHERE g.GEOPOINT_ID = :gID";
		Query query = getTransactionalEntityManager().createQuery(sql);
		query.setParameter("gID", entity.getGeopointId());
		query.executeUpdate();
	}


	@Override
	public void persist(Geopoint entity) {
		super.persist(entity);
	}

	@Override
	public int deleteGeoPointById(Long id) {
		String sql = "DELETE from Geopoint g WHERE g.GEOPOINT_ID = :gID";
		Query query = getTransactionalEntityManager().createQuery(sql);
		query.setParameter("gID", id);
		return query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Geopoint> getGeopointsByTrackId(Long trackId) {
		List<Geopoint> listGeopoints;
		Map<String, Object> params = new HashMap<String, Object>(1);
		
		String queryString = "SELECT g FROM Geopoint g WHERE g.trackId = :tId order by g.date";
		params.put("tId", trackId);
		listGeopoints = getJpaTemplate().findByNamedParams(queryString, params);
		
		return listGeopoints;
	}

};




















