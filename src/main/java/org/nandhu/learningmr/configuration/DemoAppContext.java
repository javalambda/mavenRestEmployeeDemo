package org.nandhu.learningmr.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration // ~= @beans
@ComponentScan("org.nandhu.learningmr") // ~= <context:component-scan base-package="org.nandhu.learningmr"/>
@PropertySource("classpath:config.properties") // ~=loading from properties file
public class DemoAppContext {
	
	@Value("${driverClassName}")
	String driverClassName;
	
	@Value("${url}")
	String url;
	
	@Value("${dbUserName}")
	String userName;
	
	@Value("${password}")
	String password;
	
	@Value("${initialSize}")
	String initialSize;
	
	@Value("${maxTotal}")
	String maxTotal;
	
	// bean id is the name of the method
	@Bean(destroyMethod="")    // ~= @bean
	public BasicDataSource ds()
	{
		BasicDataSource ds = new BasicDataSource();
		// ~= <property> injecting properties
		ds.setDriverClassName(driverClassName);
		ds.setUrl(url);
		ds.setUsername(userName);
		ds.setPassword(password);
		System.out.println("################");
		System.out.println(initialSize + userName +password);
		ds.setInitialSize(Integer.valueOf(initialSize).intValue());
		ds.setMaxTotal(Integer.valueOf(maxTotal).intValue());
		return ds;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate()
	{
		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		//~= injecting property refs
		jdbcTemplate.setDataSource(ds());
		return jdbcTemplate;
	}
	
	 /*
     * PropertySourcesPlaceHolderConfigurer Bean only required for @Value("{}") annotations.
     * Remove this bean if you are not using @Value annotations for injecting properties.
     */
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
	{
		return new PropertySourcesPlaceholderConfigurer();
	}
//	
//	@Bean
//	public static AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor()
//	{
//		return new AutowiredAnnotationBeanPostProcessor();
//	}

}
