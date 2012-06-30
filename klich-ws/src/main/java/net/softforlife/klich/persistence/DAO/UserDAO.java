package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Trole;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.model.TuserTrole;

/**
 * Interfaz de acceso a datos que encapsula las operaciones sobre usuarios de la aplicacion.
 * 
 * @author rtovar
 */

public interface UserDAO extends GenericDAO<Tuser, Integer> {

	/**
	 * Devuelve el usuario en funcion de su login.
	 * 
	 * @param login the login
	 * 
	 * @return the user
	 */

	Tuser getUser(String login);

	/**
	 * Devuelve el usuario en funcion de su clave primaria.
	 * 
	 * @param userId the user id
	 * 
	 * @return the user by id
	 */

	Tuser getUserById(int userId);

	/**
	 * Borramos el usuariorol.
	 * 
	 * @param userRol the user rol
	 */
	void removeUserRol(TuserTrole userRol);

	/**
	 * Dado el usuario y un rol nos devuelve el objeto relacionado con los dos
	 * TroleUser.
	 * 
	 * @param user the user
	 * @param rol the rol
	 * 
	 * @return the user rol
	 */

	TuserTrole getUserRol(Tuser user, Trole rol);
	
	/**
	 * Dado el rol nos devuelve los usuarios con dicho rol
	 * 
	 * @param rol the rol
	 * 
	 * @return the user
	 */

	List<Tuser> getUsersRol(Trole rol);	

	/**
	 * Borra todos los usuarios.
	 */

	void removeAll();

}
