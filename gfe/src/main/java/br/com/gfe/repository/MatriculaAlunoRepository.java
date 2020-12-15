package br.com.gfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gfe.dominio.MatriculaAluno;

@Repository
public interface MatriculaAlunoRepository extends JpaRepository<MatriculaAluno, Integer> {

}
