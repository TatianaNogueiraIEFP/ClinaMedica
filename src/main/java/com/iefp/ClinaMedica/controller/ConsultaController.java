package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.service.ConsultaService;

public class ConsultaController {

    private final ConsultaService consultaService;

    public ConsultaController(ConsultaService consultaService, ListagemService listagemService) {
        this.consultaService = consultaService;
    }

    @GetMapping("/consultas")
    public String listarConsultas(@RequestParam(required = false) String especialidade,
                                  Model model) {
        model.addAttribute("consultas", consultaService.listarTodas());
        model.addAttribute("especialidades", consultaService.listarEspecialidades());
        model.addAttribute("pacientes", listagemService.listarPacientes());
        model.addAttribute("secretarias", listagemService.listarSecretarias());
        model.addAttribute("especialidadeSelecionada", especialidade);

        if (especialidade != null && !especialidade.isEmpty()) {
            model.addAttribute("disponibilidades",
                    consultaService.listarDisponibilidadePorEspecialidade(especialidade));
        }

        return "consultas";
    }

    @PostMapping("/consultas")
    public String marcarConsulta(@RequestParam Long disponilidadeId,
                                 @RequestParam Long pacienteId,
                                 @RequestParam(required = false) Long secretariaId,
                                 Model model) {
        try {
            consultaService.marcarConsulta(
                    disponilidadeId,
                    pacienteId,
                    secretariaId
            );

            return "redirect:/consultas";

        } catch (RuntimeException erro) {
            model.addAttribute("erro", erro.getMessage());
            model.addAttribute("consultas", consultaService.listarTodas());
            model.addAttribute("especialidades", consultaService.listarEspecialidades());
            model.addAttribute("pacientes", paciente.listarPacientes());
            model.addAttribute("secretarias", listagemService.listarSecretarias());
            return "consultas";
        }
    }
}
