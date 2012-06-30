package net.softforlife.klich.service.msg;

import java.util.List;

/**
 * Servcio para la gestión de los mensajes que se mostrarán al usuario mediatne
 * la interfaz
 * 
 * @author rtovar
 * 
 */

public interface ViewMessageService {

	// tipos de mensaje
	public static int INFO = 0;
	public static int WARN = 1;
	public static int ERROR = 2;
	public static int QUESTION = 3;

	// esta propiedad nos permite añadir mensajes de sistema
	public static String SYSTEM_MSG = "sys.msg";

	public static final String INFO_ICO = "info.png";
	public static final String WARN_ICO = "warn.png";
	public static final String ERROR_ICO = "error.png";
	public static final String QUESTION_ICO = "question.png";

	/**
	 * Añade un mensaje al sistema de gestión de mensajes
	 * 
	 * @param severity
	 * @param msg
	 */

	void addMessage(int severity, String msg);

	/**
	 * Añade un mensaje al sistema de gestión de mensajes pudiendole pasar
	 * parámetros
	 * 
	 * @param severity
	 * @param msg
	 * @param args
	 */

	void addMessage(int severity, String msg, String[] args);

	/**
	 * Obtiene el listado de todos los mensajes
	 * 
	 * @return
	 */

	public List<Message> getViewMessages();
}