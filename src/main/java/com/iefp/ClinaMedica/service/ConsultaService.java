package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.classes.Especialidade;
import com.iefp.ClinaMedica.model.Consulta;
import com.iefp.ClinaMedica.model.Disponibilidade;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço responsável pela gestão das consultas médicas.
 */
@Service
public class ConsultaService {

    /**
     * Repositório das consultas.
     */
    private final ConsultaRepository consultaRepository;

    /**
     * Repositório das disponibilidades.
     */
    private final DisponibilidadeRepository disponibilidadeRepository;

    /**
     * Repositório dos pacientes.
     */
    private final PacienteRepository pacienteRepository;

    /**
     * Repositório das secretárias.
     */
    private final SecretariaRepository secretariaRepository;

    /**
     * Repositório dos médicos.
     */
    private final MedicoRepository medicoRepository;

    /**
     * Construtor utilizado para a injeção de dependências.
     */
    public ConsultaService(ConsultaRepository consultaRepository,
                           DisponibilidadeRepository disponibilidadeRepository,
                           PacienteRepository pacienteRepository,
                           SecretariaRepository secretariaRepository,
                           MedicoRepository medicoRepository) {

        this.consultaRepository = consultaRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.pacienteRepository = pacienteRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
    }

    /**
     * Lista todas as consultas registadas.
     *
     * @return lista de consultas
     */
    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    /**
     * Lista todas as especialidades médicas existentes.
     *
     * @return lista de especialidades
     */
    public List<Especialidade> listarEspecialidades() {
        return medicoRepository.listarEspecialidades();
    }

    /**
     * Lista as disponibilidades livres de uma determinada especialidade.
     *
     * @param especialidade especialidade pretendida
     * @return lista de disponibilidades
     */
    public List<Disponibilidade> listarDisponibilidadePorEspecialidade(Especialidade especialidade) {
        return disponibilidadeRepository
                .findByMedico_EspecialidadeAndOcupadaFalseOrderByDataAscHoraInicioAsc(especialidade);
    }

    /**
     * Marca uma consulta para um paciente numa disponibilidade existente.
     *
     * @param disponibilidadeId identificador da disponibilidade
     * @param pacienteId identificador do paciente
     * @param secretariaId identificador da secretária
     */
    public void marcarConsulta(Long disponibilidadeId,
                               Long pacienteId,
                               Long secretariaId) {

        // Procura a disponibilidade selecionada
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(disponibilidadeId)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada."));

        // Procura o paciente
        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> new RuntimeException("Paciente não encontrado."));

        // Procura a secretária
        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(() -> new RuntimeException("Secretária não encontrada."));

        // Cria a consulta
        Consulta consulta = new Consulta();

        consulta.setDisponibilidade(disponibilidade);
        consulta.setPaciente(paciente);
        consulta.setSecretaria(secretaria);
        consulta.setData(disponibilidade.getData());
        consulta.setHoraInicio(disponibilidade.getHoraInicio());
        consulta.setHoraFim(disponibilidade.getHoraFim());
        consulta.setEstado("MARCADA");

        // Guarda a consulta na base de dados
        consultaRepository.save(consulta);

        // Marca a disponibilidade como ocupada
        disponibilidade.setOcupada(true);

        // Atualiza a disponibilidade na base de dados
        disponibilidadeRepository.save(disponibilidade);
    }
}