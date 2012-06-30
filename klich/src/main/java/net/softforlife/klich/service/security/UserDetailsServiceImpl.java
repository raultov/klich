package net.softforlife.klich.service.security;

import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.model.TuserTrole;
import net.softforlife.klich.service.UserService;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.GrantedAuthorityImpl;
import org.springframework.security.userdetails.UserDetails;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.security.userdetails.UsernameNotFoundException;

/**
 * Implementación del servicio de autenticación de spring security
 * 
 * @author rtovar
 */

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	private final Log log = LogFactory.getLog(UserDetailsServiceImpl.class);
	private UserService userManager = null;

	public void setUserManager(UserService userManager) {
		this.userManager = userManager;
	}

	/**
	 * Carga de un usuario para y por los servicios de acegi.
	 */

	@Transactional(propagation = Propagation.REQUIRED)
	public UserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException, DataAccessException {
		
		if (log.isDebugEnabled()) {
			log.debug("Security verification for user '" + login + "'");
		}
		Tuser user = null;
		try {
			user = userManager.getUser(login);
		} catch (ObjectRetrievalFailureException orfe) {
			throw new UsernameNotFoundException("User '" + login
					+ "' could not be found.");
		}

		if (user == null) {
			throw new UsernameNotFoundException("User '" + login + "' could not be found.");	
		}

		// Collection<Trole> roles = user.getRoleIdCollection();
		Collection<TuserTrole> roles = user.getTuserTroleCollection();
		GrantedAuthority[] arrayAuths = new GrantedAuthority[roles.size()];
		int index = 0;
		for (TuserTrole role : roles) {
			arrayAuths[index++] = new GrantedAuthorityImpl(role.getRoleId()
					.getLkey());
		}

		SecurityUser securityUser = new SecurityUser(login, user.getPassword(),
				user.getActive(), true, true, true, arrayAuths);

		securityUser.setUserData(user);

		if (user.getUserId().getUserId() <= 0) {
			log.debug("Usuario por defecto de instalaci\u00F3n. La aplicaci\u00F3n puede que no funcione correctamente");
		}

		return securityUser;
	}

};



