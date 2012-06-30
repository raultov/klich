package net.softforlife.klich.JSF.web.util;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

// TODO: Auto-generated Javadoc
/**
 * Clase con métodos estáticos para acceder a los mensajes internacionalizados
 */

public class Messages {
		
	/**
	 * Devuelve el valor del campo Message.
	 * 
	 * @param bundleName the bundle name
	 * @param resourceId the resource id
	 * @param params the params
	 * 
	 * @return Devuelve Message
	 */
	public static FacesMessage getMessage(String bundleName, String resourceId,
			Object[] params) {
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		String summary = getString(appBundle, bundleName, resourceId, locale,
				loader, params);
		if (summary == null) {
			summary = "???" + resourceId + "???";
		}
		String detail = getString(appBundle, bundleName,
				resourceId + "_detail", locale, loader, params);
		return new FacesMessage(summary, detail);
	}

	/**
	 * Devuelve el valor del campo String.
	 * 
	 * @param bundle the bundle
	 * @param resourceId the resource id
	 * @param params the params
	 * 
	 * @return Devuelve String
	 */
	public static String getString(String bundle, String resourceId,
			Object[] params) {
		FacesContext context = FacesContext.getCurrentInstance();
		Application app = context.getApplication();
		String appBundle = app.getMessageBundle();
		Locale locale = getLocale(context);
		ClassLoader loader = getClassLoader();
		return getString(appBundle, bundle, resourceId, locale, loader, params);
	}

	/**
	 * Devuelve el valor del campo String.
	 * 
	 * @param bundle1 the bundle1
	 * @param bundle2 the bundle2
	 * @param resourceId the resource id
	 * @param locale the locale
	 * @param loader the loader
	 * @param params the params
	 * 
	 * @return Devuelve String
	 */
	public static String getString(String bundle1, String bundle2,
			String resourceId, Locale locale, ClassLoader loader,
			Object[] params) {
		String resource = null;
		ResourceBundle bundle;
		if (bundle1 != null) {
			bundle = ResourceBundle.getBundle(bundle1, locale, loader);
			if (bundle != null) {
				try {
					resource = bundle.getString(resourceId);
				} catch (MissingResourceException ex) {
				}
			}
		}
		if (resource == null) {
			bundle = ResourceBundle.getBundle(bundle2, locale, loader);
			if (bundle != null) {
				try {
					resource = bundle.getString(resourceId);
				} catch (MissingResourceException ex) {
				}
			}
		}
		if (resource == null) {
			return null; // no match
		}
		if (params == null) {
			return resource;
		}
		MessageFormat formatter = new MessageFormat(resource, locale);
		return formatter.format(params);
	}

	/**
	 * Devuelve el valor del campo Locale.
	 * 
	 * @param context the context
	 * 
	 * @return Devuelve Locale
	 */
	public static Locale getLocale(FacesContext context) {
		Locale locale = null;
		UIViewRoot viewRoot = context.getViewRoot();
		if (viewRoot != null) {
			locale = viewRoot.getLocale();
		}
		if (locale == null) {
			locale = Locale.getDefault();
		}
		return locale;
	}

	/**
	 * Devuelve el valor del campo ClassLoader.
	 * 
	 * @return Devuelve ClassLoader
	 */
	public static ClassLoader getClassLoader() {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		if (loader == null) {
			loader = ClassLoader.getSystemClassLoader();
		}
		return loader;
	}
}
