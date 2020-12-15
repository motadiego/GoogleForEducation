package br.com.gfe.dominio;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Application {

	@Id
	private int id;
	// empresa.app.code
	private String code;
	// empresa.app.key
	private String authKey;
	
	// empresa.gfe.token
	private String gfeToken;	
}
