package net.softforlife.klich.model;

import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-08-30T18:18:02.766+0200")
@StaticMetamodel(StatusType.class)
public class StatusType_ {
	public static volatile SingularAttribute<StatusType, BigDecimal> statusTypeId;
	public static volatile SingularAttribute<StatusType, String> shortDescription;
	public static volatile SingularAttribute<StatusType, String> description;
	public static volatile CollectionAttribute<StatusType, StatusTypeAssign> statusTypeAssignCollection;
}
