package br.com.gfe.service;

import org.springframework.stereotype.Service;

import br.com.gfe.dominio.Application;
import br.com.gfe.dominio.MatriculaAluno;
import br.com.gfe.dto.MatriculaAlunoDTO;
import br.com.gfe.repository.ApplicationRepository;
import br.com.gfe.repository.MatriculaAlunoRepository;
import br.com.gfe.service.client.MatriculaServiceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MatriculaAlunoService {

	private final MatriculaAlunoRepository alunoRepository;
	private final ApplicationRepository appRepository;
	private final MatriculaServiceClient matriculaServiceClient;

	public MatriculaAlunoDTO create(MatriculaAlunoDTO dto, Application app) throws Exception {

		MatriculaAluno a = new MatriculaAluno();
		a.setOrigemAlunoId(dto.getAlunoOrigemId());
		a.setEmail(dto.getEmail());
		
		try {
			String profileId = matriculaServiceClient.matricularAluno(dto , app.getGfeToken());
			app = appRepository.findByCode(app.getCode());
			a.setApp(app);
			a.setCourseId(dto.getCourseId());
			a.setProfileId(profileId);
		} catch (Exception e) {
			a.setError(e.getMessage());
		}

		alunoRepository.save(a);
		dto.setProfileId(a.getProfileId());
		
		return dto;
	}
}
