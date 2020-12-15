package br.com.gfe.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class AlocacaoProfessor {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Application app;

	private int professorIdOrigem;
	
	private String email;
	private String courseId;
	
	private String profileId;
	
	private String error;

}
