package com.proyectogrado.app.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectogrado.app.Entidades.Administrador;
import com.proyectogrado.app.Entidades.Coordinador;
import com.proyectogrado.app.Entidades.Estudiante;
import com.proyectogrado.app.Entidades.Profesor;
import com.proyectogrado.app.Repositorios.AdministradorRepositorio;
import com.proyectogrado.app.Repositorios.CoordinadorRepositorio;
import com.proyectogrado.app.Repositorios.EstudianteRepositorio;
import com.proyectogrado.app.Repositorios.ProfesorRepositorio;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginControlador {

    @Autowired
    private AdministradorRepositorio administradorRepo;
    @Autowired
    private ProfesorRepositorio profesorRepo;
    @Autowired
    private EstudianteRepositorio estudianteRepo;
    @Autowired
    private CoordinadorRepositorio coordinadorRepo;

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // archivo login.html en templates
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String usuario, @RequestParam String password, HttpSession session, Model model) {
        // Primero, intenta obtener al usuario de cada repositorio según el rol
        Administrador admin = administradorRepo.findByUsuarioAndPassword(usuario, password);
        Profesor profesor = profesorRepo.findByUsuarioAndPassword(usuario, password);
        Estudiante estudiante = estudianteRepo.findByUsuarioAndPassword(usuario, password);
        Coordinador coordinador = coordinadorRepo.findByUsuarioAndPassword(usuario, password);

        // Si se encuentra un usuario en alguna de las entidades
        if (admin != null) {
            session.setAttribute("usuarioLogueado", admin);
            return "redirect:/panelAdministrador";
        } else if (profesor != null) {
            session.setAttribute("usuarioLogueado", profesor);
            return "redirect:/profesor/panelProfesor";
        } else if (estudiante != null) {
            session.setAttribute("usuarioLogueado", estudiante);
            return "redirect:/estudiante/panelEstudiante";
        } else if (coordinador != null) {
            session.setAttribute("usuarioLogueado", coordinador);
            return "redirect:/coordinador/panelCoordinador";
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
    
    @GetMapping("/regresar")
    public String regresarLogin(HttpSession session) {
        session.invalidate(); // Cierra la sesión
        return "redirect:/login"; // Redirige al login
    }
    
    
}
