package net.softforlife.klich.ws.actions;

import java.util.HashMap;
import java.util.Map;

import net.softforlife.klich.common.ws.ACTION_TYPE;

/**
 *
 * @author rtovar
 */
public class UnloadAction implements Action{
    private ACTION_TYPE type = ACTION_TYPE.UNLOAD;
    private Map<String, String> params;

    public UnloadAction (){
        
    }

    public UnloadAction (String device){
        params = new HashMap<String, String>();

        params.put("DEVICE", device);
    }


    @Override
    public Map<String, String> getParams() {
        return params;
    }

    @Override
    public ACTION_TYPE getType() {
        return type;
    }

}

