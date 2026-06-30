package com.iefp.ClinaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

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

    @Transient
    public int getIdade() {

        // Verifica se o utilizador ou a data de nascimento são nulos
        // para evitar erros de execução (NullPointerException)
        if (utilizador == null || utilizador.getDataNascimento() == null) {
            return 0;
        }

        // Calcula a idade com base na data de nascimento do utilizador
        // até à data atual
        return Period.between(
                utilizador.getDataNascimento(),
                LocalDate.now()
        ).getYears();
    }
}