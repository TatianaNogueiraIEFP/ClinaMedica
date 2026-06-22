package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável pelo acesso e gestão dos dados da entidade Paciente.
 * Disponibiliza operações CRUD através do Spring Data JPA.
 */
public interface PacienteRepository extends JpaRepository<Paciente, Long> {

}