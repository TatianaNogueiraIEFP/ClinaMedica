package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Serviço responsável pelo registo de utilizadores e respetivos perfis
 * (Paciente, Médico e Secretária).
 */
@Service
public class RegistoService {

    private final UtilizadorRepository utilizadorRepository;
    private final SecretariaRepository secretariaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    /**
     * Construtor para injeção de dependências.
     */
    public RegistoService(
            UtilizadorRepository utilizadorRepository,
            SecretariaRepository secretariaRepository,
            MedicoRepository medicoRepository,
            PacienteRepository pacienteRepository) {

        this.utilizadorRepository = utilizadorRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Regista um novo paciente no sistema.
     */
    public void registarPaciente(
            String nome,
            String email,
            String senha,
            LocalDate dataNascimento,
            String telefone,
            String morada) {

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                "PACIENTE",
                dataNascimento,
                telefone,
                morada
        );

        utilizador = utilizadorRepository.save(utilizador);

        Paciente paciente = new Paciente(null, utilizador);
        pacienteRepository.save(paciente);
    }

    /**
     * Regista um novo médico no sistema.
     */
    public void registarMedico(
            String nome,
            String email,
            String senha,
            LocalDate dataNascimento,
            String telefone,
            String morada,
            String especialidade) {

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                "MEDICO",
                dataNascimento,
                telefone,
                morada
        );

        utilizador = utilizadorRepository.save(utilizador);

        Medico medico = new Medico(null, especialidade, utilizador);
        medicoRepository.save(medico);
    }

    /**
     * Regista uma nova secretária no sistema.
     */
    public void registarSecretaria(
            String nome,
            String email,
            String senha,
            LocalDate dataNascimento,
            String telefone,
            String morada) {

        Utilizador utilizador = new Utilizador(
                null,
                nome,
                email,
                senha,
                "SECRETARIA",
                dataNascimento,
                telefone,
                morada
        );

        utilizador = utilizadorRepository.save(utilizador);

        Secretaria secretaria = new Secretaria(null, utilizador);
        secretariaRepository.save(secretaria);
    }
}