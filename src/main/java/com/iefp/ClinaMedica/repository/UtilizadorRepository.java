package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Utilizador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositório responsável pelo acesso e gestão dos dados da entidade Utilizador.
 * Disponibiliza operações CRUD através do Spring Data JPA.
 */
public interface UtilizadorRepository extends JpaRepository<Utilizador, Long> {

    /**
     * Procura um utilizador pelo email.
     */
    Optional<Utilizador> findByEmail(String email);
}