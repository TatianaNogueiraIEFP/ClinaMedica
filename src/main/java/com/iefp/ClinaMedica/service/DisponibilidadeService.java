package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Disponibilidade;
import com.iefp.ClinaMedica.model.Medico;
import com.iefp.ClinaMedica.repository.DisponibilidadeRepository;
import com.iefp.ClinaMedica.repository.MedicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * Serviço responsável pela gestão das disponibilidades dos médicos.
 * Contém a lógica de negócio relacionada com a criação e consulta
 * de horários disponíveis.
 */
@Service
public class DisponibilidadeService {

    private final DisponibilidadeRepository disponibilidadeRepository;
    private final MedicoRepository medicoRepository;

    /**
     * Construtor utilizado para a injeção das dependências do serviço.
     *
     * @param disponibilidadeRepository repositório das disponibilidades
     * @param medicoRepository repositório dos médicos
     */
    public DisponibilidadeService(
            DisponibilidadeRepository disponibilidadeRepository,
            MedicoRepository medicoRepository) {

        this.disponibilidadeRepository = disponibilidadeRepository;
        this.medicoRepository = medicoRepository;
    }

    /**
     * Devolve todas as disponibilidades registadas.
     *
     * @return lista de disponibilidades
     */
    public List<Disponibilidade> listarTodas() {
        return disponibilidadeRepository.findAll();
    }

    /**
     * Devolve as disponibilidades livres de uma determinada especialidade.
     *
     * @param especialidade especialidade médica
     * @return lista de disponibilidades disponíveis
     */
    public List<Disponibilidade> listarPorEspecialidade(String especialidade) {
        return disponibilidadeRepository
                .findByMedico_EspecialidadeIgnoreCaseAndOcupadaFalseOrderByDataAscHoraInicioAsc(
                        especialidade);
    }

    /**
     * Cria automaticamente disponibilidades de uma hora para um médico
     * num determinado dia, entre a hora de início e a hora de fim do turno.
     *
     * @param medicoId identificador do médico
     * @param data data das disponibilidades
     * @param horaInicioTrabalho hora de início do período de trabalho
     * @param horaFimTrabalho hora de fim do período de trabalho
     */
    public void criarDisponibilidade(Long medicoId,
                                     LocalDate data,
                                     LocalTime horaInicioTrabalho,
                                     LocalTime horaFimTrabalho) {

        // Procura o médico pelo identificador.
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado."));

        // Verifica se as horas são válidas.
        if (!horaInicioTrabalho.isBefore(horaFimTrabalho)) {
            throw new RuntimeException(
                    "A hora de início deve ser anterior à hora de fim.");
        }

        // Hora atual utilizada para percorrer o horário.
        LocalTime horaAtual = horaInicioTrabalho;

        // Cria períodos de uma hora.
        while (horaAtual.plusHours(1).isBefore(horaFimTrabalho)
                || horaAtual.plusHours(1).equals(horaFimTrabalho)) {

            LocalTime horaFimConsulta = horaAtual.plusHours(1);

            // Verifica se já existe esta disponibilidade.
            boolean jaExiste =
                    disponibilidadeRepository
                            .existsByMedico_IdAndDataAndHoraInicioAndHoraFim(
                                    medicoId,
                                    data,
                                    horaAtual,
                                    horaFimConsulta);

            // Guarda apenas se ainda não existir.
            if (!jaExiste) {
                Disponibilidade disponibilidade = new Disponibilidade(
                        null,
                        medico,
                        data,
                        horaAtual,
                        horaFimConsulta,
                        false
                );

                disponibilidadeRepository.save(disponibilidade);
            }

            // Avança para a próxima hora.
            horaAtual = horaAtual.plusHours(1);
        }
    }
}