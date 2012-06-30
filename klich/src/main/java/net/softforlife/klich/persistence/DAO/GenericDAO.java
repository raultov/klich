package net.softforlife.klich.persistence.DAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Query;

/**
 * Interfaz generica de acceso a datos (Data Access Objects). <br>Define las operaciones por defecto que deben realizar
 * todas las clases de acceso a base de datos del sistema, y que heredaran automaticamente las interfaces que sean
 * hijas de GenericDAO.
 * 
 * @param <T> Tipo de la clase con la que se va a trabajar en base de datos.
 * @param <ID> Identificador de clave primaria de la clase.
 * 
 * @author rtovar
 */
public interface GenericDAO<T, ID extends Serializable> {
	
	/**
	 * Carga de base de datos un objeto plano (POJO) de tipo T, en funcion de su identificador de clave primaria ID.
	 * 
	 * @param id El identificador de clave primaria en base de datos del objeto.
	 * 
	 * @return El objeto inicializado.
	 */
	T loadById(ID id);
	
	/**
	 * Almacena en la tabla correspondiente de base de datos la entidad de tipo T, donde T es un valor generico.
	 * 
	 * @param entity La entidad que se quiere almacenar.
	 */
	void persist(T entity);
	
	/**
	 * Actualiza en la tabla correspondiente los valores de la entidad T.
	 * 
	 * @param entity La entidad con los datos que se quiere actualizar.
	 */
	void update(T entity);
	
	/**
	 * Borra la entidad T de la base de datos.
	 * 
	 * @param entity La entidad que se quiere borrar del sistema.
	 */
	void delete(T entity);
	
	/**
	 * Carga de la base de datos todas las entidades de tipo T.
	 * 
	 * @return La lista de objetos de tipo T.
	 */
	List<T> loadAll();
	
	/**
	 * Carga de la base de datos todas las entidades de tipo T.
	 * 
	 * @return La lista de objetos de tipo T.
	 */
	List<T> deleteAll();
	
	/**
	 * Devuelve el numero de objetos (registros de una tabla) de tipo T existentes.
	 * 
	 * @return Numero de registros/objetos de tipo T.
	 */
	int count();
	
	/**
	 * Devuelve el numero de elementos que contiene el resultado de la consulta que se pasa por parametro
	 * en forma de objeto javax.persistence.Query.
	 * 
	 * @param query Objeto con la consulta a realizar.
	 * 
	 * @return Numero de registros/objetos de tipo T.
	 */
	int count(Query query);
	
	/**
	 * Devuelve el numero de elementos que contiene el resultado de la consulta que se pasa por parametro.
	 * La consulta debe ser de tipo Hibernate (HQL) o JPA (JQL).<br><br>
	 * <b>Ejemplo:</b> "from Tuser u where u.userId > 10".
	 * 
	 * @param strQuery the str query
	 * 
	 * @return Numero de registros/objetos de tipo T.
	 */
	int count(String strQuery);
	
	/**
	 * Actualiza en base de datos la lista de entidades que se pasa por parametro en un proceso batch
	 * transaccional. Si las entidades puede que existan previamente en base de datos hay que tomar la
	 * precaución de realizar un preprocesado de la lista de objetos para persistir aquellos que haya que
	 * actualizar en base de datos.
	 * 
	 * @param entities the entities
	 */
	void executeBatch(List<T> entities);
	
	/**
	 * Realiza una limpieza de la memoria de persistencia. Sólo es aconsejable utilizarla después de una
	 * llamada a la funcion "flush()" de la sesion de persistencia.
	 */
	void clearPersistenceMemory();
}
