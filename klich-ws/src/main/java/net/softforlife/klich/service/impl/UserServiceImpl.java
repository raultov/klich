package net.softforlife.klich.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import net.softforlife.klich.enumeration.ROLES;
import net.softforlife.klich.model.Trole;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.model.TuserTrole;
import net.softforlife.klich.persistence.DAO.RoleDAO;
import net.softforlife.klich.persistence.DAO.UserDAO;
import net.softforlife.klich.service.UserService;
import net.softforlife.klich.service.encrypt.SecurityService;
import net.softforlife.klich.service.security.SecurityUser;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.security.providers.encoding.Md5PasswordEncoder;
import org.springframework.security.userdetails.User;
import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Clase de servicio de usuarios.<br>
 * Implementa las operaciones sobre los usuarios del sistema.
 * 
 * @see es.ine.sigue.service.UserService
 * @author scarnicero
 */
public class UserServiceImpl implements UserService {

    // private final Log log = LogFactory.getLog(UserServiceImpl.class);
    /** El user dao. */
    private UserDAO userDAO;
    /** El password encoder. */
    private Md5PasswordEncoder passwordEncoder;
    /** El role dao. */
    private RoleDAO roleDAO = null;
    /** El security service. */
    private SecurityService securityService;
    /** El logger. */
    private Logger logger = Logger.getLogger(this.getClass());
    
	/*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#getUser(java.lang.String)
     */
    @Transactional(readOnly = true)
    public Tuser getUser(String login) {
        Tuser user = getUserDAO().getUser(login);
        /*
         * if (user == null) { throw new
         * ObjectRetrievalFailureException(Tuser.class, login); }
         */
        /*
         * String realPassword =
         * securityService.decryptText(user.getRecovery()); if ( realPassword !=
         * null && realPassword.length() > 0){ user.setPassword(realPassword); }
         */

        return user;
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#getUserById(int)
     */
    @Transactional(readOnly = true)
    public Tuser getUserById(int userId) {
        Tuser user = getUserDAO().getUserById(userId);
        if (user == null) {
            throw new ObjectRetrievalFailureException(Tuser.class, userId);
        }

        /*
         * String realPassword =
         * securityService.decryptText(user.getRecovery()); if ( realPassword !=
         * null && realPassword.length() > 0){ user.setPassword(realPassword); }
         */

        return user;
    }

    /**
     * Devuelve el valor del campo UserDAO.
     *
     * @return Devuelve UserDAO
     */
    public UserDAO getUserDAO() {
        return userDAO;
    }

    /**
     * Establece el valor del campo UserDAO.
     *
     * @param userDAO
     *            es el nuevo valor de UserDAO
     */
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Devuelve el valor del campo PasswordEncoder.
     *
     * @return Devuelve PasswordEncoder
     */
    public Md5PasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * Establece el valor del campo PasswordEncoder.
     *
     * @param passwordEncoder
     *            es el nuevo valor de PasswordEncoder
     */
    public void setPasswordEncoder(Md5PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.ine.sigue.service.UserService#createUserMD5(es.ine.sigue.model.Tuser,
     * es.ine.sigue.model.Trole)
     */
    public void createUserMD5(Tuser user, Trole userType) {

        //SigueDelegaciones delega = ((List<UserDelega>) user.getUserDelegaCollection()).get(0).getDelegaId();

        //user.setIdx(userDAO.nextIndex(delega.getDelegaId()));
        String userName = generateUserName(user, userType);
        user.getUserId().setLogin(userName);

        if (StringUtils.isBlank(user.getPassword())) {
            // TODO: Generar el password
            // user.setPassword(securityService.generatePassword());
            user.setPassword(userName);
        } else {
            user.setPassword(userName);
        }

        changePassword(user);

        Date now = Calendar.getInstance().getTime();
        user.setCreationDate(now);
        user.setActive(true);

        boolean created = false;
        while (!created) {
            if (getUser(userName) == null) {
                userDAO.persist(user);
                created = true;
            } else {
                user.setIdx(user.getIdx() + 1);
                userName = generateUserName(user, userType);
                user.getUserId().setLogin(userName);
            }
        }

    }

    /**
     * Método que genera un userName distinto para cada usuario.
     *
     * @param user
     *            the user
     * @param delega
     *            the delega
     * @param role
     *            the role
     *
     * @return el valor actual de String
     */
    public String generateUserName(Tuser user,
            Trole role) {
        StringBuilder userName = new StringBuilder();

        if (role.equals(ROLES.ROLE_ENTREVISTADOR.getObject())) {
            userName.append("E");
        } else if (role.equals(ROLES.ROLE_INSPECTOR.getObject())) {
            userName.append("I");
        } else if (role.equals(ROLES.ROLE_SUPERVISOR.getObject())) {
            userName.append("T");
        }

       /* String delegaId = delega.getDelegaId();
        if (delegaId.startsWith("DLG")) {
            userName.append(delegaId.substring(3));
        } else {
            userName.append(delegaId);
        }
*/
        userName.append(user.getIdx());

        return userName.toString();
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#createUser(es.ine.sigue.model.Tuser)
     */
    public void createUser(Tuser user) {
        Date now = Calendar.getInstance().getTime();
        user.setCreationDate(now);
        userDAO.persist(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#updateUser(es.ine.sigue.model.Tuser)
     */
    public void updateUser(Tuser user) {
        // changePassword(user);
        userDAO.update(user);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.ine.sigue.service.UserService#updateUserMD5(es.ine.sigue.model.Tuser)
     */
    public void updateUserMD5(Tuser user) {
        userDAO.update(user);

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.ine.sigue.service.UserService#changePassword(es.ine.sigue.model.Tuser)
     */
    public void changePassword(Tuser user) {
        String clearPassword = user.getPassword();
        String passwordMD5 = passwordEncoder.encodePassword(clearPassword, null);
        user.setRecovery(securityService.encryptText(clearPassword));
        user.setPassword(passwordMD5);
    }

    /**
     * Devuelve el valor del campo RoleDAO.
     *
     * @return Devuelve RoleDAO
     */
    public RoleDAO getRoleDAO() {
        return roleDAO;
    }

    /**
     * Establece el valor del campo RoleDAO.
     *
     * @param roleDAO
     *            es el nuevo valor de RoleDAO
     */
    public void setRoleDAO(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#removeUser(java.lang.String)
     */
    public void removeUser(String login) {
        userDAO.delete(getUser(login));
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.ine.sigue.service.UserService#removeUserRol(es.ine.sigue.model.TuserTrole
     * )
     */
    public void removeUserRol(TuserTrole userRol) {
        userDAO.removeUserRol(userRol);
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#getUserRol(es.ine.sigue.model.Tuser,
     * es.ine.sigue.model.Trole)
     */
    public TuserTrole getUserRol(Tuser user, Trole rol) {
        return userDAO.getUserRol(user, rol);
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#getListRoles()
     */
    public List<Trole> getListRoles() {
        return roleDAO.loadAll();
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#loadById(java.lang.Integer)
     */
    public Trole loadById(Integer id) {
        return roleDAO.loadById(id);
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#getUserInSession()
     */
    public Tuser getUserInSession() {

        Tuser usuario = null;
        User u = getSecurityUser();
        if (u != null) {
            usuario = userDAO.getUser(u.getUsername());
        } else {
            // TODO error
        }
        return usuario;
    }

    /**
     * Devuelve el valor del campo SecurityUser, obtenido del contexto de
     * seguridad.
     *
     * @return Devuelve SecurityUser
     */
    private SecurityUser getSecurityUser() {

        SecurityUser securityUser = null;

        if ((SecurityContextHolder.getContext() == null) || !(SecurityContextHolder.getContext() instanceof org.springframework.security.context.SecurityContext) || (SecurityContextHolder.getContext().getAuthentication() == null) || !(SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SecurityUser)) {
            return securityUser;
        } else // si tenemos contexto casteamos
        {
            securityUser = (SecurityUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }

        return securityUser;
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#setSessionProperty(java.lang.String,
     * java.lang.Object)
     */
    public void setSessionProperty(String key, Object value) {

        SecurityUser securityUser = getSecurityUser();

        if (securityUser != null) {
            securityUser.addProperty(key, value);
        } else {
            // TODO ERROR}
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#getSessionProperty(java.lang.String)
     */
    public Object getSessionProperty(String key) {
        SecurityUser securityUser = getSecurityUser();
        if (securityUser != null) {
            return securityUser.getProperty(key);
        } else {
            // TODO ERROR
            return null;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#removeAll()
     */
    public void removeAll() {
        this.userDAO.removeAll();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.ine.sigue.service.UserService#clearSessionProperty(java.lang.String)
     */
    public void clearSessionProperty(String key) {
        SecurityUser securityUser = getSecurityUser();
        if (securityUser != null) {
            securityUser.clearProperty(key);
        } else {
            // TODO ERROR
        }
    }

    /**
     * Devuelve el servicio de seguridad
     *
     * @return the securityService
     */
    public SecurityService getSecurityService() {
        return securityService;

    }

    /**
     * Setter del servicio de seguridad
     *
     * @param securityService
     *            the securityService to set
     */
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.UserService#getUserFromServer(java.lang.String)
     */
    public Tuser getUserFromServer(String login)
            throws UsernameNotFoundException {
        try {
            loadNewUserList();
        } catch (Exception e) {
            logger.warn(
                    "\n Error al descargar los usuarios desde el Webservice",
                    e);
        }

        Tuser u = getUser(login);

        if (u == null) {
            throw new UsernameNotFoundException("User '" + login + "' could not be found ");
        }

        return u;
    }

    /**
     * Carga de la lista de usuarios.
     */
    private void loadNewUserList() {

        // TODO esto habría que sacarlo?el servicio de usuarios debería de NO
        // tener dependencias a otros servicios

        //TODO: Adaptar al nuevo sistema de servicios web
        
        /*Tablet tablet = tabletService.getThisTablet();

        if (tablet != null) {
            // Cliente de webservice
            SigueWSLocal ws = tabletService.getClientWS();

            ws.sendAction(tablet.getTabletName(), tablet.getLicense(), ACTION_TYPE.REFRESH_USER.getId());
        }*/
    }

    /**
     * Almacena el listado de usuarios.
     *
     * @param listaUser
     *            the lista user
     */
    @SuppressWarnings("unused")
	private void saveNewUserList(List<Tuser> listaUser) {

        @SuppressWarnings("rawtypes")
		Iterator iterator = listaUser.iterator();
        while (iterator.hasNext()) {
            Tuser user = (Tuser) iterator.next();
            createUser(depurarUsuario(user));

        }

    }

    /**
     * depurarUsuario.
     *
     * @param user
     *            the user
     *
     * @return el valor actual de Tuser
     */
    private Tuser depurarUsuario(Tuser user) {
        user.setUserId(null); // detached
        List<TuserTrole> listUserRol = new ArrayList<TuserTrole>();
        @SuppressWarnings("rawtypes")
		Iterator iterator = user.getTuserTroleCollection().iterator();
        user.setTuserTroleCollection(null);
        while (iterator.hasNext()) {
            TuserTrole userRols = (TuserTrole) iterator.next();
            userRols.setTuserTroleId(null);
            userRols.setUserId(user);
            userRols.setRoleId(loadById(userRols.getRoleId().getRoleId()));
            listUserRol.add(userRols);
        }
        user.setTuserTroleCollection(listUserRol);
/*
        List<UserDelega> listUserDelega = new ArrayList<UserDelega>();
        Iterator iterDelega = user.getUserDelegaCollection().iterator();
        while (iterDelega.hasNext()) {
            UserDelega userDelega = (UserDelega) iterDelega.next();
            userDelega.setUserDelegaId(null);
            userDelega.setUserId(user);
            userDelega.setDelegaId(new SigueDelegaciones(userDelega.getDelegaId().getDelegaId()));
            listUserDelega.add(userDelega);
        }
        user.setUserDelegaCollection(listUserDelega);
        */
        return user;
    }
    

}
