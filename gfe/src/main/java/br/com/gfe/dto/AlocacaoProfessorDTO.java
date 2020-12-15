package br.com.gfe.dto;

import lombok.Data;

@Data
public class AlocacaoProfessorDTO {

	private int professorIdOrigem;
	private String email;
	private String courseId;

	// Identificador do professor no google
	private String profileId;
}
