package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.service.ConsultaService;
import com.iefp.ClinaMedica.service.PacienteService;
import com.iefp.ClinaMedica.service.SecretariaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.iefp.ClinaMedica.classes.Especialidade;

import java.util.List;

/**
 * Controlador responsável pela gestão de consultas médicas.
 * Permite listar consultas, pesquisar disponibilidades e marcar consultas.
 */
@Controller
public class ConsultaController {

    /**
     * Serviço de consultas (lógica de negócio).
     */
    private final ConsultaService consultaService;

    /**
     * Serviço de pacientes.
     */
    private final PacienteService pacienteService;

    /**
     * Serviço de secretárias.
     */
    private final SecretariaService secretariaService;

    /**
     * Construtor com injeção de dependências (Spring DI).
     */
    public ConsultaController(ConsultaService consultaService,
                              PacienteService pacienteService,
                              SecretariaService secretariaService) {

        this.consultaService = consultaService;
        this.pacienteService = pacienteService;
        this.secretariaService = secretariaService;
    }

    /**
     * Apresenta a página de consultas.
     * Permite listar consultas e filtrar disponibilidades por especialidade.
     *
     * @param especialidade filtro opcional de especialidade médica
     * @param model objeto para enviar dados para a view (Thymeleaf)
     * @return página consultas.html
     */
    @GetMapping("/consultas")
    public String listarConsultas(@RequestParam(required = false) Especialidade especialidade,
                                  Model model) {

        // Lista de todas as consultas já marcadas
        model.addAttribute("consultas", consultaService.listarTodas());

        // Lista de todas as especialidades disponíveis
        model.addAttribute("especialidades", consultaService.listarEspecialidades());

        // Lista de pacientes para seleção no formulário
        model.addAttribute("pacientes", pacienteService.listarTodos());

        // Lista de secretárias para seleção no formulário
        model.addAttribute("secretarias", secretariaService.listarTodos());

        // Mantém a especialidade selecionada no filtro
        model.addAttribute("especialidadeSelecionada", especialidade);

        // Se foi escolhida uma especialidade, carrega as disponibilidades livres
        if (especialidade != null && !especialidade.name().isEmpty()) {
            model.addAttribute(
                    "disponibilidades",
                    consultaService.listarDisponibilidadePorEspecialidade(especialidade)
            );
        } else {
            model.addAttribute("disponibilidades", List.of());
        }

        return "consultas";
    }

    /**
     * Marca uma nova consulta médica.
     *
     * @param disponibilidadeId ID da disponibilidade selecionada
     * @param pacienteId ID do paciente
     * @param secretariaId ID da secretária responsável
     * @param model objeto para mensagens de erro (caso exista)
     * @return redireciona para a página de consultas
     */
    @PostMapping("/consultas")
    public String marcarConsulta(@RequestParam Long disponibilidadeId,
                                 @RequestParam Long pacienteId,
                                 @RequestParam Long secretariaId,
                                 Model model) {

        try {
            // Executa a marcação da consulta
            consultaService.marcarConsulta(
                    disponibilidadeId,
                    pacienteId,
                    secretariaId
            );

            // Evita reenvio do formulário
            return "redirect:/consultas";

        } catch (RuntimeException erro) {

            // Em caso de erro, mantém dados na página
            model.addAttribute("erro", erro.getMessage());
            model.addAttribute("consultas", consultaService.listarTodas());
            model.addAttribute("especialidades", consultaService.listarEspecialidades());
            model.addAttribute("pacientes", pacienteService.listarTodos());
            model.addAttribute("secretarias", secretariaService.listarTodos());

            return "consultas";
        }
    }
}