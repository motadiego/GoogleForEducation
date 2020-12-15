package br.com.gfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gfe.dominio.Application;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
	
	Application findByCode(String code);
}
