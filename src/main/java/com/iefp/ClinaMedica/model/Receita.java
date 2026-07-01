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
     * Dosagem do medicamento (ex: 500mg, 1 comprimido).
     */
    private String dosagem;

    /**
     * Consulta associada à receita.
     * Muitas receitas podem estar associadas a uma consulta.
     */
    @ManyToOne
    @JoinColumn(name = "consulta_id")
    private Consulta consulta;

    /**
     * Instruções médicas detalhadas para o paciente.
     * Ex: "Tomar após as refeições durante 5 dias".
     */
    @Column(length = 1000)
    private String instrucoes;

    // ---------------- GETTERS E SETTERS ----------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(String medicamento) {
        this.medicamento = medicamento;
    }

    public String getDosagem() {
        return dosagem;
    }

    public void setDosagem(String dosagem) {
        this.dosagem = dosagem;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public String getInstrucoes() {
        return instrucoes;
    }

    public void setInstrucoes(String instrucoes) {
        this.instrucoes = instrucoes;
    }
}