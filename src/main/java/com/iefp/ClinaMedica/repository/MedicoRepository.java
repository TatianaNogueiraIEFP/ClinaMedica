package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável pelo acesso e gestão dos dados da entidade Medico.
 * Disponibiliza operações CRUD através do Spring Data JPA.
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {

}