package G_T.OfficeSystem.config;


import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;



@EnableTransactionManagement
@PropertySources({ @PropertySource("classpath:ds/datasource-cfg.properties") })
@Configuration
@ComponentScan("G_T.OfficeSystem.*")
@EnableAspectJAutoProxy
public class ApplicationContextConfig {

	@Autowired
	private Environment env;
	/*
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}
	*/

	@Bean(name = "viewResolver")
	public ViewResolver getViewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();

		// TilesView 3
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	@Bean
	public MultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(10485760); // 10MB
		multipartResolver.setMaxUploadSizePerFile(1048576); // 1MB
		return multipartResolver;
	}

	@Bean(name = "tilesConfigurer")
	public TilesConfigurer getTilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();

		// TilesView 3
		tilesConfigurer.setDefinitions("/WEB-INF/tiles.xml");
		return tilesConfigurer;
	}


	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		return dataSource;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager getTransactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		DataSource dataSource = this.getDataSource();
		txManager.setDataSource(dataSource);

		return txManager;
	}


	//HibernateのSessionFactoryを設定する
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory() {
		try {
	// Create the SessionFactory from hibernate.cfg.xml
			org.hibernate.cfg.Configuration configuration = new org.hibernate.cfg.Configuration();
	    		configuration.configure("hibernate.cfg.xml");
	    		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
	    																	.applySettings(configuration.getProperties()).build();
	    		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

			return sessionFactory;
		}
		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
/*
	@Bean
	public EnterMethodLogAdvice getEnterMethodLogAdvice() {
	    return new EnterMethodLogAdvice();
	}*/
/*
	@Bean(name = "enterMethodLogAdvice")
	public EnterMethodLogAdvice getEnterMethodLogAdvice() {
		return new EnterMethodLogAdvice();
	}

	@Bean(name = "logPointcut")
	public JdkRegexpMethodPointcut getLogPointcut() {
		JdkRegexpMethodPointcut logPointcut = new JdkRegexpMethodPointcut();
		logPointcut.setPattern(".*");
		return logPointcut;
	}


	@Bean(name = "logEnterAdvisor")
	public DefaultPointcutAdvisor getLogEnterAdvisor() {
		DefaultPointcutAdvisor logEnterAdvisor = new DefaultPointcutAdvisor();
		EnterMethodLogAdvice enterMethodLogAdvice = getEnterMethodLogAdvice();
		JdkRegexpMethodPointcut logPointcut = getLogPointcut();

		logEnterAdvisor.setAdvice(enterMethodLogAdvice);
		logEnterAdvisor.setPointcut(logPointcut);

		return logEnterAdvisor;
	}

	@Bean
	public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}*/


/*	@Bean(name = "globalExceptionResolver")
	public GlobalExceptionResolver getGlobalExceptionResolver() {
	    return new GlobalExceptionResolver();
	}
*/

}
