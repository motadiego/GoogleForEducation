package br.com.gfe.dto;

import lombok.Data;

@Data
public class MatriculaAlunoDTO {

	private int alunoOrigemId;
	private String email;
	private String courseId;
	
	// identificador do aluno no google
	private String profileId;

}
