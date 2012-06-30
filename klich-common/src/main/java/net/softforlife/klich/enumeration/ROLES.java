package net.softforlife.klich.enumeration;

import java.util.Arrays;
import java.util.List;

import net.softforlife.klich.model.Trole;
import net.softforlife.klich.model.Tuser;
import net.softforlife.klich.model.TuserTrole;

/**
 * The Enum ROLES.
 * 
 * @author jmhidalgo
 */
public enum ROLES implements GenericEnum<Trole> {

    /** El ROL e_ admin. */
    ROLE_ADMIN(1, "ROLE_ADMIN"),
    
    /** El ROL e_ user. */
    ROLE_USER(2, "ROLE_USER"),
    
    /** El ROL e_ superviso r_ sscc. */
    ROLE_SUPERVISOR_SSCC(3, "ROLE_SUPERVISOR_SSCC"),
    
    /** El ROL e_ supervisor. */
    ROLE_SUPERVISOR(4, "ROLE_SUPERVISOR"),
    
    /** El ROL e_ inspector. */
    ROLE_INSPECTOR(5, "ROLE_INSPECTOR"),
    
    /** El ROL e_ entrevistador. */
    ROLE_ENTREVISTADOR(6, "ROLE_ENTREVISTADOR"),
    
    /** El ROL e_ cati. */
    ROLE_CATI(7, "ROLE_CATI"),
    
    /** El ROL e_ capi. */
    ROLE_CAPI(8, "ROLE_CAPI");
    
    
    /** El id. */
    private int id;
    
    /** El code. */
    private String code;

    /**
     * Constructor de la clase ROLES.
     * 
     * @param id the id
     * @param lkey the lkey
     */
    private ROLES(int id, String lkey) {
        this.id = id;
        this.code = lkey;
    }

    /* (non-Javadoc)
     * @see es.ine.tich.enumeration.GenericEnum#getId()
     */
    public int getId() {
        return id;
    }

    /* (non-Javadoc)
     * @see es.ine.tich.enumeration.GenericEnum#getCode()
     */
    public String getCode() {
        return code;
    }

    /* (non-Javadoc)
     * @see es.ine.tich.enumeration.GenericEnum#getObject()
     */
    public Trole getObject() {
        return new Trole(id,code);
    }

    /**
     * Devuelve la lista de tipos de usuarios.
     * 
     * @return Lista de tipo de usuario
     */
    public static List<Trole> getUserType (){
         Trole[] USER_TYPES = {
            /*ROLE_ADMIN.getObject(),*/
            ROLE_SUPERVISOR_SSCC.getObject(),
            ROLE_INSPECTOR.getObject(),
            ROLE_SUPERVISOR.getObject(),
            ROLE_ENTREVISTADOR.getObject()
        };

        return Arrays.asList(USER_TYPES);
    }

    /**
     * Devuelve la lista de roles que indican las
     * aplicaciones disponibles.
     * 
     * @return lista de roles
     */
    public static List<Trole> getApplications (){
        Trole[] APPLICATIONS = {
            ROLE_CAPI.getObject(),
            ROLE_CATI.getObject()
        };

        return Arrays.asList(APPLICATIONS);
    }
    
    /**
     * Comprueba que sSCC user es TRUE.
     * 
     * @param tuser the tuser
     * 
     * @return true si sSCC user es verdadero.
     */
    synchronized public static boolean isSSCCUser(Tuser tuser){
    	for(TuserTrole tur : tuser.getTuserTroleCollection()){
    		if(ROLE_SUPERVISOR_SSCC.getObject().equals(tur.getRoleId())){
    			return true;
    		}
    	}
    	return false; 
    }
    
    /**
     * Comprueba que cATI es TRUE.
     * 
     * @param tuser the tuser
     * 
     * @return true si cATI es verdadero.
     */
    synchronized public static boolean isCATI (Tuser tuser){
    	for(TuserTrole tur : tuser.getTuserTroleCollection()){
    		if(ROLE_CATI.getObject().equals(tur.getRoleId())){
    			return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Comprueba que cAPI es TRUE.
     * 
     * @param tuser the tuser
     * 
     * @return true si cAPI es verdadero.
     */
    synchronized public static boolean isCAPI (Tuser tuser){
    	for(TuserTrole tur : tuser.getTuserTroleCollection()){
    		if(ROLE_CAPI.getObject().equals(tur.getRoleId())){
    			return true;
    		}
    	}
    	return false;
    }


    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return code;
    }
};
    

