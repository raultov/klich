package net.softforlife.klich.service.msg;

/**
 * Clase que permite manejar los mensajes de la aplicación con desino usuario.
 * 
 * @author rtovar
 * 
 */
public class Message {

	private int severity;
	private String msg;
	private String icon;
	private String[] args;

	/**
	 * Constructor con argumentos para el mensaje
	 * 
	 * @param severity
	 * @param msg
	 * @param args
	 */

	public Message(int severity, String msg, String[] args) {
		super();
		this.severity = severity;
		this.msg = msg;
		this.args = args;
	}

	/**
	 * Constrcutor sin argumentos para el mensaje
	 * 
	 * @param severity
	 * @param msg
	 */

	public Message(int severity, String msg) {
		super();
		this.severity = severity;
		this.msg = msg;
		this.args = null;
	}

	/**
	 * Getter
	 * @return
	 */
	
	public int getSeverity() {
		return severity;
	}
	
	/**
	 * Setter
	 * @param severity
	 */

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	/**
	 * Getter
	 * @return
	 */

	public String getMsg() {
		return msg;
	}
	
	/**
	 * Setter
	 * @param msg
	 */

	public void setMsg(String msg) {
		this.msg = msg;
	}

	/**
	 * Getter
	 * @return
	 */

	public String[] getArgs() {
		return args;
	}
	
	/**
	 * Setter
	 * @param args
	 */

	public void setArgs(String[] args) {
		this.args = args;
	}

	/**
	 * Getter
	 * @return
	 */

	public String getIcon() {
		icon = "";
		switch (getSeverity()) {
		case ViewMessageService.WARN:
			icon = ViewMessageService.WARN_ICO;
			break;
		case ViewMessageService.INFO:
			icon = ViewMessageService.INFO_ICO;
			break;
		case ViewMessageService.ERROR:
			icon = ViewMessageService.ERROR_ICO;
			break;
		case ViewMessageService.QUESTION:
			icon = ViewMessageService.QUESTION_ICO;
			break;
		default:
			break;
		}
		return icon;
	}
	
	/**
	 * Setter
	 * @param icon
	 */
	
	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if (obj instanceof Message) {
			if (((Message) obj).getMsg().equals(msg)) {
				result = true;
			}
		}

		return result;
	}
}