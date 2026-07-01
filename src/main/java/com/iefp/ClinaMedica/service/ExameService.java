package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.*;
import com.iefp.ClinaMedica.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela lógica dos exames.
 */
@Service
public class ExameService {

    private final ExameRepository exameRepository;
    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public ExameService(ExameRepository exameRepository,
                        ConsultaRepository consultaRepository,
                        MedicoRepository medicoRepository,
                        PacienteRepository pacienteRepository) {

        this.exameRepository = exameRepository;
        this.consultaRepository = consultaRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Lista todos os exames.
     */
    public List<Exame> listarTodos() {
        return exameRepository.findAll();
    }

    /**
     * Cria um novo exame.
     */
    public void criarExame(Long consultaId,
                           Long medicoId,
                           Long pacienteId,
                           String tipo,
                           String resultado) {

        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada"));

        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado"));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado"));

        Exame exame = new Exame();
        exame.setConsulta(consulta);
        exame.setMedico(medico);
        exame.setPaciente(paciente);
        exame.setTipo(tipo);
        exame.setResultado(resultado);

        exameRepository.save(exame);
    }
}