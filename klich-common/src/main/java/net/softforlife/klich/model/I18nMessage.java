package net.softforlife.klich.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;


/**
*
* @author rtovar
*/
@Entity
@Table(name = "i18n_message", uniqueConstraints = {
@UniqueConstraint(columnNames = {"I18N_GROUP_ID", "LKEY", "LANGUAGE_CODE"})})
@NamedQueries({
@NamedQuery(name = "I18nMessage.findAll", query = "SELECT i FROM I18nMessage i")})
public class I18nMessage implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "I18N_MESSAGE_ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer i18nMessageId;
    
    @Basic(optional = false)
    @Column(name = "LKEY", nullable = false, length = 50)
    private String lkey;    
    
    @Column(name = "VALUE", nullable = false, length = 1024)
    private String value;
    
    @Column(name = "I18N_GROUP_ID")    
    private Integer i18nGroupId;
    
    @Column(name = "LANGUAGE_CODE")    
    private String languageCode;
    

	/**
	 * @param i18nMessageId the i18nMessageId to set
	 */
	public void setI18nMessageId(Integer i18nMessageId) {
		this.i18nMessageId = i18nMessageId;
	}

	/**
	 * @return the i18nMessageId
	 */
	public Integer getI18nMessageId() {
		return i18nMessageId;
	}
	
	
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof I18nMessage)) {
            return false;
        }
        I18nMessage other = (I18nMessage) object;
        if ((this.i18nMessageId == null && other.i18nMessageId != null) || (this.i18nMessageId != null && !this.i18nMessageId.equals(other.i18nMessageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.ine.sigue.model.I18nMessage[i18nMessageId=" + i18nMessageId + "]";
    }

	/**
	 * @param lkey the lkey to set
	 */
	public void setLkey(String lkey) {
		this.lkey = lkey;
	}

	/**
	 * @return the lkey
	 */
	public String getLkey() {
		return lkey;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
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

}
