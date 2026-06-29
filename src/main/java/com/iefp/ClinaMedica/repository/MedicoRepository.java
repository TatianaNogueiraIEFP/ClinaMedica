package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repositório responsável pelo acesso e gestão dos dados da entidade Medico.
 * Disponibiliza operações CRUD através do Spring Data JPA.
 */
public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("SELECT DISTINCT e.nome FROM Medico m JOIN m.especialidades e WHERE e IS NOT NULL")
    List<String> listarEspecialidades();
}