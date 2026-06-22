package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Secretaria;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositório responsável pelo acesso e gestão dos dados da entidade Secretaria.
 * Disponibiliza operações CRUD através do Spring Data JPA.
 */
public interface SecretariaRepository extends JpaRepository<Secretaria, Long> {

}