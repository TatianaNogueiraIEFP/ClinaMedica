package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controlador responsável pela gestão de pacientes.
 * Todas as rotas deste controller começam por /pacientes
 */
@Controller
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    /**
     * Injeção do repositório de pacientes através do construtor
     */
    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Apresenta a lista de todos os pacientes registados.
     * URL: GET /pacientes
     */
    @GetMapping
    public String listarPacientes(Model model) {

        // Vai buscar todos os pacientes à base de dados
        List<Paciente> pacientes = pacienteRepository.findAll();

        // Envia a lista para a view (Thymeleaf)
        model.addAttribute("pacientes", pacientes);

        // Nome da página HTML (templates/pacientes.html)
        return "pacientes";
    }
}