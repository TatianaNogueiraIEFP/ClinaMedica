package com.iefp.ClinaMedica.repository;

import com.iefp.ClinaMedica.classes.Especialidade;
import com.iefp.ClinaMedica.model.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Repository responsável pelo acesso aos dados de Disponibilidade.
 */
public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    /**
     * Lista disponibilidades livres por especialidade do médico.
     */
    List<Disponibilidade> findByMedico_EspecialidadeAndOcupadaFalseOrderByDataAscHoraInicioAsc(
            Especialidade especialidade
    );

    /**
     * Lista todas as disponibilidades de um médico.
     */
    List<Disponibilidade> findByMedico_IdOrderByDataAscHoraInicioAsc(Long medicoId);

    /**
     * Verifica se já existe uma disponibilidade igual para o médico.
     */
    boolean existsByMedico_IdAndDataAndHoraInicioAndHoraFim(
            Long medicoId,
            LocalDate data,
            LocalTime horaInicio,
            LocalTime horaFim
    );
}