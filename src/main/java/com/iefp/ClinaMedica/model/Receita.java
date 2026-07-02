package com.iefp.ClinaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidade que representa uma receita médica emitida numa consulta.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Receita {

    /**
     * Identificador único da receita.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome do medicamento prescrito.
     */
    private String medicamento;

    /**
     * Dosagem do medicamento (ex.: 500 mg, 1 comprimido).
     */
    private String dosagem;

    /**
     * Consulta associada à receita.
     * Várias receitas podem estar associadas à mesma consulta.
     */
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    /**
     * Instruções médicas detalhadas para o paciente.
     * Exemplo: "Tomar após as refeições durante 5 dias".
     */
    @Column(length = 1000)
    private String instrucoes;
}