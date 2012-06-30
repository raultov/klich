package net.softforlife.klich.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "I18N_GROUP")
@NamedQueries({
@NamedQuery(name = "I18nGroup.findAll", query = "SELECT i FROM I18nGroup i")})
public class I18nGroup implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "I18N_GROUP_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer i18nGroupId;
    
    @Basic(optional = false)
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;
    
    @OneToMany(mappedBy = "i18nGroupId")
    private Collection<I18nMessage> i18nMessageCollection;
    
    public I18nGroup() {
    }
    
    

	/**
	 * @param i18nGroupId the i18nGroupId to set
	 */
	public void setI18nGroupId(Integer i18nGroupId) {
		this.i18nGroupId = i18nGroupId;
	}

	/**
	 * @return the i18nGroupId
	 */
	public Integer getI18nGroupId() {
		return i18nGroupId;
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
	
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (i18nGroupId != null ? i18nGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof I18nGroup)) {
            return false;
        }
        I18nGroup other = (I18nGroup) object;
        if ((this.i18nGroupId == null && other.i18nGroupId != null) || (this.i18nGroupId != null && !this.i18nGroupId.equals(other.i18nGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.ine.sigue.model.I18nGroup[i18nGroupId=" + i18nGroupId + "]";
    }	

}
