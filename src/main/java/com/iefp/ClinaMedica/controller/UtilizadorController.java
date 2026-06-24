package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;

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
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final SecretariaRepository secretariaRepository;

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
                                SecretariaRepository secretariaRepository) {

        this.utilizadorRepository = utilizadorRepository;
        this.medicoRepository = medicoRepository;
        this.pacienteRepository = pacienteRepository;
        this.secretariaRepository = secretariaRepository;
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
     * Cria um novo utilizador e, dependendo do perfil,
     * cria também o registo na tabela correspondente.
     *
     * @param utilizador objeto preenchido automaticamente pelo formulário
     * @return redireciona para a listagem de utilizadores
     */
    @PostMapping("/adicionar-utilizador")
    public String adicionarUtilizador(@ModelAttribute Utilizador utilizador) {

        // Guarda o utilizador na base de dados e obtém o ID gerado
        Utilizador novo = utilizadorRepository.save(utilizador);

        // Cria entidade associada ao perfil do utilizador
        switch (utilizador.getPerfil()) {

            case "MEDICO":
                Medico medico = new Medico();
                medico.setUtilizador(novo);
                medicoRepository.save(medico);
                break;

            case "PACIENTE":
                Paciente paciente = new Paciente();
                paciente.setUtilizador(novo);
                pacienteRepository.save(paciente);
                break;

            case "SECRETARIA":
                Secretaria secretaria = new Secretaria();
                secretaria.setUtilizador(novo);
                secretariaRepository.save(secretaria);
                break;

            default:
                // Caso o perfil não seja válido, não cria entidades adicionais
                break;
        }

        return "redirect:/utilizadores";
    }
}