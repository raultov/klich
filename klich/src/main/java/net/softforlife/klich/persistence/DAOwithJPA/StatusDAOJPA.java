package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.softforlife.klich.model.Status;
import net.softforlife.klich.persistence.DAO.StatusDAO;

/**
 * The Class StatusDAOJPA.
 * 
 * @author rtovar
 */
public class StatusDAOJPA extends GenericDAOWithJPA<Status,Integer> implements StatusDAO {

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.StatusDAO#getStatus(java.lang.Integer)
	 */
    public Status getStatus(Integer statusId) {
    	Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("statusId", statusId);
		@SuppressWarnings("unchecked")
		List<Status> status = getJpaTemplate().findByNamedParams("SELECT s FROM Status s WHERE s.statusId = :statusId", params);

		if (status.size() == 0) {
      		return null;
        }

        return status.get(0);
    }
    
    /*
     * (non-Javadoc)
     * @see es.ine.sigue.persistence.StatusDAO#getStatusByShortDescription(java.lang.String)
     */
    public Status getStatusByShortDescription(String description){
    	Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("desc", description);
		@SuppressWarnings("unchecked")
		List<Status> status = getJpaTemplate().findByNamedParams("SELECT s FROM Status s WHERE s.shortDescription = :desc", params);

		if (status.size() == 0) {
      		return null;
        }

        return status.get(0);
    }

}