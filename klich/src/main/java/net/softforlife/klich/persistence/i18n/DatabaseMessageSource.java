package net.softforlife.klich.persistence.i18n;



import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.softforlife.klich.model.I18nMessage;
import net.softforlife.klich.persistence.DAO.I18nMessageDAO;

import org.apache.log4j.Logger;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.core.io.ResourceLoader;

public class DatabaseMessageSource extends AbstractMessageSource implements
		ResourceLoaderAware {

    protected final Logger log = Logger.getLogger(getClass());
    private I18nMessageDAO i18nMessageDAO;
    /** Cache to hold already loaded properties */
    private final Map<String, Map<String, String>> cachedProperties = new HashMap<String, Map<String, String>>();
    /** Milisegundos para refrescar el cache */
    private long cacheMillis = -1;
    /** reset everytime the timestamp was changed */
    private long refreshTimestamp = -1;

    /**
     * Metodo para cargar toda los mesajes desde la base de datos.
     *
     * @return Map - Separa las claves segun locale.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map<String, Map<String, String>> refreshProperties() {
        synchronized (this.cachedProperties) {
            if (log.isDebugEnabled()) {
                log.debug(" \n\t ** Refreshing messages \n");
            }
            List<I18nMessage> list = getI18nMessageDAO().loadAll();
            cachedProperties.clear();
            for (I18nMessage map : list) {
                String localeCode = map.getLanguageCode();
                Map cachePropertiesLocale = cachedProperties.get(localeCode);
                if (cachePropertiesLocale == null) {
                    cachePropertiesLocale = new HashMap<String, String>();
                }

                cachePropertiesLocale.put(map.getLkey(), map.getValue());
                cachedProperties.put(localeCode, cachePropertiesLocale);
            }
            if (log.isDebugEnabled()) {
                log.debug("Messages: " + cachedProperties);
            }

            refreshTimestamp = System.currentTimeMillis();

            return cachedProperties;
        }
    }

    private Map<String, String> getProperties(Locale locale) {
        synchronized (this.cachedProperties) {           

            if (this.cachedProperties != null && this.cachedProperties.size() > 0 && (cacheMillis < 0 || refreshTimestamp > (System.currentTimeMillis() - this.cacheMillis))) {
                // up to date
                return this.cachedProperties.get(locale.toString().toUpperCase());
            }
            refreshProperties();

            return this.cachedProperties.get(locale.toString().toUpperCase());
        }
    }

    /**
     * initializer for preventing dublicate code in the constructors
     *
     * @param dataSource
     * @param basename
     */
    private void initialize(I18nMessageDAO i18nMessageDAO) {
        this.i18nMessageDAO = i18nMessageDAO;
        //setUseCodeAsDefaultMessage(true);
        //log.debug(refreshProperties());
        log.debug("DatabaseMessageSource is constructed ");
    }

    /**
     * Constructor por defecto.
     *
     * @param i18nMessageDAO - Dao para el manejo de la tabla de mensajes
     */
    public DatabaseMessageSource(I18nMessageDAO i18nMessageDAO) {
        super();
        initialize(i18nMessageDAO);
    }

    @Override
    protected MessageFormat resolveCode(String lkey, Locale locale) {
        if (log.isDebugEnabled()) {
            log.debug("Getting message with code: " + lkey);
        }
        String msg = resolveCodeWithoutArguments(lkey, locale);
        MessageFormat result = createMessageFormat(msg, locale);
        return result;
    }

    @Override
    protected String resolveCodeWithoutArguments(String lkey, Locale locale) {
        Map<String, String> m = getProperties(locale);
        if (m != null) {
            return m.get(lkey);
        }
        return null;
    }

    public void setResourceLoader(ResourceLoader arg0) {
    }

    /**
     * Cambia el numero de segundos del cache de propiedades

     * <ul>
     * <li>Por defecto es "-1", indica que el cache es para siempre
     * <code>java.util.ResourceBundle</code>).
     * <li>Un numero positivo da el número de segundos que pasarán entre cada refresco.
     *
     * <li>Un valor de "0" hace que los mensajes se refresquen en cada acceso
     * <b>No usar este valor en producción</b>
     * </ul>
     */
    public void setCacheSeconds(int cacheSeconds) {
        this.cacheMillis = (cacheSeconds * 1000);
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * @return the i18nMessageDAO
     */
    public I18nMessageDAO getI18nMessageDAO() {
        return i18nMessageDAO;
    }

    /**
     * @param i18nMessageDAO the i18nMessageDAO to set
     */
    public void setI18nMessageDAO(I18nMessageDAO i18nMessageDAO) {
        this.i18nMessageDAO = i18nMessageDAO;
    }
}
