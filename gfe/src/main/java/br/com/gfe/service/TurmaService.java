package br.com.gfe.service;

import org.springframework.stereotype.Service;

import br.com.gfe.dominio.Application;
import br.com.gfe.dominio.Turma;
import br.com.gfe.dto.TurmaDTO;
import br.com.gfe.repository.ApplicationRepository;
import br.com.gfe.repository.TurmaRepository;
import br.com.gfe.service.client.CourseServiceClient;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TurmaService {

	private final TurmaRepository turmaRepository;
	private final ApplicationRepository appRepository;
	private final CourseServiceClient courseServiceClient;

	public TurmaDTO create(TurmaDTO dto, Application app) throws Exception {

		Turma t = new Turma();
		t.setOrigemId(dto.getTurmaIdOrigem());
		try {
			String courseId = courseServiceClient.create(dto, app.getGfeToken());
			app = appRepository.findByCode(app.getCode());
			t.setApp(app);
			t.setCourseId(courseId);
		} catch (Exception e) {
			t.setError(e.getMessage());
		}

		turmaRepository.save(t);
		dto.setCourseId(t.getCourseId());

		return dto;

	}

}
