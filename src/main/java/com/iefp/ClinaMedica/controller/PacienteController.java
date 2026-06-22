package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.repository.PacienteRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controlador responsável pela gestão de pacientes.
 */
@Controller
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    /**
     * Apresenta a lista de pacientes.
     */
    @GetMapping("/pacientes")
    public String listaPacientes(Model model) {

        List<Paciente> pacientes = pacienteRepository.findAll();
        model.addAttribute("pacientes", pacientes);

        return "pacientes";
    }

    /**
     * Adiciona um novo paciente (versão simplificada).
     *
     * Nota: idealmente isto devia chamar o RegistoService.
     */
    @PostMapping("/adicionar-paciente")
    public String adicionarPaciente(@RequestParam Long utilizadorId) {

        Paciente paciente = new Paciente();
        paciente.setUtilizador(null); // aqui deves buscar o Utilizador pelo ID

        pacienteRepository.save(paciente);

        return "redirect:/pacientes";
    }
}