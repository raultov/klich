package net.softforlife.klich.service.security;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.softforlife.klich.model.Tuser;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

public class SecurityUser extends User {

	private static final long serialVersionUID = -3604252220720888271L;
	private Tuser userData;
	private Map<String, Object> personalProps = new HashMap<String, Object>();

	public SecurityUser(String login, String password, Boolean active,
			boolean b, boolean b0, boolean b1, GrantedAuthority[] arrayAuths) {
		super(login, password, active, b, b0, b1, arrayAuths);
	}

	/**
	 * Devuelve el objeto usuario almacenado en base de datos.
	 * 
	 * @return Objeto usuario de base de datos.
	 */
	public Tuser getUserData() {
		return userData;
	}

	/**
	 * Establece el objeto usuario almacenado en base de datos.
	 * 
	 * @param userData
	 *            Objeto usuario de base de datos.
	 */
	public void setUserData(Tuser userData) {
		this.userData = userData;
	}

	/**
	 * Establece un conjunto de propiedades/objetos como conjunto de
	 * claves/valor asociados al usuario en sesion.
	 * 
	 * @param props
	 *            Conjunto de propiedades que se quiere asociar al usuario en
	 *            sesion.
	 */

	public void addProperties(Properties props) {
		@SuppressWarnings("rawtypes")
		Enumeration names = props.propertyNames();
		String name;
		while ((name = (String) names.nextElement()) != null) {
			personalProps.put(name, props.getProperty(name));
		}
	}

	/**
	 * Asocia una propiedad al usuario en sesion como par clave/valor.
	 * 
	 * @param name
	 *            Nombre que se otorga a la propiedad.
	 * @param value
	 *            Valor de la propiedad.
	 */
	public void addProperty(String name, Object value) {
		personalProps.put(name, value);
	}

	/**
	 * Devuelve la propiedad asociada al usuario identificada por el parametro
	 * name, y "null" si dicha propiedad no existe.
	 * 
	 * @param name
	 *            Clave de la propiedad que se desea obtener del usuario.
	 * @return Objeto valor de la propiedad. "null" en caso de que no exista.
	 */
	public Object getProperty(String name) {
		return personalProps.get(name);
	}

	/**
	 * Elimina la propiedad asociada al usuario identificada por el parametro
	 * name.
	 * 
	 * @param name
	 *            Clave de la propiedad a eliminar.
	 */
	public void clearProperty(String name) {
		personalProps.remove(name);
	}
}
