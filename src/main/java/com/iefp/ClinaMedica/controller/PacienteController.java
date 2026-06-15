package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PacienteController {

    private List<Paciente> pacientes = new ArrayList<>();

    @GetMapping("/pacientes")
    public String listaPacientes(Model model) {
        model.addAttribute("pacientes", pacientes);
        return "pacientes";
    }

    @PostMapping("/adiconarpacientes")
    public String adicionarPaciente(
            @RequestParam String nome,
            @RequestParam Integer idade) {

        pacientes.add(new Paciente(nome, idade));
        return "redirect:/pacientes";
    }
}