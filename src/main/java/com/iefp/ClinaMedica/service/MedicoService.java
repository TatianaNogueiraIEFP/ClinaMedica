package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela gestão de médicos.
 */
@Service
public class MedicoService {

    private final MedicoRepository medicoRepository;

    public MedicoService(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    /**
     * Obtém a lista de todos os médicos.
     *
     * @return lista de médicos.
     */
    public List<Medico> listarTodos() {
        return medicoRepository.findAll();
    }

    /**
     * Obtém um médico através do seu identificador.
     *
     * @param id identificador do médico.
     * @return médico encontrado, caso exista.
     */
    public Optional<Medico> findById(Long id) {
        return medicoRepository.findById(id);
    }

    /**
     * Guarda um médico na base de dados.
     *
     * @param medico médico a guardar.
     * @return médico guardado.
     */
    public Medico save(Medico medico) {
        return medicoRepository.save(medico);
    }
}