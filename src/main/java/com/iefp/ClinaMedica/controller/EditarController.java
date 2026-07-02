package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.enums.Especialidade;
import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.SecretariaRepository;
import com.iefp.ClinaMedica.service.EditarService;
import com.iefp.ClinaMedica.service.MedicoService;
import com.iefp.ClinaMedica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

/**
 * Controlador responsável pela edição de pacientes,
 * médicos e secretárias.
 */
@Controller
@RequestMapping("/editar")
public class EditarController {

    /**
     * Serviço responsável pela edição dos utilizadores.
     */
    private final EditarService editarService;

    /**
     * Serviço responsável pela gestão dos pacientes.
     */
    private final PacienteService pacienteService;

    /**
     * Serviço responsável pela gestão dos médicos.
     */
    private final MedicoService medicoService;

    /**
     * Repositório responsável pela gestão das secretárias.
     */
    private final SecretariaRepository secretariaRepository;

    /**
     * Construtor do controlador.
     *
     * @param editarService serviço de edição.
     * @param pacienteService serviço de pacientes.
     * @param medicoService serviço de médicos.
     * @param secretariaRepository repositório das secretárias.
     */
    public EditarController(EditarService editarService,
                            PacienteService pacienteService,
                            MedicoService medicoService,
                            SecretariaRepository secretariaRepository) {

        this.editarService = editarService;
        this.pacienteService = pacienteService;
        this.medicoService = medicoService;
        this.secretariaRepository = secretariaRepository;
    }

    /**
     * Apresenta a página de edição de um paciente.
     *
     * @param pacienteId identificador do paciente.
     * @param model modelo utilizado para enviar os dados para a vista.
     * @return página de edição.
     */
    @GetMapping("/paciente/{pacienteId}")
    public String mostrarEditarPaciente(@PathVariable Long pacienteId, Model model) {

        // Procurar o paciente pelo identificador
        Paciente paciente = pacienteService.findById(pacienteId)
                .orElseThrow(() ->
                        new RuntimeException("Paciente não encontrado."));

        // Adicionar o tipo de utilizador ao modelo
        model.addAttribute("tipo", "paciente");

        // Adicionar o identificador do paciente
        model.addAttribute("id", paciente.getId());

        // Adicionar os dados do utilizador associado
        model.addAttribute("utilizador", paciente.getUtilizador());

        // Apresentar a página de edição
        return "editar";
    }

    /**
     * Apresenta a página de edição de um médico.
     *
     * @param medicoId identificador do médico.
     * @param model modelo utilizado para enviar os dados para a vista.
     * @return página de edição.
     */
    @GetMapping("/medico/{medicoId}")
    public String mostrarEditarMedico(@PathVariable Long medicoId, Model model) {

        // Procurar o médico pelo identificador
        Medico medico = medicoService.findById(medicoId)
                .orElseThrow(() ->
                        new RuntimeException("Médico não encontrado."));

        // Adicionar o tipo de utilizador ao modelo
        model.addAttribute("tipo", "medico");

        // Adicionar o identificador do médico
        model.addAttribute("id", medico.getId());

        // Adicionar os dados do utilizador associado
        model.addAttribute("utilizador", medico.getUtilizador());

        // Adicionar a especialidade do médico
        model.addAttribute("especialidade", medico.getEspecialidade());

        // Apresentar a página de edição
        return "editar";
    }

    /**
     * Apresenta a página de edição de uma secretária.
     *
     * @param secretariaId identificador da secretária.
     * @param model modelo utilizado para enviar os dados para a vista.
     * @return página de edição.
     */
    @GetMapping("/secretaria/{secretariaId}")
    public String mostrarEditarSecretaria(@PathVariable Long secretariaId, Model model) {

        // Procurar a secretária pelo identificador
        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(() ->
                        new RuntimeException("Secretária não encontrada."));

        // Adicionar o tipo de utilizador ao modelo
        model.addAttribute("tipo", "secretaria");

        // Adicionar o identificador da secretária
        model.addAttribute("id", secretaria.getId());

        // Adicionar os dados do utilizador associado
        model.addAttribute("utilizador", secretaria.getUtilizador());

        // Apresentar a página de edição
        return "editar";
    }

    /**
     * Atualiza os dados de um paciente.
     *
     * @param pacienteId identificador do paciente.
     * @param nome nome do paciente.
     * @param email endereço de correio eletrónico.
     * @param telefone número de telefone.
     * @param dataNascimento data de nascimento.
     * @param morada morada.
     * @return redirecionamento para a lista de pacientes.
     */
    @PostMapping("/paciente/{pacienteId}")
    public String editarPaciente(@PathVariable Long pacienteId,
                                 @RequestParam String nome,
                                 @RequestParam String email,
                                 @RequestParam String telefone,
                                 @RequestParam LocalDate dataNascimento,
                                 @RequestParam String morada) {

        // Atualizar os dados do paciente
        editarService.editarPaciente(
                pacienteId,
                nome,
                email,
                telefone,
                dataNascimento,
                morada
        );

        // Regressar à lista de pacientes
        return "redirect:/pacientes";
    }

    /**
     * Atualiza os dados de um médico.
     *
     * @param medicoId identificador do médico.
     * @param nome nome do médico.
     * @param email endereço de correio eletrónico.
     * @param telefone número de telefone.
     * @param dataNascimento data de nascimento.
     * @param morada morada.
     * @param especialidade especialidade médica.
     * @return redirecionamento para a lista de médicos.
     */
    @PostMapping("/medico/{medicoId}")
    public String editarMedico(@PathVariable Long medicoId,
                               @RequestParam String nome,
                               @RequestParam String email,
                               @RequestParam String telefone,
                               @RequestParam LocalDate dataNascimento,
                               @RequestParam String morada,
                               @RequestParam Especialidade especialidade) {

        // Atualizar os dados do médico
        editarService.editarMedico(
                medicoId,
                nome,
                email,
                telefone,
                dataNascimento,
                morada,
                especialidade
        );

        // Regressar à lista de médicos
        return "redirect:/medicos";
    }

    /**
     * Atualiza os dados de uma secretária.
     *
     * @param secretariaId identificador da secretária.
     * @param nome nome da secretária.
     * @param email endereço de correio eletrónico.
     * @param telefone número de telefone.
     * @param dataNascimento data de nascimento.
     * @param morada morada.
     * @return redirecionamento para a lista de secretárias.
     */
    @PostMapping("/secretaria/{secretariaId}")
    public String editarSecretaria(@PathVariable Long secretariaId,
                                   @RequestParam String nome,
                                   @RequestParam String email,
                                   @RequestParam String telefone,
                                   @RequestParam LocalDate dataNascimento,
                                   @RequestParam String morada) {

        // Atualizar os dados da secretária
        editarService.editarSecretaria(
                secretariaId,
                nome,
                email,
                telefone,
                dataNascimento,
                morada
        );

        // Regressar à lista de secretárias
        return "redirect:/secretarias";
    }
}