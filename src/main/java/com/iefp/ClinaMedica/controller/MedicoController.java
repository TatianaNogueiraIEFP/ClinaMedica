package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controlador responsável pela gestão de médicos.
 */
@Controller
public class MedicoController {

    private final MedicoRepository medicoRepository;

    /**
     * Construtor para injeção do repositório.
     */
    public MedicoController(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    /**
     * Apresenta a lista de médicos.
     *
     * @param model objeto para enviar dados para a view
     * @return página de médicos
     */
    @GetMapping("/medicos")
    public String listaMedicos(Model model) {

        List<Medico> medicos = medicoRepository.findAll();
        model.addAttribute("medicos", medicos);

        return "medicos";
    }

    /**
     * Adiciona um médico simples (versão básica).
     *
     * Nota: em projeto real, isto deve usar RegistoService.
     */
    @PostMapping("/adicionar-medico")
    public String adicionarMedico(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha,
            @RequestParam String especialidade) {

        // Aqui está simplificado (sem Utilizador ligado ao RegistoService)
        Medico medico = new Medico();
        medico.setEspecialidade(especialidade);

        medicoRepository.save(medico);

        return "redirect:/medicos";
    }
}