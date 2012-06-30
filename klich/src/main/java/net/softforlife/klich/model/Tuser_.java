package net.softforlife.klich.model;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2012-02-25T13:25:03.930+0100")
@StaticMetamodel(Tuser.class)
public class Tuser_ {
	public static volatile SingularAttribute<Tuser, TuserPK> userId;
	public static volatile SingularAttribute<Tuser, String> password;
	public static volatile SingularAttribute<Tuser, String> recovery;
	public static volatile SingularAttribute<Tuser, Boolean> active;
	public static volatile SingularAttribute<Tuser, String> email;
	public static volatile SingularAttribute<Tuser, String> phone;
	public static volatile SingularAttribute<Tuser, String> name;
	public static volatile SingularAttribute<Tuser, String> surname;
	public static volatile SingularAttribute<Tuser, Integer> idx;
	public static volatile SingularAttribute<Tuser, Date> creationDate;
	public static volatile SingularAttribute<Tuser, Date> birthDate;
	public static volatile SingularAttribute<Tuser, Integer> sex;
	public static volatile CollectionAttribute<Tuser, TuserTrole> tuserTroleCollection;
	public static volatile CollectionAttribute<Tuser, Device> deviceCollection;
	public static volatile CollectionAttribute<Tuser, Notification> notificationCollection;
}
