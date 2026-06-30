package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.service.UtilizadorService;

import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import com.iefp.ClinaMedica.repository.SecretariaRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador responsável pela gestão dos utilizadores.
 * Trata os pedidos HTTP relacionados com:
 * - listagem de utilizadores
 * - criação de utilizadores
 * - criação de entidades associadas ao perfil (Médico, Paciente, Secretária)
 */
@Controller
public class UtilizadorController {

    // Repositórios utilizados para acesso à base de dados
    private final UtilizadorRepository utilizadorRepository;
    private final UtilizadorService utilizadorService;

    /**
     * Construtor com injeção de dependências (Spring DI).
     *
     * @param utilizadorRepository repositório dos utilizadores
     * @param medicoRepository repositório dos médicos
     * @param pacienteRepository repositório dos pacientes
     * @param secretariaRepository repositório das secretárias
     */
    public UtilizadorController(UtilizadorRepository utilizadorRepository,
                                MedicoRepository medicoRepository,
                                PacienteRepository pacienteRepository,
                                SecretariaRepository secretariaRepository, UtilizadorService utilizadorService) {

        this.utilizadorRepository = utilizadorRepository;
        this.utilizadorService = utilizadorService;
    }

    /**
     * Apresenta a lista de todos os utilizadores.
     *
     * @param model objeto usado para enviar dados para a view (Thymeleaf)
     * @return nome da página "utilizadores"
     */
    @GetMapping("/utilizadores")
    public String listarUtilizadores(Model model) {

        // Vai buscar todos os utilizadores na base de dados
        List<Utilizador> utilizadores = utilizadorRepository.findAll();

        // Envia a lista para a view
        model.addAttribute("utilizadores", utilizadores);

        return "utilizadores";
    }

    /**
     * Cria um novo utilizador no sistema e, consoante o perfil selecionado
     * (Médico, Paciente ou Secretária), cria também o respetivo registo
     * na tabela correspondente.
     *
     * O objeto Utilizador é preenchido automaticamente a partir dos dados
     * submetidos no formulário.
     *
     * @param utilizador objeto com os dados do formulário
     * @return redirecionamento para a página de listagem de utilizadores
     */
    @PostMapping("/adicionar-utilizador")
    public String adicionarUtilizador(
            @ModelAttribute Utilizador utilizador,
            @RequestParam(required = false) String especialidade) {

        utilizadorService.criarUtilizador(utilizador, especialidade);

        return "redirect:/utilizadores";
    }
}