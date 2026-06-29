package com.iefp.ClinaMedica.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Entidade que representa a disponibilidade de um médico.
 * Define os períodos de tempo em que um médico está disponível ou ocupado.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Disponibilidade {

    /**
     * Identificador único da disponibilidade.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Médico associado a esta disponibilidade.
     */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    /**
     * Data da disponibilidade.
     */
    private LocalDate data;

    /**
     * Hora de início do período.
     */
    private LocalTime horaInicio;

    /**
     * Hora de fim do período.
     */
    private LocalTime horaFim;

    /**
     * Indica se o horário está ocupado (true) ou livre (false).
     */
    private boolean ocupada;
}