package net.softforlife.klich.persistence.DAOwithJPA;

import net.softforlife.klich.model.Trole;
import net.softforlife.klich.persistence.DAO.RoleDAO;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository
public class  RoleDAOJPA extends GenericDAOWithJPA<Trole, Integer> implements RoleDAO {

	/**
	 * La clase RoleDAOJPA.
	 */
	
		public Trole getRole(String roleName) {
	        Trole role = getJpaTemplate().find(Trole.class, roleName);
	        if (role == null) {
	            throw new ObjectRetrievalFailureException(Trole.class, roleName);
	        }
	        return role;
	    }

}
