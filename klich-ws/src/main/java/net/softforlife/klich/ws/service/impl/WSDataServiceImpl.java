package net.softforlife.klich.ws.service.impl;

import java.util.Iterator;
import java.util.List;

import net.softforlife.klich.common.ws.ResultWSMessage;
import net.softforlife.klich.enumeration.ROLES;
import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.persistence.DAO.GeopointDAO;
import net.softforlife.klich.persistence.DAO.TrackDAO;
import net.softforlife.klich.persistence.DAO.UserDAO;
import net.softforlife.klich.persistence.DAO.WebServiceDAO;
import net.softforlife.klich.service.encrypt.SecurityService;
import net.softforlife.klich.ws.service.WSDataLoadService;
import net.softforlife.klich.ws.service.WSDataService;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

/**
 * Interfaz para los webservices
 * 
 * @author rtovar
 */
@Transactional
public class WSDataServiceImpl implements WSDataService {
	
	private Logger logger = Logger.getLogger(this.getClass());

	private WebServiceDAO webServiceDAO = null;
	private UserDAO userDAO = null;
	private WSDataLoadService wsDataLoadService = null;
	private SecurityService securityService = null;
	private TrackDAO trackDAO = null;
	private GeopointDAO geopointDAO = null;



	@Override
	public void updateDevice(Device device) {
		webServiceDAO.update(device);
	}
	
	@Override
	public void deleteDevice(Long deviceId) {
        this.webServiceDAO.delete(this.webServiceDAO.getDevice(deviceId));
	}
	
    @Override
    public Device getDevice(Long deviceId) {
        return webServiceDAO.getDevice(deviceId);
    }
    
    @Override
    public List<Tuser> getUserList() {
    	List<Tuser> userList = userDAO.getUsersRol(ROLES.ROLE_USER.getObject());
    	
    	return userList;
    }
    
    @Override
    public void refreshTracks(Device device) {
        if (device.getTrackCollection() != null) {
            Iterator<Track> iterator = device.getTrackCollection().iterator();

            while (iterator.hasNext()) {
                Track track = (Track) iterator.next();

                if (track.getGeopointCollection() != null) {
                    wsDataLoadService.updateTrack(track);
                }
            }
        }
    }
    

	@Override
	public ResultWSMessage unloadDevice(Long deviceId) {
        logger.debug(" UNLOAD Device " + deviceId);

        Device d = getDevice(deviceId);

        logger.debug("UNLOAD (1) Tracks a enviar -> \n" + d.getDeviceId());

        //ResultWSMessage result = clientWS.unloadDevice(deviceId, d);

        // Limpiamos el contenido del device
        //logger.debug("UNLOAD (2) Limpiamos el contenido del device " + deviceId);
        //removeAll(0);

        //result.setData(null);
        return null;
	}


	@Override
	public ResultWSMessage unloadTrack(Long trackId) {
        logger.debug(" UNLOAD Track " + trackId);

        Track t = trackDAO.loadById(trackId.intValue());

        logger.debug("UNLOAD (1) Geopoints a enviar -> \n" + t.getTrackId());

        //ResultWSMessage result = clientWS.unloadTrack(trackId, t);

        // Limpiamos el contenido del device
        //logger.debug("UNLOAD (2) Limpiamos el contenido del device " + deviceId);
        //removeAll(0);

        //result.setData(null);
        return null;
	}	
	

	@Override
	public ResultWSMessage unloadGeopoint(Geopoint geopoint) {
        logger.debug(" UNLOAD Geopoint " + geopoint);
        
        ResultWSMessage result = new ResultWSMessage();

        geopointDAO.persist(geopoint);

        result.setType(ResultWSMessage.TYPE.OK.ordinal());
        result.setMessage(" GEOPOINT DESCARGADO");
        
        return result;
	}
	

	@Override
	public void saveNewGeopoint(Geopoint geopoint) {
        logger.debug(" SAVE Geopoint " + geopoint);

        geopointDAO.persist(geopoint);

        while(geopoint.getGeopointId() == null) {
        	geopointDAO.flushGeopoint();
        }
	}
	
	@Override
	public void saveNewTrack(Track track) {
        logger.debug(" SAVE Track " + track);

        trackDAO.persist(track);
        
        // Forzamos la realización de la transacción hasta obtener
        // el id del track
        while(track.getTrackId() == null) {
        	trackDAO.flushTrack();
        }
	}
	
	@Override
	public Long replaceLastGeopoint(Geopoint geopoint) {
		logger.debug(" REPLACE Last Geopoint " + geopoint);
		
		return geopointDAO.replaceLastGeopoint(geopoint);
	}
	
	@Override
	public void refreshGeopoints(Track track) {
        if (track.getGeopointCollection() != null) {
            Iterator<Geopoint> iterator = track.getGeopointCollection().iterator();

            while (iterator.hasNext()) {
                Geopoint geopoint = (Geopoint) iterator.next();

                if (geopoint != null) {
                    wsDataLoadService.updateGeopoint(geopoint);
                }
            }
        }
	}
	

	@Override
	public Long replaceGeopoint(Geopoint geopoint) {
		logger.debug(" REPLACE Geopoint " + geopoint);
		
		geopointDAO.replaceGeopoint(geopoint, geopoint);
		
		return geopoint.getGeopointId();
	}
	
	@Override
	public Tuser login(Tuser user) {
		
		logger.debug(" LOGIN " + user);
		
		Tuser user_persisted = userDAO.getUser(user.getUserId().getLogin());
		
		if (user_persisted != null) {
			if (user_persisted.getPassword().equals(user.getPassword())) {
				return user_persisted;
			}
		}
		
		return null;
	}
	
	@Override
	public Tuser register(Tuser user) {
		logger.debug(" REGISTER " + user);
		
		Tuser user_persisted = userDAO.getUser(user.getUserId().getLogin());
		
		if (user_persisted == null) {
			user.setActive(false);
			userDAO.persist(user);
			return user;
		}

		return null;
	}
	
	@Override
	public Tuser remember(Tuser user) {
		logger.debug(" REMEMBER " + user);
		
		Tuser user_persisted = userDAO.getUser(user.getUserId().getLogin());
		
		if (user_persisted != null) {
			return user_persisted;
		}

		return null;
	}	
	
	@Override
	public ResultWSMessage ping() {
		return null;
		// return clientWS.ping();
	}
	
	/**
	 * @return the userDAO
	 */
	public UserDAO getUserDAO() {
		return userDAO;
	}

	/**
	 * @param userDAO the userDAO to set
	 */
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * @return the webServiceDAO
	 */
	public WebServiceDAO getWebServiceDAO() {
		return webServiceDAO;
	}

	/**
	 * @param webServiceDAO the webServiceDAO to set
	 */
	public void setWebServiceDAO(WebServiceDAO webServiceDAO) {
		this.webServiceDAO = webServiceDAO;
	}


	/**
	 * @return the wsDataLoadService
	 */
	public WSDataLoadService getWsDataLoadService() {
		return wsDataLoadService;
	}

	/**
	 * @param wsDataLoadService the wsDataLoadService to set
	 */
	public void setWsDataLoadService(WSDataLoadService wsDataLoadService) {
		this.wsDataLoadService = wsDataLoadService;
	}
	


	/**
	 * @return the clientWS
	 
	public KlichWS getClientWS() {
		return clientWS;
	}

	/**
	 * @param clientWS the clientWS to set
	 
	public void setClientWS(KlichWS clientWS) {
		this.clientWS = clientWS;
	}
*/


	/**
	 * @return the trackDAO
	 */
	public TrackDAO getTrackDAO() {
		return trackDAO;
	}

	/**
	 * @param trackDAO the trackDAO to set
	 */
	public void setTrackDAO(TrackDAO trackDAO) {
		this.trackDAO = trackDAO;
	}
	


	/**
	 * @return the geopointDAO
	 */
	public GeopointDAO getGeopointDAO() {
		return geopointDAO;
	}

	/**
	 * @param geopointDAO the geopointDAO to set
	 */
	public void setGeopointDAO(GeopointDAO geopointDAO) {
		this.geopointDAO = geopointDAO;
	}

	public SecurityService getSecurityService() {
		return securityService;
	}

	public void setSecurityService(SecurityService securityService) {
		this.securityService = securityService;
	}

}
