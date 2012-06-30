package net.softforlife.klich.persistence.DAO;

import net.softforlife.klich.model.Status;


/**
 * Interfaz de acceso a datos que encapsula las operaciones sobre todos los estados del sistema.
 * 
 * @author atovar
 */
public interface StatusDAO extends GenericDAO<Status,Integer>{
		
	/**
	 * Devuelve el estado en funcion de su clave primaria.
	 * 
	 * @param statusId Clave primaria
	 * 
	 * @return Estado
	 */
    Status getStatus(Integer statusId);
    
    /**
     * Devuelve el estado en funcion de su descripcion corta.
     * 
     * @param description Descripcion corta.
     * 
     * @return Estado.
     */
    Status getStatusByShortDescription(String description);
}