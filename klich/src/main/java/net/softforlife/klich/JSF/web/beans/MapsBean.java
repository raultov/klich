package net.softforlife.klich.JSF.web.beans;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.softforlife.klich.service.DeviceService;
import net.softforlife.klich.service.GeopointService;
import net.softforlife.klich.service.TrackService;
import net.softforlife.klich.service.UserService;
import net.softforlife.klich.JSF.web.util.JsfUtil;
import net.softforlife.klich.JSF.web.util.MessagesUtil;
import net.softforlife.klich.model.Device;
import net.softforlife.klich.model.Geopoint;
import net.softforlife.klich.model.Track;
import net.softforlife.klich.model.Tuser;

import org.apache.log4j.Logger;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.StateChangeEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

public class MapsBean {
	private final static String DATA_TABLE_ROW_TRACK_ID = "formMap:trackTable_row_";
	private final static String DATA_TABLE_ROW_DEVICE_ID = "sidebarForm2:deviceTable_row_";
	private GeopointService geopointService;
	private TrackService trackService;
	
	private MapModel model = new DefaultMapModel();
	private Geopoint geopointSelected = new Geopoint();
	private Geopoint geopointBeforeSelected = new Geopoint();
	private Device deviceSelected = null;
	private Track trackSelected = null;
	private UserService userService = null;
	private DeviceService deviceService = null;
	
	private Collection<Geopoint> geopoints = null;
	private List<Track> tracks;
	private List<Device> devices;
	
	private Integer zoomLevel = 15;
	
	private Locale locale = new Locale("es", "ES");
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	
	public MapModel getModel() {
		
		for(Geopoint geopoint : getListGeopoints()) {
			Marker marker = new Marker(new LatLng(geopoint.getLatitude(), geopoint.getLongitude()), geopoint.getGeopointId().toString());
			marker.setId(geopoint.getGeopointId().toString());
			model.addOverlay(marker);
		}

		return model;
	}
	
    public void onMarkerSelect(OverlaySelectEvent event) {  
        Marker marker = (Marker) event.getOverlay();  
        
        if ((marker != null) && (geopoints != null)) {
        	
        	for(Geopoint geopoint : geopoints) {
        		if((geopoint.getGeopointId() != null) && 
        				(marker.getTitle() != null) &&
        				(geopoint.getGeopointId()== Long.valueOf(marker.getTitle()))) {
        			
        			if(geopointBeforeSelected.getGeopointId() == null) {
        				geopointBeforeSelected.setGeopointId(geopoint.getGeopointId());
        			} else {
        				geopointBeforeSelected.setGeopointId(geopointSelected.getGeopointId());
        			}
        			
        			geopointSelected = geopoint;
        		}
        	}
        }
    }  	
    
    public String getApproximateCenter() {
		if (devices == null) {
			Tuser userInSession = userService.getUserInSession();
			devices = deviceService.getDevicesByUser(userInSession);
		}
    	
    	
		if ((trackSelected == null)  && (devices != null) && (devices.size() > 0)) {
			tracks = new ArrayList<Track>(devices.get(0).getTrackCollection());
			trackSelected = tracks.get(0);
		}
		
    	LatLng center = trackService.getAverageCenter(trackSelected);
    	
    	if (center == null) {
    		return "40.429, -3.699";
    	}
    	
    	return center.getLat() + "," + center.getLng();
    }
    
    public void onStateChange(StateChangeEvent event) {
    	zoomLevel = event.getZoomLevel();
    }

	
	public Collection<Geopoint> getListGeopoints() {
		String deviceId = JsfUtil.getStringParam("deviceId");
		
		if (deviceId == null) {
			if (devices == null) {
				devices = deviceService.getDevicesByUser(userService.getUserInSession());
			
				if ((deviceSelected == null) && (devices != null) && (devices.size() > 0)) {
					deviceSelected = devices.get(0);
				
					tracks = new ArrayList<Track>(deviceSelected.getTrackCollection());
				
					trackSelected = null;
					
					if ((tracks != null) && (tracks.size() > 0)) {
						trackSelected = tracks.get(0);
					}
				} 
			}
		} else { // Significa que un nuevo dispositivo ha sido seleccionado.
			deviceSelected = deviceService.getDeviceById(Long.parseLong(deviceId));
			
			tracks = new ArrayList<Track>(deviceSelected.getTrackCollection());
			trackSelected = null;
			
			if ((tracks != null) && (tracks.size() > 0)) {
				trackSelected = tracks.get(0);
			}
		}
		
		if (trackSelected == null) {
			return new ArrayList<Geopoint>();
		}
		
		geopoints = trackSelected.getGeopointCollection();

		return geopoints;
	}
	
	public void onTrackSelect() {
		String trackId = JsfUtil.getStringParam("trackId");
		
		if (trackId != null) {
			model = new DefaultMapModel();
			trackSelected = trackService.getTrackById(Long.parseLong(trackId));
		
			geopointSelected = new Geopoint();
			geopointBeforeSelected = new Geopoint();		
		
			MessagesUtil.showInfoMessage("Operación realizada con éxito", "Mostrándose la nueva vista en el mapa");
			logger.info("Pista con id: " + trackSelected.getTrackId() + " ha sido cargada con éxito");
		} else {
			logger.error("La pista no pudo ser cargada");
		}
	}
	
	public void onDeviceSelect() {
		String deviceId = JsfUtil.getStringParam("deviceId");
		
		if (deviceId != null) {
			logger.info("deviceId: " + deviceId);
			
			deviceSelected = deviceService.getDeviceById(Long.parseLong(deviceId));
			model = new DefaultMapModel();
			geopointSelected = new Geopoint();
			geopointBeforeSelected = new Geopoint();
			tracks = new ArrayList<Track>(deviceSelected.getTrackCollection());
			
			MessagesUtil.showInfoMessage("Operación realizada con éxito", "Mostrándose información sobre el dispositvo");
			logger.info("Device con id: " + deviceSelected.getDeviceId() + " ha sido cargado con éxito");
			
			if ((tracks != null) && (tracks.size() > 0)) {
				trackSelected = tracks.get(0);
			} else {
				trackSelected = null;
			}
			
		} else {
			logger.error("El device no pudo ser cargado");
		}
	}

	
	public Integer getAccuracyGeopointSelectedInMeters() {
		if(geopointSelected.getAccuracy() != null) {
			Float f = new Float(geopointSelected.getAccuracy() * 1000.0f);
			return f.intValue();
		} else {
			return null;
		}
	}
	
	public String getDateGeopointSelected() {
		if(geopointSelected.getDate() != null) {
			Date date = geopointSelected.getDate();
			String pattern = "dd MMM yyyy";

			SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
						
			String dateS = sdf.format(date);
			
			return dateS;
		} else {
			return null;
		}
	}
	
	public String getTimeGeopointSelected() {
		if(geopointSelected.getDate() != null) {
			Date date = geopointSelected.getDate();
			String pattern = "HH:mm:ss";

			SimpleDateFormat sdf = new SimpleDateFormat(pattern, locale);
						
			String timeS = sdf.format(date);
			
			
			return timeS;
		} else {
			return null;
		}		
	}
	
	public List<Track> getTracks() {
		if (tracks == null) {
			if (deviceSelected == null) {
				if (devices == null) {
					devices = deviceService.getDevicesByUser(userService.getUserInSession());
				}
				
				if ((devices != null) && (devices.size() > 0)) {
					deviceSelected = devices.get(0);
					tracks = new ArrayList<Track>(deviceSelected.getTrackCollection());
				}
			}
		}
		
		if (tracks == null) {
			return new ArrayList<Track>();
		}
		
		return tracks;
	}

	public void setTracks(List<Track> tracks) {
		this.tracks = tracks;
	}
	
	public List<Device> getDevices() {
		if (devices == null) {
			devices = deviceService.getDevicesByUser(userService.getUserInSession());
		}
		
		if (devices == null) {
			return new ArrayList<Device>();
		}
		
		return devices;
	}
	
	public String getHtmlIdSelectedTrack() {
		int index = 0;
		
		if ((trackSelected != null) && (tracks != null)) {
			index = tracks.indexOf(trackSelected);
		}
		
		return DATA_TABLE_ROW_TRACK_ID + index;
	}
	
	public String getHtmlIdSelectedDevice() {
		int index = 0;
		
		if ((deviceSelected != null) && (devices != null)) {
			index = devices.indexOf(deviceSelected);
		}
		
		return DATA_TABLE_ROW_DEVICE_ID + index;
	}	
	
	public Geopoint getGeopointSelected() {
		return geopointSelected;
	}
	
	public void setGeopointSelected(Geopoint geopointSelected) {
		this.geopointSelected = geopointSelected;
	}

	public Geopoint getGeopointBeforeSelected() {
		return geopointBeforeSelected;
	}

	public void setGeopointBeforeSelected(Geopoint geopointBeforeSelected) {
		this.geopointBeforeSelected = geopointBeforeSelected;
	}

	public Integer getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(Integer zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DeviceService getDeviceService() {
		return deviceService;
	}

	public void setDeviceService(DeviceService deviceService) {
		this.deviceService = deviceService;
	}	
	

	public void setGeopointService(GeopointService geopointService) {
		if (geopointService == null) {
			logger.error("El geopointService nos está llegando a null");
		}
		
		this.geopointService = geopointService;
	}

	public GeopointService getGeopointService() {
		return geopointService;
	}

	/**
	 * @param trackService the trackService to set
	 */
	public void setTrackService(TrackService trackService) {
		this.trackService = trackService;
	}

	/**
	 * @return the trackService
	 */
	public TrackService getTrackService() {
		return trackService;
	}


	public void setModel(MapModel model) {
		this.model = model;
	}
};




