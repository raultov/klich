package net.softforlife.klich.persistence.DAOwithJPA;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import net.softforlife.klich.model.Trole;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.model.TuserTrole;
import net.softforlife.klich.persistence.DAO.UserDAO;

import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOJPA extends GenericDAOWithJPA<Tuser, Integer> implements UserDAO {

	@Override
	public Tuser getUser(String login) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("login", login);
		@SuppressWarnings("unchecked")
		List<Tuser> users = getJpaTemplate().findByNamedQueryAndNamedParams(
				"Tuser.findByLogin", params);

		if (users.size() == 0) {
			// throw new ObjectRetrievalFailureException(Tuser.class, login);
			return null;
		}

		return users.get(0);
	}

	@Override
	public Tuser getUserById(int userId) {
		Tuser user = loadById(userId);

		if (user == null) {
			throw new ObjectRetrievalFailureException(Tuser.class, userId);
		}

		return user;
	}

	@Override
	public void removeUserRol(TuserTrole userRol) {
		EntityManager em = getTransactionalEntityManager();
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" DELETE FROM TuserTrole t WHERE t.userId = :user ");
		queryStr.append(" AND t.roleId = :role");

		Query query = em.createQuery(queryStr.toString());
		query.setParameter("user", userRol.getUserId());
		query.setParameter("role", userRol.getRoleId());

		query.executeUpdate();
	}

	@Override
	public TuserTrole getUserRol(Tuser user, Trole rol) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		params.put("userid", user.getUserId());
		params.put("rolid", rol.getRoleId());
		StringBuilder query = new StringBuilder();

		query.append("FROM TuserTrole t ");
		query.append("WHERE t.userId.userId = :userid ");
		query.append(" AND t.roleId.roleId = :rolid");
		query.append(" order by t.tuserTroleId");

		@SuppressWarnings("unchecked")
		List<TuserTrole> userTrole = getJpaTemplate().findByNamedParams(
				query.toString(), params);

		TuserTrole result;
		if (userTrole.isEmpty()) {
			result = new TuserTrole();
		} else {
			result = userTrole.get(0);
		}

		return result;
	}

	@Override
	public void removeAll() {
		EntityManager em = getTransactionalEntityManager();
		Query query = em.createQuery(" DELETE FROM TuserTrole ");
		query.executeUpdate();

		query = em.createQuery(" DELETE FROM Tuser ");
		query.executeUpdate();
	}


}
