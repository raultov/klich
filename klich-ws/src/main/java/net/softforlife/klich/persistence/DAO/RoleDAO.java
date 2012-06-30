package net.softforlife.klich.persistence.DAO;

import net.softforlife.klich.model.Trole;

/**
 * Interfaz de acceso a datos que encapsula las operaciones sobre roles de usuarios
 * en la aplicacion.
 * 
 * @author rtovar
 */
public interface RoleDAO extends GenericDAO<Trole, Integer> {
	
	/**
	 * Devuelve un rol a partir de su nombre, que debe ser unico.
	 * 
	 * @param roleName Nombre de rol.
	 * 
	 * @return Rol
	 */
	public Trole getRole(String roleName);
}
