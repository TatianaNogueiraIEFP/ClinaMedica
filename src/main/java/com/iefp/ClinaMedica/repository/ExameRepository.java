package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.model.Exame;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositório responsável pelos exames.
 */
@Repository
public interface ExameRepository extends JpaRepository<Exame, Long> {

    /**
     * Lista exames por paciente.
     */
    List<Exame> findByPaciente_Id(Long pacienteId);

    /**
     * Lista exames por médico.
     */
    List<Exame> findByMedico_Id(Long medicoId);
}