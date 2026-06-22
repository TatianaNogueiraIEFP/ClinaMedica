package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Controlador responsável pela gestão de secretárias.
 */
@Controller
public class SecretariaController {

    private final SecretariaRepository secretariaRepository;

    /**
     * Construtor para injeção do repositório.
     */
    public SecretariaController(SecretariaRepository secretariaRepository) {
        this.secretariaRepository = secretariaRepository;
    }

    /**
     * Apresenta a lista de secretárias.
     *
     * @param model objeto para enviar dados para a view
     * @return página de secretárias
     */
    @GetMapping("/secretarias")
    public String listaSecretarias(Model model) {

        List<Secretaria> secretarias = secretariaRepository.findAll();
        model.addAttribute("secretarias", secretarias);

        return "secretarias";
    }

    /**
     * Adiciona uma secretária (versão simplificada).
     *
     * Nota: em projeto real, deve ser usado o RegistoService.
     */
    @PostMapping("/adicionar-secretaria")
    public String adicionarSecretaria(
            @RequestParam String nome,
            @RequestParam String email,
            @RequestParam String senha) {

        // Versão simplificada (sem Utilizador completo)
        Secretaria secretaria = new Secretaria();
        secretariaRepository.save(secretaria);

        return "redirect:/secretarias";
    }
}