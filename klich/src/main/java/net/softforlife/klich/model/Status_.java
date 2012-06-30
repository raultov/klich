package net.softforlife.klich.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-08-30T18:18:02.739+0200")
@StaticMetamodel(Status.class)
public class Status_ {
	public static volatile SingularAttribute<Status, Integer> statusId;
	public static volatile SingularAttribute<Status, String> shortDescription;
	public static volatile SingularAttribute<Status, String> description;
	public static volatile SingularAttribute<Status, Boolean> internal;
	public static volatile SingularAttribute<Status, Boolean> finalState;
	public static volatile CollectionAttribute<Status, StatusTypeAssign> statusTypeAssignCollection;
}
