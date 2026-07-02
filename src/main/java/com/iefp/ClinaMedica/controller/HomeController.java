package com.iefp.ClinaMedica.controller;

import com.iefp.ClinaMedica.model.Utilizador;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controlador responsável pela página inicial (Home).
 */
@Controller
public class HomeController {

    /**
     * Apresenta a página inicial da aplicação.
     *
     * Verifica se existe um utilizador autenticado na sessão.
     * Caso não exista, o utilizador é redirecionado para a página de login.
     *
     * @param session sessão HTTP da aplicação.
     * @return página inicial ou redirecionamento para o login.
     */
    @GetMapping("/")
    public String home(HttpSession session) {

        // Verificar se existe um utilizador autenticado
        if (session.getAttribute("utilizadorLogado") == null) {
            return "redirect:/login";
        }

        // Apresentar a página inicial
        return "redirect:/home";
    }

    /**
     * Apresenta a página inicial da aplicação.
     *
     * Verifica se existe um utilizador autenticado na sessão.
     * Caso não exista, o utilizador é redirecionado para a página de login.
     * Caso exista, os seus dados são enviados para a vista.
     *
     * @param session sessão HTTP da aplicação.
     * @param model modelo utilizado para enviar dados para a vista.
     * @return página inicial ou redirecionamento para o login.
     */
    @GetMapping("/home")
    public String mostrarHome(HttpSession session, Model model) {

        // Obter o utilizador autenticado da sessão
        Utilizador utilizadorLogado =
                (Utilizador) session.getAttribute("utilizadorLogado");

        // Caso não exista um utilizador autenticado,
        // redirecionar para a página de login
        if (utilizadorLogado == null) {
            return "redirect:/login";
        }

        // Adicionar o utilizador autenticado ao modelo
        model.addAttribute("utilizador", utilizadorLogado);

        // Apresentar a página inicial
        return "home";
    }
}