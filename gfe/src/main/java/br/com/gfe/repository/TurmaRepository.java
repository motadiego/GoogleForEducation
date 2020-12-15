package br.com.gfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gfe.dominio.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Integer> {

}
