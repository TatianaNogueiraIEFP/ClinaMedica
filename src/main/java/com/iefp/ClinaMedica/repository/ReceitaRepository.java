package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório responsável pelo acesso aos dados da entidade Receita.
 * Permite operações CRUD automaticamente fornecidas pelo Spring Data JPA.
 */
@Repository
public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}