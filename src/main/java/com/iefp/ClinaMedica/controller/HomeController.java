package com.iefp.ClinaMedica.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável pela página inicial (Home/Dashboard).
 */
@Controller
public class HomeController {

    /**
     * Página inicial da aplicação.
     */
    @GetMapping("/")
    public String home(HttpSession session) {

        // Se não houver utilizador autenticado, vai para login
        if (session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        // Caso contrário, mostra o dashboard
        return "home";
    }
}