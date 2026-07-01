package com.iefp.ClinaMedica.model;

import jakarta.persistence.*;
import lombok.Data;

/**
 * Entidade que representa um exame clínico.
 * Um exame é realizado por um médico a um paciente,
 * associado a uma consulta.
 */
@Entity
@Data
public class Exame {

    /**
     * Identificador único do exame.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo de exame (ex: sangue, raio-x, ECG, etc.)
     */
    private String tipo;

    /**
     * Resultados ou observações do exame.
     */
    @Column(length = 2000)
    private String resultado;

    /**
     * Consulta associada ao exame.
     */
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    /**
     * Médico que realizou o exame.
     */
    @ManyToOne
    @JoinColumn(name = "medico_id")
    private Medico medico;

    /**
     * Paciente a quem foi feito o exame.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}