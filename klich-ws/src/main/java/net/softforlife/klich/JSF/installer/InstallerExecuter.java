package net.softforlife.klich.JSF.installer;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.context.ServletContextAware;

/**
 * Encapsulación en un hilo del lanzamiento de operaciones de carga de muestra
 * 
 * @author jmhidalgo
 */
public class InstallerExecuter implements ServletContextAware,
		ResourceLoaderAware {

	/** El logger. */
	final Logger logger = Logger.getLogger(InstallerExecuter.class);

	/** El template. */
	//protected JdbcTemplate template = null;
	
	protected EntityManagerFactory entityManagerFactory = null;

	/** El resource loader. */
	protected ResourceLoader resourceLoader = null;

	/** El init on startup. */
	protected boolean initOnStartup = false;

	/** El sql script processor. */
	private SqlScriptProcessor sqlScriptProcessor;

	/**
	 * Sets <code>DataSource</code>.
	 */
	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {
		this.entityManagerFactory  = entityManagerFactory;
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
	 * Implementation of <code>ResourceLoaderAware</code>.
	 */
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.context.ServletContextAware#setServletContext
	 * (javax.servlet.ServletContext)
	 */
	public void setServletContext(ServletContext servletContext) {
		logger.info(" \n########\n#\tSIGUE INSTALLER\n########");
		run();
		logger.info(" \n########\n#\tSIGUE INSTALLER TERMINADO\n########");

		// servletContext.setAttribute(ViewMessageService.SYSTEM_MSG, msgList);
	}

	/**
	 * Run.
	 */
	private void run() {
		//try {

			//sqlScriptProcessor.configureStandAloneDB();
			//sqlScriptProcessor.configureNotStandAloneDB();
			
			sqlScriptProcessor.process();
			//loadSurvey();
		/*} catch (Exception ex) {
			logger.error(" \n************\n*\tSIGUE INSTALLER WARNING: \n"
					+ ex.toString());


		}*/
	}

	/**
	 * Limpieza de la base de datos y nueva creación a nivel de tablas
	 */
	public void cleanDataBase() {
		String sqlFile = "classpath:/installer/sigue_oracle_2009.sql";

		Resource resource = resourceLoader.getResource(sqlFile);
		try {
			// Limpiamos el modelo
			sqlScriptProcessor.execSqlFile(resource);

			// Iniciamos la base de datos
			sqlScriptProcessor.process();

			// Cargamos las encuestas
			//loadSurvey();
		} catch (Exception ex) {
			logger.error(" \n************\n*\tError fichero: " + sqlFile + "\n"
					+ ex.toString(), ex);
		}
	}

	/**
	 * Operación de carga de la encuesta.
	 * 
	 * @throws Exception
	 *             the exception
	 
	private void loadSurvey() throws Exception {
		List surveys = template.query("SELECT * FROM SETB34SURVEY WHERE active = 1",
				new RowMapper() {

					public Object mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return null;
					}
				});

		if (surveys.size() == 0) {
			// La encuesta
			loadSurvey.run(Constants.SURVEY_TYPE.NORMAL.getId(), 11,
					"/tich2011/cuestionario/2011Bloque");
			// El cuestionario de escuchas
			//loadSurvey.run(Constants.SURVEY_TYPE.ESCUCHAS.getId(), 2,
			//		"/tich2011/cuestionatio_escuchas/2011Bloque");
			// El cuestionario de inspecciones
			loadSurvey.run(Constants.SURVEY_TYPE.INSPECCION.getId(), 4,
					"/tich2011/cuestionario_inspecciones/2011Bloque");
		}
	}
*/
	/**
	 * @param loadSurvey
	 *            the loadSurvey to set
	
	public void setLoadSurvey(LoadSurveyInterface loadSurvey) {
		this.loadSurvey = loadSurvey;
	}
 */
	/**
	 * @param sqlScriptProcessor
	 *            the sqlScriptProcessor to set
	 */
	public void setSqlScriptProcessor(SqlScriptProcessor sqlScriptProcessor) {
		this.sqlScriptProcessor = sqlScriptProcessor;
	}
}