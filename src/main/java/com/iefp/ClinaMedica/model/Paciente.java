package com.iefp.ClinaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa um paciente da clínica.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    /**
     * Identificador único do paciente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Utilizador associado ao paciente.
     * Cada paciente possui um único utilizador.
     */
    @OneToOne
    @JoinColumn(name = "utilizador_id", unique = true)
    private Utilizador utilizador;

    /**
     * Utilizador associado ao medico.
     * Cada paciente possui um único medico.
     */
    @ManyToOne
    @JoinColumn(name = "medico_id", unique = true)
    private Medico medico;
}