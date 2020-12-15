package br.com.gfe.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jndi.JndiObjectFactoryBean;

@Configuration
public class DataSourceConfig {

	@Bean(name = { "appDataSource", "jndiDataSource" })
	@Primary
	@ConfigurationProperties(prefix = "gfe.datasource.app")
	@ConditionalOnProperty(name = "spring.profiles.active", havingValue = "dev", matchIfMissing = false)
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}

	@Primary
	@Bean(name = { "appDataSource", "jndiDataSource" })
	@Conditional(DatasourceProductionCondition.class)
	public DataSource productionDataSource(@Value("${gfe.datasource.app.jndi-name}") String jndiName)
			throws NamingException {
		return createDataSourceJndi(jndiName);
	}

	private DataSource createDataSourceJndi(String name) {
		try {
			return createDataSourceJndi2(name);
		} catch (Exception e) {
			throw new BeanCreationException("erro ao criar datasource para o recurso jndi " + name, e);
		}
	}

	private DataSource createDataSourceJndi2(String name) throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean bean = new JndiObjectFactoryBean();
		bean.setJndiName("java:comp/env/" + name);
		bean.setProxyInterface(DataSource.class);
		bean.setLookupOnStartup(false);
		bean.afterPropertiesSet();
		return (DataSource) bean.getObject();
	}

}
