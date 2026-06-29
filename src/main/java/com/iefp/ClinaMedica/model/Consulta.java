package com.iefp.ClinaMedica.model;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Classe que representa uma consulta médica no sistema.
 * Contém a informação principal associada à marcação de uma consulta.
 */
public class Consulta {

    /**
     * Identificador único da consulta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Data em que a consulta está agendada.
     */
    private LocalDate data;

    /**
     * Disponibilidade associada a esta consulta.
     * Representa o horário disponível que foi utilizado para marcar a consulta.
     */
    @ManyToOne
    @JoinColumn(name = "disponibilidade_id")
    private Disponibilidade disponibilidade;

    /**
     * Paciente associado à consulta.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    /**
     * Secretária responsável pelo registo/gestão da consulta.
     */
    @ManyToOne
    @JoinColumn(name = "secretaria_id")
    private Secretaria secretaria;

    /**
     * Hora de início da consulta.
     */
    private LocalTime horaInicio;

    /**
     * Hora de fim da consulta.
     */
    private LocalTime horaFim;

    /**
     * Estado atual da consulta (ex: marcada, realizada, cancelada).
     */
    private String estado;
}