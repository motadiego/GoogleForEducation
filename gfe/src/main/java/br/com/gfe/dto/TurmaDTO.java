package br.com.gfe.dto;

import lombok.Data;

@Data
public class TurmaDTO {

	private int turmaIdOrigem;
	private String codigo;
	private String descricao;
	private String periodoCalendario;
	private String sala;
	
	private String courseId;

}
