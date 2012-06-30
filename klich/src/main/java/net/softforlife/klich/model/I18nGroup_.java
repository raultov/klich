package net.softforlife.klich.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-08-30T18:18:02.652+0200")
@StaticMetamodel(I18nGroup.class)
public class I18nGroup_ {
	public static volatile SingularAttribute<I18nGroup, Integer> i18nGroupId;
	public static volatile SingularAttribute<I18nGroup, String> name;
	public static volatile CollectionAttribute<I18nGroup, I18nMessage> i18nMessageCollection;
}
