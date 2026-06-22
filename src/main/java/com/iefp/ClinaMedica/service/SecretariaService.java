package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela gestão de secretárias.
 */
@Service
public class SecretariaService {

    private final SecretariaRepository secretariaRepository;

    public SecretariaService(SecretariaRepository secretariaRepository) {
        this.secretariaRepository = secretariaRepository;
    }

    public List<Secretaria> listarTodos() {
        return secretariaRepository.findAll();
    }
}