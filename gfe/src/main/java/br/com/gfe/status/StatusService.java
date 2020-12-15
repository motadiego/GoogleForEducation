package br.com.gfe.status;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Serviço para construir um status page padronizada do serviço.
 *
 */
@Component
@PropertySource(value = "classpath:/build.properties", ignoreResourceNotFound = true)
public class StatusService {

	private StatusDTO status;

	@Autowired
	private Environment env;

	@PostConstruct
	public void init() {

		status = new StatusDTO(new Date(), "gfe");
		status.setInstanceName(AmbienteUtil.getInstanceName());
		// no file build.properties do produto
		try {
			status.setAppBuildDate(env.getProperty("info.build.date"));
		} catch (Exception e) {
			status.setAppBuildDate("no_build_information");
		}
		status.setJavaVersion( System.getProperty("java.version") );
	}

	public StatusDTO getStatus() {
		return status;
	}

}