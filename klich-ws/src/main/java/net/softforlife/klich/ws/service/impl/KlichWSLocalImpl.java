package net.softforlife.klich.ws.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.softforlife.klich.common.ws.ACTION_TYPE;
import net.softforlife.klich.common.ws.KlichWSLocal;
import net.softforlife.klich.common.ws.ResultWSMessage;
import net.softforlife.klich.enumeration.NOTIFICATION_TYPE;
import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Notification;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.service.NotificationService;
import net.softforlife.klich.ws.service.WSDataService;
import net.softforlife.klich.ws.service.WSEmailSender;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

//servidor
@WebService(endpointInterface = "net.softforlife.klich.common.ws.KlichWSLocal")
@Transactional
@XmlSeeAlso({ResultWSMessage.class, ArrayList.class, HashMap.class})
public class KlichWSLocalImpl implements KlichWSLocal {
	/** El logger. */
	final Logger logger = Logger.getLogger(this.getClass());
	
	private WSDataService wsDataService;
	
	private WSEmailSender wSEmailSender = null;
	
	private NotificationService notificationService = null;
	
	
	@Override
	public ResultWSMessage sendAction(Long deviceId, String license, int action, int idxActive) {

        logger.info("\n ** WEBSERVICE - SendAction ** ");
        ResultWSMessage result = null;
        
        if (action == ACTION_TYPE.LOAD.getId()) {
            //result = wsDataService.loadTablet(tablet, license);
        } else if (action == ACTION_TYPE.UNLOAD.getId()) {
            result = wsDataService.unloadDevice(deviceId);
        } else if (action == ACTION_TYPE.REFRESH_USER.getId()) {
            //result = wsDataService.loadCapiUser(tablet, license);
        } else if (action == ACTION_TYPE.PING.getId()) {
            result = wsDataService.ping();
        } else {
            result = new ResultWSMessage();
            result.setType(ResultWSMessage.TYPE.ERROR.ordinal());
            result.setMessage("Accion no reconocida por el webservice");
        }

        return result;
		//return null;
	}

	@Override
	public ResultWSMessage unloadGeopoint(Geopoint g) {
        logger.info("\n ** WEBSERVICE - unloadGeopoint ** ");
        ResultWSMessage result = null;
        
        result = wsDataService.unloadGeopoint(g);
        
		return result;
	}
	

	@Override
	public ResultWSMessage startNewTrack(String s) {
		
		logger.info("Vamos a iniciar un nuevo Track: " + s);
		
		Track track = Track.parse(s);
		wsDataService.saveNewTrack(track);
		
		ResultWSMessage result = new ResultWSMessage();
		result.setMessage(track.getTrackId().toString());

		return result;
	}
	
	@Override
	public ResultWSMessage startNewGeopoint(String s) {
		
		logger.info("Vamos a iniciar un nuevo Geopoint: " + s);
		
		Geopoint geopoint = Geopoint.parse(s);
		geopoint.setGeopointId(null);
		
		ResultWSMessage result = new ResultWSMessage();
		
		if(geopoint.getTrackId() != null) {
			wsDataService.saveNewGeopoint(geopoint);
			result.setMessage(geopoint.getGeopointId().toString());
		} else {
			result.setMessage("FAIL");
		}
	
		return result;
	}
	
	@Override
	public ResultWSMessage sendNormalGeopoint(String s) {
		logger.info("Vamos a insertar un Geopoint: " + s);
		
		ResultWSMessage result = new ResultWSMessage();
		
		Geopoint geopoint = Geopoint.parse(s);
		geopoint.setGeopointId(null);
		
		if(geopoint.getTrackId() != null) {
			wsDataService.saveNewGeopoint(geopoint);
			result.setMessage(geopoint.getGeopointId().toString());
		} else {
			result.setMessage("FAIL");
		}
		
		return result;
	}
	
	@Override
	public ResultWSMessage replaceLastGeopoint(String s) {
		logger.info("Vamos a reemplazar el ultimo Geopoint: " + s);
		
		ResultWSMessage result = new ResultWSMessage();	
		
		Geopoint geopoint = Geopoint.parse(s);
		
		if(geopoint.getTrackId() != null) {
			Long id = wsDataService.replaceLastGeopoint(geopoint);
			if(id != null) {
				result.setMessage(id.toString());
			} else {
				result.setMessage("FAIL");
			}
		} else {
			result.setMessage("FAIL");
		}
		
		return result;
	}
	
	@Override
	public ResultWSMessage replaceGeopoint(String s) {
		logger.info("Vamos a reemplazar un Geopoint: " + s);
		
		ResultWSMessage result = new ResultWSMessage();	
		
		Geopoint geopoint = Geopoint.parse(s);
		
		if(geopoint.getTrackId() != null) {
			Long id = wsDataService.replaceGeopoint(geopoint);
			if(id != null) {
				result.setMessage(id.toString());
			} else {
				result.setMessage("FAIL");
			}
		} else {
			result.setMessage("FAIL");
		}
		
		return result;
	}
	

	@Override
	public ResultWSMessage login(String s) {
		logger.info("Vamos a verificar si el login es correcto: " + s);
		ResultWSMessage result = new ResultWSMessage();	
		Tuser user = Tuser.parse(s);
		
		user = wsDataService.login(user);
		
		if (user != null) {
			result.setMessage(user.toString());
		} else {
			result.setMessage("FAIL");
		}
		
		return result;
	}


	@Override
	public ResultWSMessage register(String s) {
		logger.info("Vamos a registrar un nuevo usuario: " + s);
		ResultWSMessage result = new ResultWSMessage();	
		
		Tuser user = Tuser.parse(s);
		
		user = wsDataService.register(user);
		
		if (user != null) {
			result.setMessage(user.toString());
			// TODO Mandar email al usuario para que confirme su registro
		} else {
			result.setMessage("FAIL");
		}
		
		return result;
	}
	
	@Override
	public ResultWSMessage resetPassword(String s) {
		logger.info("Vamos a resetear la clave de un usuario: " + s);
		ResultWSMessage result = new ResultWSMessage();	
		
		Tuser user = Tuser.parse(s);
		
		user = wsDataService.remember(user);
		
		if (user != null) {
			
			String code = notificationService.generateCode();
			
			String [] to = { user.getEmail() };
			//boolean ret = wSEmailSender.send(to, "Correo de prueba", "estamos haciendo una pruebecilla mu chula en el remember");
			boolean ret = wSEmailSender.sendResetPasswordEmail(to, user.getUserId().getUserId().toString(), code);
			if (ret) {
				Notification notification = new Notification();
				// TODO meter notificacion en base de datos
				notification.setNotificationType(NOTIFICATION_TYPE.RESET_PASS.getObject());
				notification.setReceiver(user);
				notification.setActive(true);
				notification.setAttempts(0);
				Date now = new Date();
				notification.setSendDate(now);
				notification.setCode(code);
				notificationService.persist(notification);
				result.setMessage(user.toString());
			}
			
			// error = -2 indica que el correo no pudo ser enviado
			result.setMessage("-2");
		} else {
			// error = -1 indica que el usuario no existe
			result.setMessage("-1");
		}
		
		return result;
	}	

	/**
	 * @param wsDataService the wsDataService to set
	 */
	public void setWsDataService(WSDataService wsDataService) {
		this.wsDataService = wsDataService;
	}


	/**
	 * @return the wsDataService
	 */
	public WSDataService getWsDataService() {
		return wsDataService;
	}

	public WSEmailSender getwSEmailSender() {
		return wSEmailSender;
	}

	public void setwSEmailSender(WSEmailSender wSEmailSender) {
		this.wSEmailSender = wSEmailSender;
	}

	public NotificationService getNotificationService() {
		return notificationService;
	}

	public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}
}
