package net.softforlife.klich.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import net.softforlife.klich.model.I18nMessage;
import net.softforlife.klich.persistence.DAO.I18nMessageDAO;
import net.softforlife.klich.persistence.DAO.LanguageDAO;
import net.softforlife.klich.persistence.i18n.DatabaseMessageSource;
import net.softforlife.klich.service.LanguageService;
import net.softforlife.klich.service.UserService;
import net.softforlife.klich.service.msg.Message;

import org.apache.log4j.Logger;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;

/**
 * La clase LanguageServiceImpl implementa la gestión de la internacinalización
 */
public class LanguageServiceImpl implements LanguageService,
        HttpSessionListener {

    /** El log. */
    private Logger log = Logger.getLogger(LanguageServiceImpl.class);
    /** El user service. */
    private UserService userService = null;
    /** El KEY. */
    private static String KEY = "CURRENT_LOCALE";
    /** El default locale. */
    private Locale defaultLocale = new Locale("es");
    /** El allowed locales. */
    private List<Locale> allowedLocales = null;
    /** El message source. */
    private MessageSource fileMessageSource = null;
    private DatabaseMessageSource databaseMessageSource = null;
    private I18nMessageDAO i18nMessageDAO;
    @SuppressWarnings("unused")
	private LanguageDAO languageDAO;

    /**
     * Establece el valor del campo MessageSource.
     *
     * @param messageSource
     *            es el nuevo valor de MessageSource
     */
    public void setFileMessageSource(MessageSource messageSource) {
        this.fileMessageSource = messageSource;
    }

    /**
     * Establece el valor del campo DefaultLocale.
     *
     * @param defaultLocale
     *            es el nuevo valor de DefaultLocale
     */
    public void setDefaultLocale(Locale defaultLocale) {
        this.defaultLocale = defaultLocale;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.ine.sigue.service.LanguageService#getMessage(es.ine.sigue.service.msg
     * .Message)
     */
    public String getMessage(Message msg) {
        if (msg.getArgs() == null) {
            return getMessage(msg.getMsg());
        }
        return getMessage(msg.getMsg(), msg.getArgs());
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.LanguageService#getMessage(java.lang.String,
     * java.lang.Object[])
     */
    public String getMessage(String key, Object[] params) {

        Locale locale = getCurrentUserLocale();
        String message = null;

        message = getMessage(key, params, locale);

        return message;
        // ResourceBundle resourceBundle =
        // ResourceBundle.getBundle(baseName,locale);
        // return resourceBundle.getString(key);

    }

    @Override
    public String getMessage(String key, Object[] params, Locale locale) {
        String message = null;

        try {
            message = fileMessageSource.getMessage(key, params, locale);
        } catch (Exception e) {
            // e.printStackTrace();           

            //Try from database
            try {
                message = databaseMessageSource.getMessage(key, params, locale);
            } catch (Exception ex) {
                message = "???" + key + "???";
            }

        }
        return message;
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.LanguageService#getMessage(java.lang.String)
     */
    public String getMessage(String key) {
        return getMessage(key, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.LanguageService#getCurrentUserLocale()
     */
    public Locale getCurrentUserLocale() {
        Locale current = null;
        Object object = userService.getSessionProperty(KEY);
        if (object == null) {
            current = defaultLocale;
        } else {
            current = (Locale) object;
        }

        return current;

    }

    /*
     * (non-Javadoc)
     *
     * @see
     * es.ine.sigue.service.LanguageService#setCurrentUserLocale(java.util.Locale
     * )
     */
    public void setCurrentUserLocale(Locale locale) {

        if (!allowedLocales.contains(locale)) {
            log.debug("LOCALE " + locale + " NOT ALLOWED");
            return;
        }

        // TODO error si n existe
        log.debug("LOCALE changed... " + locale);

        userService.setSessionProperty(KEY, locale);

    }

    /**
     * Establece el valor del campo UserService.
     *
     * @param userService
     *            es el nuevo valor de UserService
     */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Metodo principal.
     *
     * @param args
     *            parametros de entrada.
     */
    public static void main(String[] args) {
        /*
         * LenguageServiceImpl serviceImpl = new LenguageServiceImpl();
         * UserService userService = new UserServiceMock();
         * serviceImpl.setUserService(userService); String cad =
         * serviceImpl.getMessage("filter.nocall"); System.out.println(cad);
         * serviceImpl.setCurrentUserLocale(new Locale("en")); cad =
         * serviceImpl.getMessage("filter.nocall"); System.out.println(cad);
         */
    }

    /*
     * (non-Javadoc)
     *
     * @see es.ine.sigue.service.LanguageService#getAllowedLocales()
     */
    public List<Locale> getAllowedLocales() {
        return allowedLocales;
    }

    /**
     * Establece el valor del campo AllowedLocales.
     *
     * @param allowedLocales
     *            es el nuevo valor de AllowedLocales
     */
    public void setAllowedLocales(List<Locale> allowedLocales) {
        this.allowedLocales = allowedLocales;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.servlet.http.HttpSessionListener#sessionCreated(javax.servlet.http
     * .HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
        setCurrentUserLocale(defaultLocale);
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * javax.servlet.http.HttpSessionListener#sessionDestroyed(javax.servlet
     * .http.HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    }

    /**
     * @param databaseMessageSource the databaseMessageSource to set
     */
    public void setDatabaseMessageSource(DatabaseMessageSource databaseMessageSource) {
        this.databaseMessageSource = databaseMessageSource;
    }

    /**
     * @param i18nMessageDAO the i18nMessageDAO to set
     */
    public void setI18nMessageDAO(I18nMessageDAO i18nMessageDAO) {
        this.i18nMessageDAO = i18nMessageDAO;
    }

    /**
     * @param languageDAO the languageDAO to set
     */
    public void setLanguageDAO(LanguageDAO languageDAO) {
        this.languageDAO = languageDAO;
    }

    @Override
    public List<I18nMessage> getI18nMessageList(String lkey) {
        return i18nMessageDAO.getI18nMessageList(lkey);
    }

    @Override
    @Transactional
    public void updateI18nMessageList(String lkey, @SuppressWarnings("rawtypes") Map i18nMessageMap) {
        @SuppressWarnings({ "unchecked", "unused" })
		Iterator<String> it = i18nMessageMap.keySet().iterator();
        List<I18nMessage> persistMsgList = i18nMessageDAO.getI18nMessageList(lkey);

        for (I18nMessage i18nMessage : persistMsgList){
            String value = (String) i18nMessageMap.get(i18nMessage.getLanguageCode());

            i18nMessage.setValue(value);
        }

        i18nMessageDAO.executeBatch(persistMsgList);

        databaseMessageSource.refreshProperties();
    }
}


