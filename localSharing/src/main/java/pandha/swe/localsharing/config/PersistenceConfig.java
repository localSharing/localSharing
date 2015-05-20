package pandha.swe.localsharing.config;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import pandha.swe.localsharing.model.dao.AngebotsDAO;
import pandha.swe.localsharing.model.dao.AusleihartikelDAO;
import pandha.swe.localsharing.model.dao.HilfeleistungDAO;
import pandha.swe.localsharing.model.dao.TauschartikelDAO;
import pandha.swe.localsharing.service.AusleihartikelService;
import pandha.swe.localsharing.service.HilfeleistungService;
import pandha.swe.localsharing.service.LS_AngebotService;
import pandha.swe.localsharing.service.TauschartikelService;

@Configuration
public class PersistenceConfig {
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;
	@Value("${hibernate.show_sql}")
	private String hibernateShowSql;
	@Value("${hibernate.hbm2ddl.auto}")
	private String hibernateHbm2ddlAuto;

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(username);
		ds.setPassword(password);
		return ds;
	}

	@Bean
	public HibernateTemplate getHibernateTemplate(SessionFactory sessionFactory) {
		HibernateTemplate hibernateTemplate = new HibernateTemplate(
				sessionFactory);

		hibernateTemplate.setCheckWriteOperations(false);
		return hibernateTemplate;
	}

	@Bean
	public SessionFactory sessionFactory() {
		LocalSessionFactoryBuilder builder = new LocalSessionFactoryBuilder(
				getDataSource());
		builder.scanPackages("pandha.swe.localsharing.model").addProperties(
				getHibernateProperties());

		return builder.buildSessionFactory();
	}

	public Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", hibernateShowSql);
		properties.put("hibernate.hbm2ddl.auto", hibernateHbm2ddlAuto);
		properties.put("hibernate.connection.useUnicode", "true");
		properties.put("hibernate.connection.characterEncoding", "UTF-8");
		properties.put("hibernate.connection.charSet", "UTF-8");

		return properties;
	}

	@Autowired
	private AusleihartikelDAO ausleihartikelDao;

	@Autowired
	private TauschartikelDAO tauschartikelDao;

	@Autowired
	private HilfeleistungDAO hilfeleistungDao;

	@Bean
	public Map<String, AngebotsDAO<?>> getAngebotDAOs() {
		Map<String, AngebotsDAO<?>> angebotDAOs = new HashMap<String, AngebotsDAO<?>>();
		angebotDAOs.put("ausleihen", ausleihartikelDao);
		angebotDAOs.put("tauschen", tauschartikelDao);
		angebotDAOs.put("helfen", hilfeleistungDao);

		return angebotDAOs;

	}

	@Autowired
	protected AusleihartikelService ausleihartikelService;
	@Autowired
	protected TauschartikelService tauschartikelService;
	@Autowired
	protected HilfeleistungService hilfeleistungService;

	@Bean
	public Map<String, LS_AngebotService<?, ?>> getAngebotServices() {
		Map<String, LS_AngebotService<?, ?>> angebotServices = new HashMap<String, LS_AngebotService<?, ?>>();
		angebotServices.put("ausleihen", ausleihartikelService);
		angebotServices.put("tauschen", tauschartikelService);
		angebotServices.put("helfen", hilfeleistungService);
		return angebotServices;

	}

}