package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela gestão de utilizadores.
 */
@Service
public class UtilizadorService {

    private final UtilizadorRepository utilizadorRepository;

    public UtilizadorService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    public List<Utilizador> listarTodos() {
        return utilizadorRepository.findAll();
    }
}