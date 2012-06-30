package net.softforlife.klich.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="Dali", date="2011-08-30T18:18:02.712+0200")
@StaticMetamodel(Language.class)
public class Language_ {
	public static volatile SingularAttribute<Language, String> languageCode;
	public static volatile SingularAttribute<Language, String> name;
	public static volatile CollectionAttribute<Language, I18nMessage> i18nMessageCollection;
}
