package net.softforlife.klich.common.serialization;

import net.softforlife.klich.model.Geopoint;
import org.apache.commons.beanutils.PropertyUtilsBean;
import java.beans.PropertyDescriptor;

import org.apache.log4j.Logger;

public class Serializate {

	/** El logger. */
	final static Logger logger = Logger.getLogger(Serializate.class);
	
	public static String serializateGeopoint(Geopoint geopoint) {
		StringBuffer sb = new StringBuffer();
		Long geopointId = null;
		
		try {
			//Map properties = BeanUtils.describe(geopoint);
			PropertyUtilsBean pub = new PropertyUtilsBean();
			
			PropertyDescriptor[] vpd = pub.getPropertyDescriptors(Geopoint.class);
			
			
			for(PropertyDescriptor pd : vpd) {
				System.out.println("Property name: " + pd.getName());
				
				try {
					System.out.println("Property value: " + pub.getProperty(geopoint, pd.getName()));
				} catch (Exception e) {
					String msg = "La propiedad: " + pd.getName() + " tiene valor null";
					logger.info(msg);
				}
			}
			
			// TODO Meter en un String los nombres de las propiedades seguidas de sus valores y delitimitados 
			//por una cifra que indica la longitud del valor que vendrá a continuación
			
			//BeanUtils.copyProperty(geopoint, "", geopointId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		sb.append(String.valueOf(geopointId));
		
		return null;
	}
}
