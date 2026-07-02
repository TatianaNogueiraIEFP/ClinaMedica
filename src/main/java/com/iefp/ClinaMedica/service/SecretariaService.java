package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela gestão de secretárias.
 */
@Service
public class SecretariaService {

    private final SecretariaRepository secretariaRepository;

    public SecretariaService(SecretariaRepository secretariaRepository) {
        this.secretariaRepository = secretariaRepository;
    }

    /**
     * Obtém a lista de todas as secretárias.
     *
     * @return lista de secretárias.
     */
    public List<Secretaria> listarTodos() {
        return secretariaRepository.findAll();
    }

    /**
     * Obtém uma secretária através do seu identificador.
     *
     * @param id identificador da secretária.
     * @return secretária encontrada, caso exista.
     */
    public Optional<Secretaria> findById(Long id) {
        return secretariaRepository.findById(id);
    }

    /**
     * Guarda uma secretária na base de dados.
     *
     * @param secretaria secretária a guardar.
     * @return secretária guardada.
     */
    public Secretaria save(Secretaria secretaria) {
        return secretariaRepository.save(secretaria);
    }
}