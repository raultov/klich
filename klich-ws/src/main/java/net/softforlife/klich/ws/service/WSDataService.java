package net.softforlife.klich.ws.service;

import java.util.List;

import net.softforlife.klich.common.ws.ResultWSMessage;
import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.model.Tuser;

import org.springframework.transaction.annotation.Transactional;

/**
 * Servicio para la gestión de devices
 * 
 * @author rtovar
 */
@Transactional
public interface WSDataService {

    /**
     * Actualización del Device
     *
     * @param device
     */
    public void updateDevice(Device device);
    
    /**
     * Borrado físico de un device
     *
     * @param deviceId
     */
    void deleteDevice(Long deviceId);
    
    Device getDevice(Long deviceId);
    
    List<Tuser> getUserList();
    
    /**
     * Actualiza los datos de los tracks descargados del device
     *
     * @param trackList
     */
    void refreshTracks(Device device);
    
    /**
     * Actualiza los datos de los geopoints descargados del track
     *
     * @param geopointList
     */
    void refreshGeopoints(Track track);    
    
    /**
     * Descarga el device
     * 
     * @param deviceId - deviceId
     */
    ResultWSMessage unloadDevice(Long deviceId);
    
    /**
     * Descarga el track
     * 
     * @param trackId - trackId
     */
    ResultWSMessage unloadTrack(Long trackId);    
    
    /**
     * Descarga el geopoint
     * 
     * @param geopointId - geopointId
     */
    ResultWSMessage unloadGeopoint(Geopoint geopoint);  
    
    /**
     * Guarda un nuevo Geopoint
     * 
     * @param geopoint - geopoint
     */    
    void saveNewGeopoint(Geopoint geopoint);
    
    /**
     * Guarda un nuevo Track
     * 
     * @param Track - track
     */    
    void saveNewTrack(Track track);
    
    /**
     * Reemplaza el último Geopoint que se introdujo por uno nuevo
     * 
     * @param geopoint - geopoint
     */    
    Long replaceLastGeopoint(Geopoint geopoint);
    
    /**
     * Reemplaza geopoint
     * 
     * @param geopoint - geopoint
     */    
    Long replaceGeopoint(Geopoint geopoint);
    
    /**
     * Devuelve el Usuario registrado en caso de estarlo, null en caso contrario
     * 
     * @param Tuser - user
     * @return id
     */      
    Tuser login(Tuser user);
    
    /**
     * Devuelve el Usuario después de haberlo pre-registrado, null en caso de que ya existiera u otro tipo de error
     * 
     * @param Tuser - user
     * @return id
     */      
    Tuser register(Tuser user);
    
    /**
     * Devuelve el Usuario después de haberlo encontrado en la BBDD, null en caso de que ya existiera u otro tipo de error
     * 
     * @param Tuser - user
     * @return id
     */      
    Tuser remember(Tuser user);    
    
    /**
     * Metodo para comprobar la disponibilidad del webservices
     *
     * @return Mensaje del webservices encapsulado
     */
    ResultWSMessage ping ();
    
}