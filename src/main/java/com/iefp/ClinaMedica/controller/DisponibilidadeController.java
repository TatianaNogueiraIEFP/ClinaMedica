package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.service.DisponibilidadeService;
import com.iefp.ClinaMedica.service.MedicoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Controlador responsável pela gestão das disponibilidades dos médicos.
 */
@Controller
public class DisponibilidadeController {

    private final DisponibilidadeService disponibilidadeService;
    private final MedicoService medicoService;

    public DisponibilidadeController(
            DisponibilidadeService disponibilidadeService,
            MedicoService medicoService) {

        this.disponibilidadeService = disponibilidadeService;
        this.medicoService = medicoService;
    }

    @GetMapping("/disponibilidades")
    public String listarDisponibilidade(Model model) {

        model.addAttribute(
                "disponibilidades",
                disponibilidadeService.listarTodas()
        );

        model.addAttribute(
                "medicos",
                medicoService.listarTodos()
        );

        return "disponibilidades";
    }

    @PostMapping("/disponibilidades")
    public String criarDisponibilidades(
            @RequestParam Long medicoId,
            @RequestParam LocalDate data,
            @RequestParam LocalTime horaInicioTrabalho,
            @RequestParam LocalTime horaFimTrabalho,
            Model model) {

        try {
            disponibilidadeService.criarDisponibilidade(
                    medicoId,
                    data,
                    horaInicioTrabalho,
                    horaFimTrabalho
            );

            return "redirect:/disponibilidades";

        } catch (RuntimeException erro) {

            model.addAttribute("erro", erro.getMessage());
            model.addAttribute("disponibilidades", disponibilidadeService.listarTodas());
            model.addAttribute("medicos", medicoService.listarTodos());

            return "disponibilidades";
        }
    }
}