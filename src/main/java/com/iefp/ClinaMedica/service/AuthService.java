package com.iefp.ClinaMedica.service;

import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.repository.UtilizadorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Serviço responsável pela autenticação.
 */
@Service
public class AuthService {

    private final UtilizadorRepository utilizadorRepository;

    public AuthService(UtilizadorRepository utilizadorRepository) {
        this.utilizadorRepository = utilizadorRepository;
    }

    /**
     * Valida login do utilizador.
     */
    public Utilizador login(String email, String senha) {

        Optional<Utilizador> user = utilizadorRepository.findByEmail(email);

        if (user.isEmpty()) {
            throw new RuntimeException("Utilizador não encontrado");
        }

        Utilizador utilizador = user.get();

        if (!utilizador.getSenha().equals(senha)) {
            throw new RuntimeException("Password incorreta");
        }

        return utilizador;
    }
}