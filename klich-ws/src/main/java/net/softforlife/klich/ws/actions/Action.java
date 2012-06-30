package net.softforlife.klich.ws.actions;

import java.util.Map;

import net.softforlife.klich.common.ws.ACTION_TYPE;

/**
*
* @author rtovar
*/
public interface Action {

   public ACTION_TYPE getType();

   @SuppressWarnings("rawtypes")
public Map getParams();   
}
