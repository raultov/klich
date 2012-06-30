package net.softforlife.klich.JSF.web.util;

import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public final class URLUtils {

    private static final Log log = LogFactory.getLog(URLUtils.class);

    private static final String X_FORWARDED_HOST = "x-forwarded-host";

    private URLUtils() {
    }

    public static String getServerURL() {
        return getServerURL(null);
    }

    /**
     * @return Server URL as : protocol://serverName:port/
     */
    public static String getServerURL(ServletRequest request) {
        String baseURL = null;
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null || request != null) {

            StringBuilder sbaseURL = new StringBuilder();

            if (request == null) {
                request = (ServletRequest) facesContext.getExternalContext().getRequest();
            }

            if (request instanceof HttpServletRequest) {
                HttpServletRequest httpRequest = (HttpServletRequest) request;

                String serverName = httpRequest.getServerName();
                int serverPort = httpRequest.getServerPort();

                // Detect virtual hosting based in standard header
                String forwardedHost = httpRequest.getHeader(X_FORWARDED_HOST);

                // :XXX: Need to test out with different proxy configurations
                // especially with several proxies routing the request.
                if (forwardedHost != null) {
                    if (forwardedHost.contains(":")) {
                        serverName = forwardedHost.split(":")[0];
                        serverPort = Integer.valueOf(forwardedHost.split(":")[1]);
                    } else {
                        serverPort = 80; // fallback
                        serverName = forwardedHost;
                    }
                }

                // :XXX: To be tested in case of virtual hosting...
                String protocol = httpRequest.getScheme();

                sbaseURL.append(protocol);
                sbaseURL.append("://");
                sbaseURL.append(serverName);
                if (serverPort != 0) {
                    if ("http".equals(protocol) && serverPort != 80
                            || "https".equals(protocol) && serverPort != 443) {
                        sbaseURL.append(':');
                        sbaseURL.append(serverPort);
                    }
                }
                sbaseURL.append('/');
                baseURL = sbaseURL.toString();
            }
        }
        if (baseURL == null) {
            log.error("Could not retrieve base url correctly");
        }
        return baseURL;
    }

    /**
     * @return WebApp name
     */
    public static String getWebAppName() {
        final FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            String baseURL = facesContext.getExternalContext().getRequestContextPath();
            baseURL = baseURL.replace("/", "");
            return baseURL;
        } else {
            return "";
        }
    }

    /**
     * @return base URL as protocol://serverName:port/webappName/
     */
    public static String getBaseURL() {
        String baseURL = null;
        String serverUrl = getServerURL();
        if (serverUrl != null) {
            baseURL = serverUrl + getWebAppName() + '/';
        }
        if (baseURL == null) {
            log.error("Could not retrieve base url correctly");
        }
        return baseURL;
    }

    public static String getBaseURL(ServletRequest request) {
        String baseURL = null;
        String serverUrl = getServerURL(request);
        if (serverUrl != null) {
            baseURL = serverUrl + getWebAppName() + '/';
        }
        if (baseURL == null) {
            log.error("Could not retrieve base url correctly");
        }
        return baseURL;
    }

}