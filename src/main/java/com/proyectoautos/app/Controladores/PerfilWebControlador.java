package com.proyectoautos.app.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PerfilWebControlador {
	 @GetMapping("/perfil")
	    public String mostrarLogin() {
	        return "perfil"; // archivo login.html en templates
	    }
	 
	 @GetMapping("/galeria")
	    public String galeria() {
	        return "galeria";
	    }
	 
	    @GetMapping("/historia")
	    public String historia() {
	        return "historia";
	    }
	    
	    @GetMapping("/nosotros")
	    public String nosotros() {
	        return "nosotros";
	    }
}
