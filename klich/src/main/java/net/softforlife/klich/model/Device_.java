package net.softforlife.klich.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-08-30T18:18:02.546+0200")
@StaticMetamodel(Device.class)
public class Device_ {
	public static volatile SingularAttribute<Device, Long> deviceId;
	public static volatile SingularAttribute<Device, String> model;
	public static volatile SingularAttribute<Device, String> brand;
	public static volatile SingularAttribute<Device, String> type;
	public static volatile CollectionAttribute<Device, Track> trackCollection;
	public static volatile SingularAttribute<Device, Tuser> userId;
}
