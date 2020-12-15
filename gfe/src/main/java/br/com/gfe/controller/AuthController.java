package br.com.gfe.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;

import br.com.gfe.dominio.Application;
import br.com.gfe.service.GoogleAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping(value = "/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

	private final GoogleAuthService authService;

	/**
	 * Chama a autentica��o do google.
	 * 
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/redirect")
	public String redirectToGoogleAuthentication(@RequestParam("urlcallback") String urlCallback , Application app) throws Exception {
		GoogleAuthorizationCodeRequestUrl url = authService.getFlow(app.getCode()).newAuthorizationUrl();
		String redirectUrl = url.setRedirectUri(urlCallback).setAccessType("offline").build();
		log.info("redirectUrl, " + redirectUrl);
		return redirectUrl;
	}

	/**
	 * Gera o token a partir do code retornado pelo Google auth depois do usu�riio autenticado
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("/generateToken")
	public String saveAuthorizationCode(@RequestParam("code") String code, @RequestParam("urlcallback") String urlCallback, Application app) throws Exception {

		log.info("Gerando token para app " + app.getCode());

		if (code != null) {
			GoogleTokenResponse tokenResponse = authService.getFlow(app.getCode()).newTokenRequest(code).setRedirectUri(urlCallback).execute();
			log.info("Token gerado: " + tokenResponse.getAccessToken());
			return tokenResponse.getAccessToken();
		} else {
			throw new ResponseStatusException(HttpStatus.BAD_GATEWAY, "Nenhum token reetornado do Google");
		}
	}
	
	
	/**
	 * Callback URI de redirecionamento do Google auth depois do usu�rio autenticado
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@GetMapping("callback")
	public void saveAuthorizationCode(HttpServletRequest request) throws Exception {
		log.debug("Callback chamado...");
		String code = request.getParameter("code");
		log.info(code);
	}


}