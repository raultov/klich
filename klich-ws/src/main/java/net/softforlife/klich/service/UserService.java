package net.softforlife.klich.service;

import java.util.List;

import net.softforlife.klich.model.Trole;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.model.TuserTrole;

import org.springframework.security.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interface de servicio de usuarios.<br>
 * Define las operaciones que se realizan sobre los usuarios de sistema y sobre los objetos almacenados en sesion de cada
 * uno de los mismos que esten logados en la aplicacion.
 * @author rtovar
 */
public interface UserService {

    /**
     * Devuelve un objeto usuario (Tuser) a partir de su atributo "login".
     * @param login Login del usuario.
     * @return Objeto usuario con todos sus datos inicializados.
     */
    @Transactional
    Tuser getUser(String login);

    /**
     * Devuelve un objeto usuario (Tuser) a partir de su atributo "id" de base de datos.
     * @param userId Login del usuario.
     * @return Objeto usuario con todos sus datos inicializados.
     */
    @Transactional
    Tuser getUserById(int userId);

    /**
     * Almacena en base de datos un objeto usuario (Tuser).
     * @param user El objeto usuario a guardar en el sistema.
     */
    @Transactional
    void createUser(Tuser user);

    /**
     * Almacena en base de datos un objeto usuario (Tuser) , encriptando
     * la password en MD5.
     * @param user
     */
    @Transactional
    public void createUserMD5(Tuser user, Trole userType);    

    /**
     * Actualiza los datos del usuario en base datos.
     * @param user Objeto con los nuevos datos del usuario.
     */
    @Transactional
    void updateUser(Tuser user);

    /**
     * Actualiza en base de datos un objeto usuario (Tuser) , encriptado
     * la password en MD5.
     * @param user
     */
    @Transactional
    public void updateUserMD5(Tuser user);

    /**
     * Borra un usuario del sistema.
     * @param login Login del usuario a eliminar.
     */
    @Transactional
    void removeUser(String login);

    /**
     * Borra usuario relacionado con un rol
     * @param userRol
     */
    @Transactional
    public void removeUserRol(TuserTrole userRol);

    /**
     * Devuelve el usuario relacionado con el rol
     * @param user
     * @param rol
     * @return
     */
    @Transactional
    public TuserTrole getUserRol(Tuser user, Trole rol);

    /**
     * Devuelve la lista de roles de usuario definidos en el sistema.
     * @return Lista de objetos rol (Trol).
     */
    @Transactional
    List<Trole> getListRoles();

    /**
     * Devuelve un rol de usuario de base de datos
     * @param id Identificador del rol
     * @return Objeto Rol
     */
    @Transactional
    public Trole loadById(Integer id);

    /**
     * Devuelve el usuario en sesion.
     * @return Objeto usuario en sesion con todos sus datos inicializados.
     */
    @Transactional
    public Tuser getUserInSession();

    /**
     * Asocia un objeto al usuario en sesion.
     * @param key Identificador del objeto asociado a la sesion del usuario.
     * @param value Objeto asociado a sesion.
     */
    void setSessionProperty(String key, Object value);

    /**
     * Recupera un objeto asociado a la sesion del usuario.
     * @param key Identificador del objeto asociado a la sesion del usuario.
     * @return Objeto asociado a sesion.
     */
    Object getSessionProperty(String key);

    /**
     * Elimina un objeto de la sesion del usuario.
     * @param key Identificador del objeto asociado a la sesion del usuario.
     */
    void clearSessionProperty(String key);

    /**
     * 
     * @return
     */
    @Transactional
    public void removeAll();

    /**
     * Encripta el nuevo password para el usuario
     *
     * @param user - Usuario
     */
    void changePassword(Tuser user);

    @Transactional
    Tuser getUserFromServer (String login) throws UsernameNotFoundException;
}


