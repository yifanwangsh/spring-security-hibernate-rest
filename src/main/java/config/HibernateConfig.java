package config;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import auth.DO.UserLoginDO;


@Configuration
public class HibernateConfig {
	
	@Autowired
	private DriverManagerDataSource ds;
	
	@Autowired
	private LocalSessionFactoryBean sessionFactoryBean;
	
	@Bean
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.postgresql.Driver");
		dataSource.setUrl("jdbc:postgresql://localhost:5432/proj");
		dataSource.setSchema("auth");
		dataSource.setUsername("postgres");
		dataSource.setPassword("admin");
		return dataSource;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactoryBean() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(ds);
		sessionFactoryBean.setPackagesToScan(new String[] {"auth"});
		sessionFactoryBean.setAnnotatedClasses(UserLoginDO.class);
		return sessionFactoryBean;
	}
	
	@Bean
	public HibernateTransactionManager tx() {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(sessionFactoryBean.getObject());
		return tx;
	}
	
	@Bean
	public Session session() {
		Session session = sessionFactoryBean.getObject().openSession();
		return session;
	}
}
