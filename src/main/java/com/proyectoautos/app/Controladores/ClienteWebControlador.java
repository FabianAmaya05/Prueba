package com.proyectoautos.app.Controladores;

import com.proyectoautos.app.Entidades.Cliente;
import com.proyectoautos.app.Entidades.IdeasAuto;
import com.proyectoautos.app.Repositorios.EstudianteRepositorio;
import com.proyectoautos.app.Repositorios.IdeasProyectoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiante")
public class ClienteWebControlador {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Autowired
    private IdeasProyectoRepositorio ideasProyectoRepositorio;

    // Mostrar proyectos disponibles
    @GetMapping("/{idEstudiante}/proyectos")
    public String verProyectosDisponibles(@PathVariable String idEstudiante, Model model) {
        List<IdeasAuto> proyectosDisponibles = ideasProyectoRepositorio.findByEstudianteIsNull();
        model.addAttribute("proyectos", proyectosDisponibles);
        model.addAttribute("idEstudiante", idEstudiante);
        return "proyectos-disponibles"; // JSP o Thymeleaf
    }

    // Seleccionar proyecto
    @PostMapping("/{idEstudiante}/seleccionar/{idProyecto}")
    public String seleccionarProyecto(@PathVariable String idEstudiante, @PathVariable String idProyecto, Model model) {
        Cliente estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto).orElse(null);

        if (proyecto == null || estudiante == null || proyecto.getEstudiante() != null) {
            model.addAttribute("error", "Proyecto no disponible o datos inválidos.");
            return "error"; // error.jsp o error.html
        }

        proyecto.setEstudiante(estudiante);
        ideasProyectoRepositorio.save(proyecto);

        return "redirect:/estudiante/" + idEstudiante + "/proyecto";
    }

    // Ver proyecto asignado al estudiante
    @GetMapping("/{idEstudiante}/proyecto")
    public String verProyectoAsignado(@PathVariable String idEstudiante, Model model) {
        List<IdeasAuto> proyectos = ideasProyectoRepositorio.findByEstudiante_Id(idEstudiante);

        if (proyectos.isEmpty()) {
            model.addAttribute("mensaje", "Aún no has seleccionado un proyecto.");
            return "sin-proyecto";
        }

        model.addAttribute("proyecto", proyectos.get(0));
        return "mi-proyecto";
    }
    
    @GetMapping("/panelCliente")
    public String mostrarPanelEstudiante() {
        return "panelCliente"; // Vista HTML en templates/estudiante/panel.html
    }
    
    

}
