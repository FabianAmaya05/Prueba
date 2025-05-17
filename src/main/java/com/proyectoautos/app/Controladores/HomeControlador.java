package com.proyectoautos.app.Controladores;

import org.springframework.web.bind.annotation.GetMapping;



public class HomeControlador {

	@GetMapping("/")
    public String home() {
        return "index"; 
    }
	

    @GetMapping("/login")
    public String login() {
        return "login"; // Asegúrate de tener un archivo login.html o login.jsp si lo usas
    }
    
    @GetMapping("/perfil")
    public String perfil() {
        return "perfil"; // Este busca perfil.html en templates
    }



    @GetMapping("/galeria")
    public String galeria() {
        return "galeria";
    }

    @GetMapping("/nosotros")
    public String nosotros() {
        return "nosotros";
    }
}
