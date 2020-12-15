package br.com.gfe.constant;

import java.util.Set;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.classroom.ClassroomScopes;

public class ApplicationConstant {
	
	public static HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	public static JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

	public static final Set<String> SCOPES = ClassroomScopes.all();

	public static final String APPLICATION_NAME = "Google Classroom Integration";
}
