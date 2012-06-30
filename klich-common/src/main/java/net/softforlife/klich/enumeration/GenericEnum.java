package net.softforlife.klich.enumeration;

/**
 * The Interface GenericEnum.
 * 
 * @author jmhidalgo
 */
public interface GenericEnum <T>{

    /**
     * Devuelve el valor del campo Code.
     * 
     * @return Devuelve Code
     */
    String getCode ();

    /**
     * Devuelve el valor del campo Id.
     * 
     * @return Devuelve Id
     */
    int getId();

    /**
     * Devuelve el valor del campo Object.
     * 
     * @return Devuelve Object
     */
    T getObject();

}