package com.proyectogrado.app.Controladores;

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
}
