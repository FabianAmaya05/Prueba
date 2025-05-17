package com.proyectogrado.app.Controladores;

import com.proyectogrado.app.Entidades.Estudiante;
import com.proyectogrado.app.Entidades.IdeasProyecto;
import com.proyectogrado.app.Repositorios.EstudianteRepositorio;
import com.proyectogrado.app.Repositorios.IdeasProyectoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/estudiante")
public class EstudianteWebControlador {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Autowired
    private IdeasProyectoRepositorio ideasProyectoRepositorio;

    // Mostrar proyectos disponibles
    @GetMapping("/{idEstudiante}/proyectos")
    public String verProyectosDisponibles(@PathVariable String idEstudiante, Model model) {
        List<IdeasProyecto> proyectosDisponibles = ideasProyectoRepositorio.findByEstudianteIsNull();
        model.addAttribute("proyectos", proyectosDisponibles);
        model.addAttribute("idEstudiante", idEstudiante);
        return "proyectos-disponibles"; // JSP o Thymeleaf
    }

    // Seleccionar proyecto
    @PostMapping("/{idEstudiante}/seleccionar/{idProyecto}")
    public String seleccionarProyecto(@PathVariable String idEstudiante, @PathVariable String idProyecto, Model model) {
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante).orElse(null);
        IdeasProyecto proyecto = ideasProyectoRepositorio.findById(idProyecto).orElse(null);

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
        List<IdeasProyecto> proyectos = ideasProyectoRepositorio.findByEstudiante_Id(idEstudiante);

        if (proyectos.isEmpty()) {
            model.addAttribute("mensaje", "Aún no has seleccionado un proyecto.");
            return "sin-proyecto";
        }

        model.addAttribute("proyecto", proyectos.get(0));
        return "mi-proyecto";
    }
    
    @GetMapping("/panelEstudiante")
    public String mostrarPanelEstudiante() {
        return "panelEstudiante"; // Vista HTML en templates/estudiante/panel.html
    }
    
    

}
