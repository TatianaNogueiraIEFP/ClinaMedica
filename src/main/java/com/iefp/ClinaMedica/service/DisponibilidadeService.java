package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.classes.Especialidade;
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
 * de horários disponíveis para marcação de consultas.
 */
@Service
public class DisponibilidadeService {

    /**
     * Repositório das disponibilidades.
     */
    private final DisponibilidadeRepository disponibilidadeRepository;

    /**
     * Repositório dos médicos.
     */
    private final MedicoRepository medicoRepository;

    /**
     * Construtor com injeção de dependências.
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
     * Lista todas as disponibilidades existentes no sistema.
     *
     * @return lista de disponibilidades
     */
    public List<Disponibilidade> listarTodas() {
        return disponibilidadeRepository.findAll();
    }

    /**
     * Lista as disponibilidades livres associadas a uma especialidade médica.
     *
     * @param especialidade especialidade médica
     * @return lista de disponibilidades disponíveis
     */
    public List<Disponibilidade> listarPorEspecialidade(Especialidade especialidade) {
        return disponibilidadeRepository
                .findByMedico_EspecialidadeAndOcupadaFalseOrderByDataAscHoraInicioAsc(especialidade);
    }

    /**
     * Cria automaticamente disponibilidades de 1 hora para um médico,
     * num determinado dia, dentro do horário de trabalho definido.
     *
     * @param medicoId identificador do médico
     * @param data data em que as disponibilidades serão criadas
     * @param horaInicioTrabalho hora de início do turno
     * @param horaFimTrabalho hora de fim do turno
     */
    public void criarDisponibilidade(Long medicoId,
                                     LocalDate data,
                                     LocalTime horaInicioTrabalho,
                                     LocalTime horaFimTrabalho) {

        // Procura o médico pelo ID
        Medico medico = medicoRepository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Médico não encontrado."));

        // Validação: a hora de início deve ser anterior à hora de fim
        if (!horaInicioTrabalho.isBefore(horaFimTrabalho)) {
            throw new RuntimeException("A hora de início deve ser anterior à hora de fim.");
        }

        // Variável auxiliar para percorrer o horário
        LocalTime horaAtual = horaInicioTrabalho;

        // Cria blocos de 1 hora até ao fim do turno
        while (horaAtual.plusHours(1).isBefore(horaFimTrabalho)
                || horaAtual.plusHours(1).equals(horaFimTrabalho)) {

            LocalTime horaFimConsulta = horaAtual.plusHours(1);

            // Verifica se já existe esta disponibilidade
            boolean jaExiste = disponibilidadeRepository
                    .existsByMedico_IdAndDataAndHoraInicioAndHoraFim(
                            medicoId,
                            data,
                            horaAtual,
                            horaFimConsulta
                    );

            // Se não existir, cria nova disponibilidade
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

            // Avança para o próximo bloco horário
            horaAtual = horaAtual.plusHours(1);
        }
    }
}