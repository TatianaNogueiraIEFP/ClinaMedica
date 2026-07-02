package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

/**
 * Serviço responsável pela remoção de utilizadores da clínica.
 */
@Service
public class ApagarService {

    private final UtilizadorRepository utilizadorRepository;
    private final MedicoRepository medicoRepository;
    private final SecretariaRepository secretariaRepository;
    private final PacienteRepository pacienteRepository;

    public ApagarService(UtilizadorRepository utilizadorRepository,
                         MedicoRepository medicoRepository,
                         SecretariaRepository secretariaRepository,
                         PacienteRepository pacienteRepository) {

        this.utilizadorRepository = utilizadorRepository;
        this.medicoRepository = medicoRepository;
        this.secretariaRepository = secretariaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Remove um paciente da base de dados.
     *
     * @param pacienteId identificador do paciente.
     */
    public void apagarPaciente(Long pacienteId) {

        // Obter o paciente
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() ->
                        new RuntimeException("Paciente não encontrado."));

        // Remover o utilizador/paciente associado
        pacienteRepository.delete(paciente);
        utilizadorRepository.delete(paciente.getUtilizador());
    }

    /**
     * Remove um médico da base de dados.
     *
     * @param medicoId identificador do médico.
     */
    public void apagarMedico(Long medicoId) {

        // Obter o médico
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() ->
                        new RuntimeException("Médico não encontrado."));

        // Remover o utilizador/medico associado
        medicoRepository.delete(medico);
        utilizadorRepository.delete(medico.getUtilizador());
    }

    /**
     * Remove uma secretária da base de dados.
     *
     * @param secretariaId identificador da secretária.
     */
    public void apagarSecretaria(Long secretariaId) {

        // Obter a secretária
        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(() ->
                        new RuntimeException("Secretária não encontrada."));

        // Remover o utilizador/secretaria associado
        secretariaRepository.delete(secretaria);
        utilizadorRepository.delete(secretaria.getUtilizador());
    }
}