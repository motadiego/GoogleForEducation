package br.com.gfe.service;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.util.store.MemoryDataStoreFactory;

import br.com.gfe.constant.ApplicationConstant;
import br.com.gfe.dominio.Application;
import br.com.gfe.repository.ApplicationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleAuthService {

	private Map<String, GoogleAuthorizationCodeFlow> flows = new HashMap<String, GoogleAuthorizationCodeFlow>();
	private final ApplicationRepository appRepository;

	@PostConstruct
	public void init() throws Exception {

		List<Application> apps = appRepository.findAll();

		for (Application app : apps) {

			InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(app.getCode() + "_cliente.json"));
			MemoryDataStoreFactory dataStoreFactory = new MemoryDataStoreFactory();

			GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(ApplicationConstant.JSON_FACTORY, reader);
			GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY, clientSecrets,
					ApplicationConstant.SCOPES).setDataStoreFactory(dataStoreFactory).build();

			flows.put(app.getCode(), flow);

		}
	}

	public GoogleAuthorizationCodeFlow getFlow(String appCode) {

		GoogleAuthorizationCodeFlow flow = flows.get(appCode);
		if (flow == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "App " + appCode + " não encontrada");
		}

		return flow;
	}

}
