package br.com.gfe.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfe.dominio.Application;
import br.com.gfe.dto.TurmaDTO;
import br.com.gfe.service.TurmaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/turma")
@RequiredArgsConstructor
public class TurmaRestController {

	private final TurmaService turmaService;

	@RequestMapping(method = RequestMethod.POST)
	public TurmaDTO create(@RequestBody TurmaDTO dto, Application app) throws Exception {
		return turmaService.create(dto, app);
	}
	
}