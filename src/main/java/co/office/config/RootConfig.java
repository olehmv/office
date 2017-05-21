package co.office.config;

import java.beans.PropertyVetoException;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import co.entity.Department;
import co.entity.Employee;
import co.entity.Manager;
import co.service.EmployeeService;
import co.service.ManagerService;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "co.repository", entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager")
@ComponentScan(basePackages = { "co" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class) })
@PropertySource(value = { "/WEB-INF/db.properties" }, ignoreResourceNotFound = false)
public class RootConfig {

	@Autowired
	Environment env;

	@Bean
	public DataSource dataSource() throws PropertyVetoException {
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(env.getRequiredProperty("db.driverClassName"));
		dataSource.setJdbcUrl(env.getRequiredProperty("db.databaseurl"));
		dataSource.setUser(env.getRequiredProperty("db.username"));
		dataSource.setPassword(env.getRequiredProperty("db.password"));
		dataSource.setInitialPoolSize(10);
		dataSource.setIdleConnectionTestPeriod(10);
		return dataSource;
	}

	@Bean
	public JpaVendorAdapter jpaVendorAdaptor() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabasePlatform("MYSQL");
		adapter.setShowSql(true);
		adapter.setGenerateDdl(true);
		adapter.setDatabasePlatform("org.hibernate.dialect.MySQLDialect");
		return adapter;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource,
			JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
		factory.setDataSource(dataSource);
		factory.setJpaVendorAdapter(jpaVendorAdapter);
		factory.setJpaProperties(jpaProperties());
		factory.setPackagesToScan("co.entity");
		return factory;
	}

	private Properties jpaProperties() {
		Properties prop = new Properties();
		prop.put("hibernate.dialect", env.getRequiredProperty("hibernate.dialect"));
		prop.put("hibernate.shoq_sql", env.getRequiredProperty("hibernate.show_sql"));
		prop.put("hibernate.format_sql", env.getRequiredProperty("hibernate.format_sql"));
		prop.put("hibernate.hb2ddl.avto", env.getRequiredProperty("hibernate.hbm2ddl.auto"));
		prop.put("hibernate.enable_lazy_load_no_trans",
				env.getRequiredProperty("hibernate.enable_lazy_load_no_trans"));
		return prop;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory)
			throws PropertyVetoException {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setDataSource(dataSource());
		jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
		return jpaTransactionManager;
	}

	@Bean
	public static BeanPostProcessor persistenceAnnotation() {
		return new PersistenceAnnotationBeanPostProcessor();
	}

	@Bean
	public static BeanPostProcessor persistenceTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
	
}
