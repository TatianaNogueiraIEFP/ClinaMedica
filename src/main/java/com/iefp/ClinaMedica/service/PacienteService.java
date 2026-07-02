package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela gestão de pacientes.
 */
@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Obtém a lista de todos os pacientes.
     *
     * @return lista de pacientes.
     */
    public List<Paciente> listarTodos() {
        return pacienteRepository.findAll();
    }

    /**
     * Obtém um paciente através do seu identificador.
     *
     * @param id identificador do paciente.
     * @return paciente encontrado, caso exista.
     */
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }
}