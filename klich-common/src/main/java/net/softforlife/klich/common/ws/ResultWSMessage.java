package net.softforlife.klich.common.ws;


/**
 *
 * @author rtovar
 */
public class ResultWSMessage {

    private int type;
    private String message;
    private String exception;
    private Object data;
    
    /**
     * @return the data
     */
    public Object getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

    public enum TYPE {
        OK, WARN, ERROR, NOACTIVE, NOMYIDX
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the exception
     */
    public String getException() {
        return exception;
    }

    /**
     * @param exception the exception to set
     */
    public void setException(String exception) {
        this.exception = exception;
    }

    public boolean isOK (){
        return getType() == ResultWSMessage.TYPE.OK.ordinal();
    }
    
    public boolean isNoActive (){
        return getType() == ResultWSMessage.TYPE.NOACTIVE.ordinal();
    }
    
    public boolean isNoMyIdx (){
        return getType() == ResultWSMessage.TYPE.NOMYIDX.ordinal();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();

        s.append("\n RESULT WS MSG");
        s.append("\n type=" + getType());
        s.append("\n msg=" + getMessage());
        if (getException()!= null){
            s.append("\n ex=" + getException());
        }
        if (getData() != null){
            s.append("\n data= "  + getData());
        }

        return s.toString();
    }


}
