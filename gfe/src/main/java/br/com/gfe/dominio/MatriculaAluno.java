package br.com.gfe.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class MatriculaAluno {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Application app;

	private String courseId;
	private String email;
	
	private int origemAlunoId;
	
	private String profileId;
	
	private String error;

}
