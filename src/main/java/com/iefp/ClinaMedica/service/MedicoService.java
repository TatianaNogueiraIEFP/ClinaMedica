package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela gestão de médicos.
 */
@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }
}