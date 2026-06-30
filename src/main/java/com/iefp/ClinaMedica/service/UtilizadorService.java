package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.classes.Especialidade;
import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela gestão de utilizadores do sistema.
 * Cria o utilizador base e associa automaticamente o perfil
 * correspondente (Médico, Paciente ou Secretária).
 */
@Service
public class UtilizadorService {

    /**
     * Repositório de utilizadores.
     */
    private final UtilizadorRepository utilizadorRepository;

    /**
     * Repositório de médicos.
     */
    private final MedicoRepository medicoRepository;

    /**
     * Repositório de pacientes.
     */
    private final PacienteRepository pacienteRepository;

    /**
     * Repositório de secretárias.
     */
    private final SecretariaRepository secretariaRepository;

    /**
     * Construtor utilizado para injeção de dependências.
     */
    public UtilizadorService(UtilizadorRepository utilizadorRepository,
                             MedicoRepository medicoRepository,
                             PacienteRepository pacienteRepository,
                             SecretariaRepository secretariaRepository) {

        this.utilizadorRepository = utilizadorRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.secretariaRepository = secretariaRepository;
    }

    /**
     * Lista todos os utilizadores existentes no sistema.
     *
     * @return lista de utilizadores
     */
    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }

    /**
     * Cria um novo utilizador e associa automaticamente
     * o perfil correspondente (Médico, Paciente ou Secretária).
     *
     * @param utilizador objeto com os dados do utilizador
     */
    public void criarUtilizador(Utilizador utilizador, String especialidadeStr) {

        Especialidade especialidade = null;

        if (especialidadeStr != null && !especialidadeStr.isBlank()) {
            especialidade = Especialidade.valueOf(especialidadeStr);
        }

        // Guarda o utilizador na base de dados
        Utilizador novo = utilizadorRepository.save(utilizador);

        // Cria automaticamente a entidade correspondente ao perfil
        switch (utilizador.getPerfil()) {

            case MEDICO:
                Medico medico = new Medico();
                medico.setUtilizador(novo);
                medico.setEspecialidade(especialidade);
                medicoRepository.save(medico);
                break;

            case PACIENTE:
                Paciente paciente = new Paciente();
                paciente.setUtilizador(novo);
                pacienteRepository.save(paciente);
                break;

            case SECRETARIA:
                Secretaria secretaria = new Secretaria();
                secretaria.setUtilizador(novo);
                secretariaRepository.save(secretaria);
                break;
        }
    }
}