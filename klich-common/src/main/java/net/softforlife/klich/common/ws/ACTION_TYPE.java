package net.softforlife.klich.common.ws;

/**
 * Tipos de acciones implementadas por el webservices
 *
 * @author rtovar
 */
public enum ACTION_TYPE implements GenericEnum<String> {

    VALIDATE("VALIDATE", 1),
    LOAD("LOAD", 2),
    UNLOAD("UNLOAD", 3),
    REFRESH_USER ("REFRESH_USER",4),
    PING ("PING",5);

    private ACTION_TYPE(String code, int id) {
        this.id = id;
        this.code = code;
    }
    
    private int id;
    private String code;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getObject() {
        return " ACTION WS: " + code + " - "+ id;
    }
};
