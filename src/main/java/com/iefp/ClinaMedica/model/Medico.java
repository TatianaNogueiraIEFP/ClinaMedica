package com.iefp.ClinaMedica.model;

import com.iefp.ClinaMedica.classes.Especialidade;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa um médico da clínica.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Medico {

    /**
     * Identificador único do médico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Especialidade médica do profissional.
     */
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    /**
     * Utilizador associado ao médico.
     * Cada médico possui um único utilizador.
     */
    @OneToOne
    @JoinColumn(name = "utilizador_id", unique = true)
    private Utilizador utilizador;
}