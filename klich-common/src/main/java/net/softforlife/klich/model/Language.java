package net.softforlife.klich.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
*
* @author rtovar
*/
@Entity
@Table(name = "LANGUAGE")
@NamedQueries({
@NamedQuery(name = "Language.findAll", query = "SELECT l FROM Language l")})
public class Language implements Serializable {
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
    @Id
    @Basic(optional = false)
    @Column(name = "LANGUAGE_CODE", nullable = false, length = 8)
    private String languageCode;

    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    
    @OneToMany(mappedBy = "languageCode")
    private Collection<I18nMessage> i18nMessageCollection;

    public Language() {
    }
    
    
	/**
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return languageCode;
	}


	/**
	 * @param i18nMessageCollection the i18nMessageCollection to set
	 */
	public void setI18nMessageCollection(Collection<I18nMessage> i18nMessageCollection) {
		this.i18nMessageCollection = i18nMessageCollection;
	}


	/**
	 * @return the i18nMessageCollection
	 */
	public Collection<I18nMessage> getI18nMessageCollection() {
		return i18nMessageCollection;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}


	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (languageCode != null ? languageCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Language)) {
            return false;
        }
        Language other = (Language) object;
        if ((this.languageCode == null && other.languageCode != null) || (this.languageCode != null && !this.languageCode.equals(other.languageCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.ine.sigue.model.Language[languageCode=" + languageCode + "]";
    }
	
}
