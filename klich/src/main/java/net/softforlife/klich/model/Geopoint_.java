package net.softforlife.klich.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-08-30T18:18:02.623+0200")
@StaticMetamodel(Geopoint.class)
public class Geopoint_ {
	public static volatile SingularAttribute<Geopoint, Long> geopointId;
	public static volatile SingularAttribute<Geopoint, Date> date;
	public static volatile SingularAttribute<Geopoint, Double> longitude;
	public static volatile SingularAttribute<Geopoint, Double> latitude;
	public static volatile SingularAttribute<Geopoint, Status> typeGeopoint;
	public static volatile SingularAttribute<Geopoint, Float> accuracy;
	public static volatile SingularAttribute<Geopoint, Track> trackId;
}
