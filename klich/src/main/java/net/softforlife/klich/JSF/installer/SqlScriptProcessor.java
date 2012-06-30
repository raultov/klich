package net.softforlife.klich.JSF.installer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

/**
 * Clase responsable de cargar scripts y lanzarlos contra la base de datos
 * 
 */
public class SqlScriptProcessor implements ResourceLoaderAware {
	/** El logger. */
	final Logger logger = Logger.getLogger(SqlScriptProcessor.class);
	/** El template. */
	protected JdbcTemplate template = null;
	protected EntityManagerFactory entityManagerFactory = null;
	/** El resource loader. */
	protected ResourceLoader resourceLoader = null;
	/** El init on startup. */
	protected boolean initOnStartup = false;
	/** El l sql scripts. */
	protected List<String> lSqlScripts = null;

	private List<String> standAloneSqlStartList = new ArrayList<String>();
	private List<String> sqlStartList = new ArrayList<String>();

	/**
	 * Sets <code>DataSource</code>.
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		//this.execSqlFile(new Resource());
		this.entityManagerFactory  = entityManagerFactory;
		//template = new JdbcTemplate(((LocalContainerEntityManagerFactoryBean) entityManagerFactory).getDataSource());
	}

	/**
	 * Whether or not SQL scripts on startup. Default is <code>false</code>.
	 */
	public boolean isInitOnStartup() {
		return initOnStartup;
	}

	/**
	 * Sets whether or not SQL scripts run on statup. Default is
	 * <code>false</code>.
	 */
	public void setInitOnStartup(boolean initOnStartup) {
		this.initOnStartup = initOnStartup;
	}

	/**
	 * Gets SQL scripts.
	 */
	public List<String> getSqlScripts() {
		return lSqlScripts;
	}

	/**
	 * Sets SQL scripts.
	 */
	public void setSqlScripts(List<String> lSqlScripts) {
		this.lSqlScripts = lSqlScripts;
	}

	public void configureStandAloneDB() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		for (String sql : standAloneSqlStartList) {
			logger.info("Sql de configuración de base de datos :"+sql);

			Query query = entityManagerFactory.createEntityManager().createNativeQuery(sql);
			query.executeUpdate();
			em.getTransaction().commit();
		}
		em.close();
	}
	
	public void configureNotStandAloneDB() {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();		
		for (String sql : sqlStartList) {
			logger.debug("Sql de configuración de base de datos :"+sql);
			
			Query query = entityManagerFactory.createEntityManager().createNativeQuery(sql);
			query.executeUpdate();
			em.getTransaction().commit();
		}
		em.close();
	}

	/**
	 * Implementation of <code>IntializingBean</code>.
	 * 
	 * public void afterPropertiesSet() throws Exception { if (initOnStartup) {
	 * process(); } }
	 */
	/**
	 * Iniciliza SQL scripts.
	 */
	public void process()  {
		if (lSqlScripts != null) {

			
			for (String sqlScript : lSqlScripts) {
				
				String sql = null;

				Resource resource = resourceLoader.getResource(sqlScript);

				if (!resource.exists()) {
					EntityManager em = entityManagerFactory.createEntityManager();
					em.getTransaction().begin();
					
					sql = sqlScript;

					if (StringUtils.hasLength(sql.trim())) {
						// execute sql
						sql = sql.replace(";", "");
						logger.info("SQL query: " + sql);
						Query query = entityManagerFactory.createEntityManager().createNativeQuery(sql);
						query.executeUpdate();
						em.getTransaction().commit();
					}
					
					em.getTransaction().commit();
					em.close();

				} else {

					try {
						execSqlFile(resource);
					} catch (Exception e) {
						logger
								.error(" \n************\n*\tError fichero: \n"
										+ sqlScript
										+ "\n********\n"
										+ e.toString());
					}

				}
			}
		}
	}

	/**
	 * Ejecución sql file.
	 * 
	 * @param resource
	 *            the resource
	 * 
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public void execSqlFile(Resource resource) throws Exception {
		BufferedReader br = null;
		String sql = null;
		
		try {
			br = new BufferedReader(new InputStreamReader(resource
					.getInputStream()));

			StringBuilder sb = new StringBuilder();
			String line = null;
			
			EntityManager em = entityManagerFactory.createEntityManager();
			em.getTransaction().begin();

			while ((line = br.readLine()) != null) {

				if (!line.startsWith("--")) {
					line = line.trim();
					sb.append(line);
					sb.append("\n");
					if (line.endsWith(";")) {
						sql = sb.toString();
						
						if (StringUtils.hasLength(sql)) {
							// execute sql
							sql = sql.substring(0, sql.length() - 2);

							sql = sql.trim();
							logger.info("Query sql: " + sql);

							Query query = em.createNativeQuery(sql);
							query.executeUpdate();

						}

						sb = new StringBuilder();
					}
				}

			}
			
			em.getTransaction().commit();
			em.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				br.close();
			} 
			catch (Exception e) {
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ResourceLoaderAware#setResourceLoader(org
	 * .springframework.core.io.ResourceLoader)
	 */
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 * @param sqlStartList the sqlStartList to set
	 */
	public void setSqlStartList(List<String> sqlStartList) {
		this.sqlStartList = sqlStartList;
	}

	/**
	 * @param standAloneSqlStartList the standAloneSqlStartList to set
	 */
	public void setStandAloneSqlStartList(List<String> standAloneSqlStartList) {
		this.standAloneSqlStartList = standAloneSqlStartList;
	}

}
