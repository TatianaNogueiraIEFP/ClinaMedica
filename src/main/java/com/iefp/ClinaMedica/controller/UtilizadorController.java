package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Controlador responsável pela gestão de utilizadores.
 */
@Controller
public class UtilizadorController {

    private final UtilizadorRepository utilizadorRepository;

    /**
     * Construtor para injeção do repositório.
     */
    public UtilizadorController(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    /**
     * Apresenta a lista de todos os utilizadores do sistema.
     *
     * @param model objeto utilizado para enviar dados para a view
     * @return página Thymeleaf de utilizadores
     */
    @GetMapping("/utilizadores")
    public String listarUtilizadores(Model model) {

        List<Utilizador> utilizadores = utilizadorRepository.findAll();
        model.addAttribute("utilizadores", utilizadores);

        return "utilizadores";
    }
}