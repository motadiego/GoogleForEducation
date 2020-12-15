package br.com.gfe.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import br.com.gfe.dominio.Application;

/**
 * Injeta a aplica��o que est� chamando o endpoint.
 * 
 *
 */
public class ApplicationArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		return methodParameter.getParameterType().equals(Application.class);
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest,
			WebDataBinderFactory webDataBinderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest) nativeWebRequest.getNativeRequest();

		String appCode = request.getHeader("empresa.app.code");
		// String appKey = request.getHeader("empresa.app.key");
		String gfeKey = request.getHeader("empresa.gfe.token");
		
		Application app = new Application();
		app.setCode(appCode);
		app.setGfeToken(gfeKey);
		
		return app;

	}

}