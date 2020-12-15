package br.com.gfe.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfe.dominio.Application;
import br.com.gfe.dto.MatriculaAlunoDTO;
import br.com.gfe.service.MatriculaAlunoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/aluno")
@RequiredArgsConstructor
public class MatriculaAlunoRestController {

	private final MatriculaAlunoService alunoService;

	@RequestMapping(method = RequestMethod.POST)
	public MatriculaAlunoDTO create(@RequestBody MatriculaAlunoDTO dto, Application app) throws Exception {
		return alunoService.create(dto, app);
	}

}
