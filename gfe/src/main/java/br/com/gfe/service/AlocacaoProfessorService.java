package br.com.gfe.service;

import org.springframework.stereotype.Service;

import br.com.gfe.dominio.AlocacaoProfessor;
import br.com.gfe.dominio.Application;
import br.com.gfe.dto.AlocacaoProfessorDTO;
import br.com.gfe.repository.AlocacaoProfessorRepository;
import br.com.gfe.repository.ApplicationRepository;
import br.com.gfe.service.client.MatriculaServiceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlocacaoProfessorService {

	private final AlocacaoProfessorRepository professorRepository;
	private final ApplicationRepository appRepository;
	private final MatriculaServiceClient matriculaServiceClient;

	public AlocacaoProfessorDTO create(AlocacaoProfessorDTO dto, Application app) throws Exception {

		AlocacaoProfessor p = new AlocacaoProfessor();
		p.setProfessorIdOrigem(dto.getProfessorIdOrigem());
		p.setEmail(dto.getEmail());
		
		try {
			String profileId = matriculaServiceClient.matricularProfessor(dto, app.getGfeToken());
			app = appRepository.findByCode(app.getCode());
			p.setApp(app);
			p.setCourseId(dto.getCourseId());
			p.setProfileId(profileId);
		} catch (Exception e) {
			p.setError(e.getMessage());
		}

		professorRepository.save(p);
		dto.setProfileId(p.getProfileId());
	
		return dto;
	}

}
