package net.softforlife.klich.persistence.DAOwithJPA;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Query;

import net.softforlife.klich.persistence.DAO.GenericDAO;

import org.apache.commons.lang.WordUtils;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
import org.springframework.orm.jpa.support.JpaDaoSupport;

/**
 * Clase abstracta de acceso a base de datos. Implementa las operaciones que
 * tienen por defecto todas las clases DAO (Data Access Object) del sistema, que
 * deberan heredar de esta.
 * 
 * @param <T> Tipo de la clase con la que se va a trabajar en base de datos.
 * @param <ID> Identificador de clave primaria de la clase.
 * 
 * @author rtovar
 */
public abstract class GenericDAOWithJPA<T, ID extends Serializable> extends
		JpaDaoSupport implements GenericDAO<T, ID> {

	/** El persistent class. */
	private Class<T> persistentClass;

	/**
	 * Constructor de la clase GenericDAOWithJPA.
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOWithJPA() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#getPersistentClass()
	 */
	/**
	 * Devuelve el valor del campo PersistentClass.
	 * 
	 * @return Devuelve PersistentClass
	 */
	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#loadById(java.io.Serializable)
	 */
	public T loadById(ID id) {
		return getJpaTemplate().find(persistentClass, id);
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#persist(java.lang.Object)
	 */
	public void persist(T entity) {
		getJpaTemplate().persist(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#update(java.lang.Object)
	 */
	public void update(T entity) {
		getJpaTemplate().merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#delete(java.lang.Object)
	 */
	public void delete(T entity) {
		getJpaTemplate().remove(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#loadAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> loadAll() {
		return getJpaTemplate().find(
				"Select t from " + persistentClass.getSimpleName() + " t");
	}
	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#deleteAll()
	 */
	@SuppressWarnings("unchecked")
	public List<T> deleteAll() {
		return getJpaTemplate().find(
				"delete from " + persistentClass.getSimpleName());
	}
	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#count()
	 */
	public int count() {
		StringBuffer strbfr = new StringBuffer("select count(*) from ").append(
				persistentClass.getSimpleName()).append(" t");
		EntityManager em = getJpaTemplate().getEntityManagerFactory()
				.createEntityManager();
		Query query = em.createQuery(strbfr.toString());
		Object result = query.getSingleResult();
		if (result instanceof Long) {
			return ((Long) result).intValue();
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#count(javax.persistence.Query)
	 */
	public int count(Query query) {
		// TODO Probar este método. Si "toString()" devuelve la query,
		// concatenar "select count(*)"
		// con la consulta a partir del "from".
		int result = -1;
		result = query.getResultList().size();
		System.out.println(query.toString());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#count(java.lang.String)
	 */
	public int count(String strQuery) {
		EntityManager em = getJpaTemplate().getEntityManagerFactory()
				.createEntityManager();
		Query query = em.createQuery("select count(*) ".concat(strQuery));
		Object result = query.getSingleResult();
		if (result instanceof Long) {
			return ((Long) result).intValue();
		}
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#clearPersistenceMemory()
	 */
	public void clearPersistenceMemory() {
		EntityManager em = getJpaTemplate().getEntityManagerFactory()
				.createEntityManager();
		em.clear();
		logger.debug("SE HA LIMPIADO LA MEMORIA DE PERSISTENCIA.");
	}

	/*
	 * (non-Javadoc)
	 * @see es.ine.sigue.persistence.GenericDAO#executeBatch(java.util.List)
	 */
	public void executeBatch(List<T> entities) {
		logger.debug("executeBatch");
		int cont = 0;
		int total = 0;
		for (T entity : entities) {
			cont++;
			Integer id = getPkValue(entity);
			if (id == null) {
				persist(entity);
			} else {
				update(entity);
			}

			total += 1;
			if (cont % 100 == 0) {
				getJpaTemplate().flush();
				cont = 0;
				logger.debug("Llevamos " + total + " registros procesados...");
			}
		}
		getJpaTemplate().flush();
		logger.debug("Total de registros procesados: " + total);
	}

	/**
	 * Devuelve el valor del campo PkValue.
	 * 
	 * @param entity the entity
	 * 
	 * @return Devuelve PkValue
	 */
	private Integer getPkValue(T entity) {
		Integer invoked = null;
		Field[] declaredFields = entity.getClass().getDeclaredFields();
		for (Field field : declaredFields) {
			Annotation an = field.getAnnotation(Id.class);
			if (an != null) {
				String executeMethodName = "get".concat(WordUtils.capitalize(field.getName()));
				try {
					@SuppressWarnings("rawtypes")
					Class c = null;
					Method executeMethod = entity.getClass().getMethod(executeMethodName, c);
					Object obj = null;
					invoked = (Integer) executeMethod.invoke(entity, obj);
					// logger.debug("PK VALUE: " + invoked);
				} catch (Exception e) {
					logger.warn("Error en reflection: ", e);
				}
				break;
			}
		}
		return invoked;
	}
	
	/**
	 * Devuelve el valor del campo TransactionalEntityManager.
	 * 
	 * @return Devuelve TransactionalEntityManager
	 */
	protected EntityManager getTransactionalEntityManager() {

		EntityManagerFactory emf = getJpaTemplate().getEntityManagerFactory();
		EntityManager entityManager = EntityManagerFactoryUtils
				.getTransactionalEntityManager(emf);
		return entityManager;
	}
	
	/**
	 * executeNativeQuery.
	 * 
	 * @param strQuery the str query
	 * @param params the params
	 * 
	 * @return el valor actual de List<T>
	 */
	@SuppressWarnings("unchecked")
	protected List<T> executeNativeQuery(String strQuery, Map<String, ? extends Object> params) {
		EntityManager em = getJpaTemplate().getEntityManagerFactory().createEntityManager();
		Query query = em.createNativeQuery(strQuery);

		if (params != null) {
			for (Iterator<?> it = params.entrySet().iterator(); it.hasNext();) {
				Map.Entry<String, Object> entry = (Map.Entry<String, Object>) it.next();
				query.setParameter(entry.getKey(), entry.getValue());
			}
		}

		return query.getResultList();
	}
}
