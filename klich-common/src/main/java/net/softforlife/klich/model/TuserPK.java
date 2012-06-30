package net.softforlife.klich.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class TuserPK implements Serializable {
    //default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;

	@Column(name = "USER_ID", nullable = false)
	private Integer userId;

	@Column(name = "LOGIN", nullable = false)
	private String login;
	
    public int hashCode() {
        return (int) (userId);
    }

    public boolean equals(Object obj) {
        if(obj == this) return true;
        if(!(obj instanceof TuserPK)) return false;
        TuserPK pk = (TuserPK) obj;
        return pk.userId == userId && pk.login.equals(login);
    }		

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

} 