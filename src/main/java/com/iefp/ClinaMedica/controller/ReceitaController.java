package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Consulta;
import com.iefp.ClinaMedica.model.Receita;
import com.iefp.ClinaMedica.repository.ConsultaRepository;
import com.iefp.ClinaMedica.repository.ReceitaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador responsável pela gestão das receitas médicas.
 * Permite listar receitas existentes e criar novas receitas
 * associadas a consultas médicas.
 */
@Controller
public class ReceitaController {

    /**
     * Repositório responsável pelo acesso às receitas na base de dados.
     */
    private final ReceitaRepository receitaRepository;

    /**
     * Repositório responsável pelo acesso às consultas na base de dados.
     */
    private final ConsultaRepository consultaRepository;

    /**
     * Construtor com injeção de dependências.
     *
     * @param receitaRepository repositório de receitas
     * @param consultaRepository repositório de consultas
     */
    public ReceitaController(ReceitaRepository receitaRepository,
                             ConsultaRepository consultaRepository) {
        this.receitaRepository = receitaRepository;
        this.consultaRepository = consultaRepository;
    }

    /**
     * Apresenta a página de receitas.
     * Envia para a view todas as consultas e receitas existentes.
     *
     * @param model objeto usado para transportar dados para a view (Thymeleaf)
     * @return página "receitas"
     */
    @GetMapping("/receitas")
    public String listarReceitas(Model model) {

        // Lista de todas as consultas existentes (para seleção no formulário)
        model.addAttribute("consultas", consultaRepository.findAll());

        // Lista de todas as receitas registadas no sistema
        model.addAttribute("receitas", receitaRepository.findAll());

        return "receitas";
    }

    /**
     * Cria uma nova receita médica associada a uma consulta.
     *
     * @param consultaId identificador da consulta
     * @param medicamento nome do medicamento prescrito
     * @param dosagem dosagem do medicamento
     * @param instrucoes instruções de utilização para o paciente
     * @return redireciona para a página de receitas
     */
    @PostMapping("/receitas/criar")
    public String criarReceita(@RequestParam Long consultaId,
                               @RequestParam String medicamento,
                               @RequestParam String dosagem,
                               @RequestParam String instrucoes) {

        // Procura a consulta associada à receita
        Consulta consulta = consultaRepository.findById(consultaId)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada."));

        // Cria uma nova entidade Receita
        Receita receita = new Receita();
        receita.setConsulta(consulta);
        receita.setMedicamento(medicamento);
        receita.setDosagem(dosagem);
        receita.setInstrucoes(instrucoes);

        // Guarda a receita na base de dados
        receitaRepository.save(receita);

        return "redirect:/receitas";
    }
}