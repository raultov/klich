package net.softforlife.klich.enumeration;

import net.softforlife.klich.model.Status;

/**
 * The Enum STATUS.
 * 
 * @author rtovar
 */
public enum STATUS implements GenericEnum<Status> {

    /** El ESTAD o_ null. */
    ESTADO_NULL(5, "INIT",true,false);
    
    /** El id. */
    private int id;
    
    /** El code. */
    private String code;
    
    /** El internal. */
    private boolean internal;
    
    /** El final state. */
    private boolean finalState;

    /**
     * Constructor de la clase STATUS.
     * 
     * @param id the id
     * @param lkey the lkey
     * @param internal the internal
     * @param finalState the final state
     */
    private STATUS(int id, String lkey,boolean internal, boolean finalState) {
        this.id = id;
        this.code = lkey;
        this.internal = internal;
        this.finalState = finalState;
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
    public Status getObject() {
        return new Status(id, code,internal,finalState);
    }

    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return code;
    }
};