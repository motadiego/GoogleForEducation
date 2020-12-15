package br.com.gfe.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configura o Argumen Resolver para inserir o usuário logado nos controladores.
 * 
 *
 */
@Configuration
public class GfeWebMvcConfigurer implements WebMvcConfigurer {

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new ApplicationArgumentResolver());

	}

}
