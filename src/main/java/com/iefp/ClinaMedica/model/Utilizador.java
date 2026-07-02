package com.iefp.ClinaMedica.model;

import com.iefp.ClinaMedica.classes.Perfil;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

/**
 * Entidade que representa um utilizador do sistema.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Utilizador {

    /**
     * Identificador único do utilizador.
     * É gerado automaticamente pela base de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do utilizador.
     */
    private String nome;

    /**
     * Endereço de e-mail do utilizador.
     * Deve ser único na base de dados.
     */
    @Column(unique = true)
    private String email;

    /**
     * Palavra-passe do utilizador.
     */
    private String senha;

    /**
     * Perfil ou função do utilizador no sistema
     */
    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    /**
     * Data de nascimento do utilizador.
     */
    private LocalDate dataNascimento;

    /**
     * Número de telefone do utilizador.
     */
    private String telefone;

    /**
     * Morada do utilizador.
     */
    private String morada;

    /**
     * Calcula a idade do utilizador com base na data de nascimento.
     *
     * @return idade em anos; devolve 0 caso a data de nascimento não esteja definida.
     */
    public int getIdade() {
        if (dataNascimento == null) {
            return 0;
        }
        // Calcula a idade do utilizador
        return Period.between(dataNascimento, LocalDate.now()).getYears();
    }

}