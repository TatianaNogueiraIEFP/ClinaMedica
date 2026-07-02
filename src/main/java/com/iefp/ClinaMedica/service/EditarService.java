package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.enums.Especialidade;
import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

/**
 * Serviço responsável por disponibilizar os dados necessários
 * para edição de utilizadores da clínica.
 */
@Service
public class EditarService {

    private final UtilizadorRepository utilizadorRepository;
    private final MedicoRepository medicoRepository;
    private final SecretariaRepository secretariaRepository;
    private final PacienteRepository pacienteRepository;

    public EditarService(UtilizadorRepository utilizadorRepository,
                         MedicoRepository medicoRepository,
                         SecretariaRepository secretariaRepository,
                         PacienteRepository pacienteRepository) {

        this.utilizadorRepository = utilizadorRepository;
        this.medicoRepository = medicoRepository;
        this.secretariaRepository = secretariaRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Edita os dados de um paciente.
     *
     * @param pacienteId identificador do paciente.
     * @param nome novo nome.
     * @param email novo endereço de correio eletrónico.
     * @param telefone novo número de telefone.
     * @param dataNascimento nova data de nascimento.
     * @param morada nova morada.
     */
    public void editarPaciente(Long pacienteId,
                               String nome,
                               String email,
                               String telefone,
                               LocalDate dataNascimento,
                               String morada) {

        // Obter o paciente
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() ->
                        new RuntimeException("Paciente não encontrado."));

        // Atualizar os dados do utilizador associado
        paciente.getUtilizador().setNome(nome);
        paciente.getUtilizador().setEmail(email);
        paciente.getUtilizador().setTelefone(telefone);
        paciente.getUtilizador().setDataNascimento(dataNascimento);
        paciente.getUtilizador().setMorada(morada);

        // Guardar as alterações
        utilizadorRepository.save(paciente.getUtilizador());
    }

    /**
     * Edita os dados de um médico.
     *
     * @param medicoId identificador do médico.
     * @param nome novo nome.
     * @param email novo endereço de correio eletrónico.
     * @param telefone novo número de telefone.
     * @param dataNascimento nova data de nascimento.
     * @param morada nova morada.
     * @param especialidade nova especialidade.
     */
    public void editarMedico(Long medicoId,
                             String nome,
                             String email,
                             String telefone,
                             LocalDate dataNascimento,
                             String morada,
                             Especialidade especialidade) {

        // Obter o médico
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() ->
                        new RuntimeException("Médico não encontrado."));

        // Atualizar a especialidade
        medico.setEspecialidade(especialidade);

        // Atualizar os dados do utilizador associado
        medico.getUtilizador().setNome(nome);
        medico.getUtilizador().setEmail(email);
        medico.getUtilizador().setTelefone(telefone);
        medico.getUtilizador().setDataNascimento(dataNascimento);
        medico.getUtilizador().setMorada(morada);

        // Guardar as alterações do utilizador
        utilizadorRepository.save(medico.getUtilizador());

        // Guardar as alterações do médico
        medicoRepository.save(medico);
    }

    /**
     * Edita os dados de uma secretária.
     *
     * @param secretariaId identificador da secretária.
     * @param nome novo nome.
     * @param email novo endereço de correio eletrónico.
     * @param telefone novo número de telefone.
     * @param dataNascimento nova data de nascimento.
     * @param morada nova morada.
     */
    public void editarSecretaria(Long secretariaId,
                                 String nome,
                                 String email,
                                 String telefone,
                                 LocalDate dataNascimento,
                                 String morada) {

        // Obter a secretária
        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(() ->
                        new RuntimeException("Secretária não encontrada."));

        // Atualizar os dados do utilizador associado
        secretaria.getUtilizador().setNome(nome);
        secretaria.getUtilizador().setEmail(email);
        secretaria.getUtilizador().setTelefone(telefone);
        secretaria.getUtilizador().setDataNascimento(dataNascimento);
        secretaria.getUtilizador().setMorada(morada);

        // Guardar as alterações
        utilizadorRepository.save(secretaria.getUtilizador());
    }
}