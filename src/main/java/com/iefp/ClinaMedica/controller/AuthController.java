package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Utilizador;
import com.iefp.ClinaMedica.service.AuthService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticação.
 */
@Controller
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Página de login.
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    /**
     * Processa login.
     */
    @PostMapping("/login")
    public String login(@RequestParam String email,
                        @RequestParam String senha,
                        HttpSession session,
                        Model model) {

        try {
            Utilizador user = authService.login(email, senha);

            // guardar utilizador na sessão
            session.setAttribute("user", user);

            // redirecionar por perfil
            switch (user.getPerfil()) {

                case MEDICO:
                    return "redirect:/medicos";

                case PACIENTE:
                    return "redirect:/pacientes";

                case SECRETARIA:
                    return "redirect:/consultas";

                default:
                    return "redirect:/utilizadores";
            }

        } catch (RuntimeException e) {
            model.addAttribute("erro", e.getMessage());
            return "login";
        }
    }

    /**
     * Logout.
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}