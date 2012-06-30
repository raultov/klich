package net.softforlife.klich.ws;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.softforlife.klich.common.ws.ACTION_TYPE;
import net.softforlife.klich.common.ws.ResultWSMessage;
import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.ws.actions.UnloadAction;

import org.springframework.transaction.annotation.Transactional;

/**
*
* @author rtovar
*/
@WebService
@Transactional
@XmlSeeAlso({Device.class, Track.class, Geopoint.class, ArrayList.class, Tuser.class, 
			 UnloadAction.class, HashMap.class, ResultWSMessage.class})
public interface KlichWS {

   ResultWSMessage sendAction(ACTION_TYPE action);

   Object validateDevice(Long deviceId);

   ResultWSMessage loadDevice(Long deviceId);

   ResultWSMessage unloadDevice(Long deviceId, Device d);
   
   ResultWSMessage unloadTrack(Long trackId, Track t);
   
   ResultWSMessage unloadGeopoint(Long geopointId, Geopoint g);

   ResultWSMessage ping ();
   
};


