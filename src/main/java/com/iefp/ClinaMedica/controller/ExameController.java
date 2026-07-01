package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pela gestão de exames.
 */
@Controller
public class ExameController {

    private final ExameService exameService;
    private final ConsultaService consultaService;
    private final MedicoService medicoService;
    private final PacienteService pacienteService;

    public ExameController(ExameService exameService,
                           ConsultaService consultaService,
                           MedicoService medicoService,
                           PacienteService pacienteService) {

        this.exameService = exameService;
        this.consultaService = consultaService;
        this.medicoService = medicoService;
        this.pacienteService = pacienteService;
    }

    /**
     * Página de exames.
     */
    @GetMapping("/exames")
    public String listar(Model model) {

        model.addAttribute("exames", exameService.listarTodos());
        model.addAttribute("consultas", consultaService.listarTodas());
        model.addAttribute("medicos", medicoService.listarTodos());
        model.addAttribute("pacientes", pacienteService.listarTodos());

        return "exames";
    }

    /**
     * Criar exame.
     */
    @PostMapping("/exames/criar")
    public String criar(@RequestParam Long consultaId,
                        @RequestParam Long medicoId,
                        @RequestParam Long pacienteId,
                        @RequestParam String tipo,
                        @RequestParam String resultado) {

        exameService.criarExame(
                consultaId,
                medicoId,
                pacienteId,
                tipo,
                resultado
        );

        return "redirect:/exames";
    }
}