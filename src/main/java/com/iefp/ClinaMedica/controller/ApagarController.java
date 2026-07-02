package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.service.ApagarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador responsável pela remoção de pacientes,
 * médicos e secretárias.
 */
@Controller
@RequestMapping("/apagar")
public class ApagarController {

    private final ApagarService apagarService;

    public ApagarController(ApagarService apagarService) {
        this.apagarService = apagarService;
    }

    /**
     * Remove um paciente.
     *
     * @param pacienteId identificador do paciente.
     * @return redirecionamento para a lista de pacientes.
     */
    @PostMapping("/paciente/{pacienteId}")
    public String apagarPaciente(@PathVariable Long pacienteId) {

        apagarService.apagarPaciente(pacienteId);

        return "redirect:/login";
    }

    /**
     * Remove um médico.
     *
     * @param medicoId identificador do médico.
     * @return redirecionamento para a lista de médicos.
     */
    @PostMapping("/medico/{medicoId}")
    public String apagarMedico(@PathVariable Long medicoId) {

        apagarService.apagarMedico(medicoId);

        return "redirect:/login";
    }

    /**
     * Remove uma secretária.
     *
     * @param secretariaId identificador da secretária.
     * @return redirecionamento para a lista de secretárias.
     */
    @PostMapping("/secretaria/{secretariaId}")
    public String apagarSecretaria(@PathVariable Long secretariaId) {

        apagarService.apagarSecretaria(secretariaId);

        return "redirect:/login";
    }
}