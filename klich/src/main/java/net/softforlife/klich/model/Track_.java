package net.softforlife.klich.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-08-30T18:18:02.816+0200")
@StaticMetamodel(Track.class)
public class Track_ {
	public static volatile SingularAttribute<Track, Long> trackId;
	public static volatile SingularAttribute<Track, Date> date;
	public static volatile CollectionAttribute<Track, Geopoint> geopointCollection;
	public static volatile SingularAttribute<Track, Device> deviceId;
}
