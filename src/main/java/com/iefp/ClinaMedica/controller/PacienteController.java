package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Controlador responsável pela gestão dos pacientes.
 */
@Controller
public class PacienteController {

    // Lista que armazena os pacientes em memória
    private List<Paciente> pacientes = new ArrayList<>();

    /**
     * Apresenta a página com a lista de pacientes.
     *
     * @param model objeto utilizado para enviar dados para a view
     * @return nome da página Thymeleaf a apresentar
     */
    @GetMapping("/pacientes")
    public String listaPacientes(Model model) {

        // Adiciona a lista de pacientes ao modelo
        model.addAttribute("pacientes", pacientes);

        // Devolve a página pacientes.html
        return "pacientes";
    }

    /**
     * Recebe os dados do formulário e adiciona um novo paciente.
     *
     * @param nome nome do paciente
     * @param idade idade do paciente
     * @return redirecionamento para a lista de pacientes
     */
    @PostMapping("/adiconarpacientes")
    public String adicionarPaciente(
            @RequestParam String nome,
            @RequestParam Integer idade) {

        // Cria um novo paciente e adiciona-o à lista
        pacientes.add(new Paciente(nome, idade));

        // Redireciona para a página de listagem
        return "redirect:/pacientes";
    }
}