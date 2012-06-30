package net.softforlife.klich.common.ws;

import java.util.ArrayList;
import java.util.HashMap;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;

import net.softforlife.klich.model.Geopoint;

import org.springframework.transaction.annotation.Transactional;

/**
*
* @author rtovar
*/
@WebService
@Transactional
@XmlSeeAlso({ResultWSMessage.class, ArrayList.class, HashMap.class, Geopoint.class, String.class})
public interface KlichWSLocal {

	ResultWSMessage sendAction(Long deviceId, String license, int action, int idxActive);
	
	ResultWSMessage unloadGeopoint(Geopoint g);
	
	ResultWSMessage startNewTrack(String s);

	ResultWSMessage startNewGeopoint(String s);
	
	ResultWSMessage sendNormalGeopoint(String s);
	
	ResultWSMessage replaceLastGeopoint(String s);
	
	ResultWSMessage replaceGeopoint(String s);
	
	ResultWSMessage login(String s);
	
	ResultWSMessage register(String s);

	ResultWSMessage resetPassword(String s);
}
