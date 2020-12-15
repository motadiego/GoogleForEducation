package br.com.gfe.service.client;

import org.springframework.stereotype.Component;

import com.google.api.services.classroom.Classroom;
import com.google.api.services.classroom.model.Course;

import br.com.gfe.dto.TurmaDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CourseServiceClient {

	private final AuthServiceClient authServiceClient;

	public String create(TurmaDTO dto , String gfeToken) throws Exception {

		Classroom classroom = authServiceClient.getClassroom(gfeToken);

		Course course = new Course();
		course.setName(dto.getDescricao());
		course.setSection(dto.getPeriodoCalendario());
		course.setDescriptionHeading(dto.getDescricao());
		course.setDescription(dto.getDescricao());
		course.setRoom(dto.getSala());
		course.setOwnerId("me");
		course.setCourseState("ACTIVE");

		course = classroom.courses().create(course).execute();

		return course.getId();

	}

}
