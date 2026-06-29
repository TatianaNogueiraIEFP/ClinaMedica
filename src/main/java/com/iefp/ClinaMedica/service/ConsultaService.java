package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Consulta;
import com.iefp.ClinaMedica.model.Disponibilidade;
import com.iefp.ClinaMedica.model.Paciente;
import com.iefp.ClinaMedica.model.Secretaria;
import com.iefp.ClinaMedica.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {
    private final ConsultaRepository consultaRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final PacienteRepository pacienteRepository;
    private final SecretariaRepository secretariaRepository;
    private final MedicoRepository medicoRepository;

    public ConsultaService(ConsultaRepository consultaRepository, DisponibilidadeRepository disponibilidadeRepository, PacienteRepository pacienteRepository, SecretariaRepository secretariaRepository, MedicoRepository medicoRepository) {
        this.consultaRepository = consultaRepository;
        this.disponibilidadeRepository = disponibilidadeRepository;
        this.pacienteRepository = pacienteRepository;
        this.secretariaRepository = secretariaRepository;
        this.medicoRepository = medicoRepository;
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public List<String> listarEspecialidades() {
        return medicoRepository.listarEspecialidades();
    }

    public List<Disponibilidade> listarDisponibilidadePorEspecialidade(String especialidade) {
        return disponibilidadeRepository
                .findByMedico_EspecialidadeIgnoreCaseAndOcupadaFalseOrderByDataAscHoraInicioAsc(especialidade);
    }

    public void marcarConsulta(Long disponibilidadeId,
                               Long pacienteId,
                               Long secretariaId) {
        Disponibilidade disponibilidade = disponibilidadeRepository.findById(disponibilidadeId)
                .orElseThrow(() -> new RuntimeException("Disponibilidade não encontrada."));

        Paciente paciente = pacienteRepository.findById(pacienteId)
                .orElseThrow(()-> new RuntimeException("Paciente não encontrado"));

        Secretaria secretaria = secretariaRepository.findById(secretariaId)
                .orElseThrow(()->new RuntimeException("Secretária não encontrada"));

        Consulta consulta = new Consulta(
                null,
                disponibilidade,
                paciente,
                secretaria,
                disponibilidade.getData(),
                disponibilidade.getHoraInicio(),
                disponibilidade.getHoraFim(),
                "MARCADA"
        );

        consultaRepository.save(consulta);

        disponibilidade.setOcupada(true);
        disponibilidadeRepository.save(disponibilidade);

    }
}
