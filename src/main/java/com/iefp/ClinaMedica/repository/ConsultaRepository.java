package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável pelo acesso e gestão dos dados da entidade Consulta.
 * Disponibiliza operações CRUD através do Spring Data JPA.
 */
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
