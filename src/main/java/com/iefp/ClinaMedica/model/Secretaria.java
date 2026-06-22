package com.iefp.ClinaMedica.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Classe que representa um secretaria da clínica.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Secretaria {

    /**
     * Identificador único do secretaria.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Utilizador associado a secretaria.
     * Cada secretaria possui um único registo de utilizador.
     */
    @OneToOne
    @JoinColumn(name = "utilizador_id", unique = true)
    private Utilizador utilizador;

}