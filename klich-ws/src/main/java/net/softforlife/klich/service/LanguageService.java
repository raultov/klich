package net.softforlife.klich.service;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.softforlife.klich.model.I18nMessage;
import net.softforlife.klich.service.msg.Message;

public interface LanguageService {

	/**
	 * Devuelve el mensaje asociado a la clave teniendo en cuenta el lenguage
	 * seleccionado por el usuario actual
	 * 
	 * @param key
	 *            clave para el mensaje
	 * @return
	 */

	String getMessage(String key);

	/**
	 * Devuelve un mensaje del contenedor
	 * 
	 * @param key
	 * @param params
	 * @return
	 */

	String getMessage(String key, Object[] params);

        /**
	 * Devuelve un mensaje del contenedor
	 *
	 * @param key
	 * @param params
	 * @return
	 */

	String getMessage(String key, Object[] params, Locale locale);

	/**
	 * Devuelve un mensaje del contenedor
	 * 
	 * @param msg
	 * @return
	 */
	
	String getMessage(Message msg);

	/**
	 * Método para establecer el lenguage dul usuario actual
	 * 
	 * @param locale
	 */

	void setCurrentUserLocale(Locale locale);

	/**
	 * Devuelve el lenguage seleccionado por el usuario actual
	 * 
	 * @return
	 */

	Locale getCurrentUserLocale();

	/**
	 * Devuelve un listado con todos los locales permitidos.
	 * 
	 * @return
	 */

	List<Locale> getAllowedLocales();

        /**
         * Devuelve la lista de mensajes internacionalizados para la clave pasada
         * en cada uno de los lenguajes.
         *
         * @param lkey - Clave del mensaje
         * @return Lista de mensajes internacionalizados
         */
        List<I18nMessage> getI18nMessageList (String lkey);

        /**
         * Actualiza los mensajes internacionalizados para la clave pasada
         *
         * @param lkey - Clave del mensaje
         * @param i18nMessageMap - Mensajes internacionalizados para cada idioma
         */
        void updateI18nMessageList (String lkey, @SuppressWarnings("rawtypes") Map i18nMessageMap) ;
}