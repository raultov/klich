package net.softforlife.klich.persistence.DAO;

import java.util.List;

import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.model.Tuser;

import org.springframework.stereotype.Repository;

/**
 * Interfaz de acceso a datos que encapsula las operaciones sobre devices.
 * 
 * @author rtovar
 */
@Repository
public interface WebServiceDAO extends GenericDAO<Device, Integer> {
	
	
    /**
     * Devuelve la lista de devices dados de alta en un usuario.
     *
     * @param user Filtro por usuario.
     *
     * @return lista de tablets.
     */
    public List<Device> getListDevicesUser(Tuser userId);

    /**
     * Devuelve un device en funcion de su clave primaria.
     *
     * @param idDevice Clave primaria.
     *
     * @return Device.
     */
    public Device getDevice(Long idDevice);  
    
    /**
     * Descarga los tracks de un device.
     *
     * @return Lista de tracks descargados.
     */
    public List<Track> getTracksToUnloadDevice();
    
    /**
     * Devuelve una lista de devices ordenados por sus claves primarias.
     *
     * @return the list order by id
     */
    public List<Device> getListOrderById();
    
    
    
    
    
    
}
