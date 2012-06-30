package net.softforlife.klich.JSF.ws;

import java.io.IOException;
import java.util.Date;

import javax.xml.ws.soap.SOAPFaultException;

import net.softforlife.klich.JSF.web.util.URLUtils;
import net.softforlife.klich.common.ws.ACTION_TYPE;
import net.softforlife.klich.common.ws.KlichWSLocal;
import net.softforlife.klich.enumeration.CLAVE_GEOPOINT;
import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.service.TrackService;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.apache.log4j.Logger;

public class ClientWS {
	TrackService trackService = null;
	
	/** El logger. */
	private Logger logger = Logger.getLogger(ClientWS.class);
	
	/** El ws client. */
	private KlichWSLocal clientWS;


	/**
	 * Este método se ejecuta en el device.
	 * 
	 * @return
	 * @throws IOException
	 */

	/*
	public String descargarDeviceOk() throws IOException {
		long t0 = System.currentTimeMillis();
		// servidor
		
		List<Track> tracks = trackService.getListAllTracks();
		if(tracks.isEmpty()){

			return "failed";
		}
		
		try {
			KlichWSLocal klichLocal = getClientWS();
			
			if (klichLocal == null) {
				return "failed";
			}
			
			ResultWSMessage result = klichLocal.sendAction(tabletName, license, ACTION_TYPE.UNLOAD.getId(), idxActive);
			
		} catch (SOAPFaultException e) {
			//JsfUtil.addMessage(ViewMessageService.ERROR,"clientWS.connection.error", 	new String[] { e.getMessage() });
			return "failed";
		}
		
		return "";
	}
	*/
	
	public String getPruebaWS() {
		String ret = "success";
		/*
		try {
			KlichWSLocal klichLocal = getClientWS();
			
			Geopoint g = new Geopoint();
			g.setAccuracy(1);
			g.setDate(new Date());
			g.setLatitude(-5.5);
			g.setLongitude(5.5);
			g.setTrackId(null);
			g.setTypeGeopoint(CLAVE_GEOPOINT.GPS.getObject());
			
			klichLocal.unloadGeopoint(g);
			
			
		} catch(SOAPFaultException e) {
			ret = "failed";
		}
		*/
		
		return ret;
	}
	
	
	
	/**
	 * @return the clientWS
	 */
	public KlichWSLocal getClientWS() {

		ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
		factory.setServiceClass(KlichWSLocal.class);

		String base = URLUtils.getServerURL(null);

		logger.info("\n  LOCAL SERVER URL  -> " + base);

		String localWSUrl = base + "klich-ws/ws/klichWS";
		logger.info("\n  LOCAL WS URL  -> " + localWSUrl);

		factory.setAddress(localWSUrl);
		clientWS = (KlichWSLocal) factory.create();

		final Client cl = ClientProxy.getClient(clientWS);

		HTTPConduit http = (HTTPConduit) cl.getConduit();

		HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
		httpClientPolicy.setConnectionTimeout(500000);
		httpClientPolicy.setReceiveTimeout(500000);
		http.setClient(httpClientPolicy);

		try {
			logger.info(clientWS.sendAction(null, null, ACTION_TYPE.PING
					.getId(), 0));
		} catch (Exception ex) {
			logger.error("WebService no disponible", ex);
			clientWS = null;
		}

		return clientWS;

	}
	

	/**
	 * @param clientWS the clientWS to set
	 */
	public void setClientWS(KlichWSLocal clientWS) {
		this.clientWS = clientWS;
	}
	

	/**
	 * @return the trackService
	 */
	public TrackService getTrackService() {
		return trackService;
	}

	/**
	 * @param trackService the trackService to set
	 */
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}
}
