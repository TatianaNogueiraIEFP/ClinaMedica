package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Receita;
import com.iefp.ClinaMedica.repository.ReceitaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela gestão de receitas médicas.
 */
@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    /**
     * Construtor com injeção de dependência.
     */
    public ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    /**
     * Lista todas as receitas registadas no sistema.
     */
    public List<Receita> listarReceitas() {
        return receitaRepository.findAll();
    }
}