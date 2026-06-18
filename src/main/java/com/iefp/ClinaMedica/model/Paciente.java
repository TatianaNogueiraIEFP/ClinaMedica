package com.iefp.ClinaMedica.model;

/**
 * Classe que representa um paciente da clínica.
 */
public class Paciente {

    // Nome do paciente
    private String nome;

    // Idade do paciente
    private Integer idade;

    /**
     * Construtor da classe Paciente.
     *
     * @param nome nome do paciente
     * @param idade idade do paciente
     */
    public Paciente(String nome, Integer idade){
        this.nome = nome;
        this.idade = idade;
    }

    /**
     * Obtém o nome do paciente.
     *
     * @return nome do paciente
     */
    public String getNome(){
        return nome;
    }

    /**
     * Obtém a idade do paciente.
     *
     * @return idade do paciente
     */
    public Integer getIdade(){
        return idade;
    }
}