package br.com.gfe.dominio;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;

@Data
@Entity
public class Turma {

	@Id
	@GeneratedValue
	private int id;

	@ManyToOne
	private Application app;

	private int origemId;
	private String courseId;

	private String error;

}
