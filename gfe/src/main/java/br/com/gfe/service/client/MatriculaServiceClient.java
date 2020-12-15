package br.com.gfe.service.client;

import org.springframework.stereotype.Component;

import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Student;
import com.google.api.services.classroom.model.Teacher;

import br.com.gfe.dto.AlocacaoProfessorDTO;
import br.com.gfe.dto.MatriculaAlunoDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MatriculaServiceClient {

	private final AuthServiceClient authServiceClient;

	public String matricularAluno(MatriculaAlunoDTO dto , String gfeToken) throws Exception {
		Classroom classroom = authServiceClient.getClassroom(gfeToken);
		
		Student student = new Student().setUserId(dto.getEmail());
		student = classroom.courses().students().create(dto.getCourseId(), student).execute();
		
		return student.getProfile().getId();
	}
	
	public String matricularProfessor(AlocacaoProfessorDTO dto , String gfeToken) throws Exception {
		Classroom classroom = authServiceClient.getClassroom(gfeToken);

		Teacher teacher = new Teacher().setUserId(dto.getEmail());
		teacher = classroom.courses().teachers().create(dto.getCourseId(), teacher).execute();
		
		return teacher.getProfile().getId();
	}
}
