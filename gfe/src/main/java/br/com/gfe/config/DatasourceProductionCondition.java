package br.com.gfe.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Testa se o ambiente é de produçõa para injetar o DataSource a partir do name
 * jndi.
 *
 */
public class DatasourceProductionCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String env = context.getEnvironment().getProperty("spring.profiles.active");
		return env == null || !env.equals("dev");
	}

}