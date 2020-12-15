package br.com.gfe.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.gfe.dominio.Application;
import br.com.gfe.dto.AlocacaoProfessorDTO;
import br.com.gfe.service.AlocacaoProfessorService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(value = "/api/professor")
@RequiredArgsConstructor
public class AlocacaoProfessorRestController {

	private final AlocacaoProfessorService professorService;

	@RequestMapping(method = RequestMethod.POST)
	public AlocacaoProfessorDTO create(@RequestBody AlocacaoProfessorDTO dto,Application app ) throws Exception {
		return professorService.create(dto, app);
	}

}
