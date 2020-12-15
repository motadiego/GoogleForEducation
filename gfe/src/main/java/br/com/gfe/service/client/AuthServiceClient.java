package br.com.gfe.service.client;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.stereotype.Service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.services.classroom.Classroom;

import br.com.gfe.constant.ApplicationConstant;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceClient {

	public Classroom getClassroom(String gfeToken) throws GeneralSecurityException, IOException {
		Classroom classroom = new Classroom.Builder(ApplicationConstant.HTTP_TRANSPORT, ApplicationConstant.JSON_FACTORY, getCredentials(gfeToken))
				.setApplicationName(ApplicationConstant.APPLICATION_NAME).build();
		return classroom;
	}

	private Credential getCredentials(String gfeToken) throws IOException {
		GoogleCredential credential = new GoogleCredential().setAccessToken(gfeToken);
		return credential;
	}

}
