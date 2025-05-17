package com.proyectogrado.app.Controladores;

import com.proyectogrado.app.Entidades.IdeasProyecto;
import com.proyectogrado.app.Entidades.Profesor;
import com.proyectogrado.app.Repositorios.ProfesorRepositorio;
import com.proyectogrado.app.Repositorios.IdeasProyectoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profesor")
public class ProfesorWebControlador {

    @Autowired
    private ProfesorRepositorio profesorRepo;

    @Autowired
    private IdeasProyectoRepositorio proyectoRepo;

    // 🔹 Ver proyectos asignados al docente (director o evaluador)
    @GetMapping("/proyectos/{idProfesor}")
    public String verProyectos(@PathVariable String idProfesor, Model model) {
        Profesor profesor = profesorRepo.findById(idProfesor).orElse(null);
        
        if (profesor == null) {
            model.addAttribute("error", "Profesor no encontrado.");
            return "error";
        }

        List<IdeasProyecto> proyectos = proyectoRepo.findByDirectorIdOrEvaluadorId(idProfesor, idProfesor);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("profesor", profesor);
        return "profesor/proyectos";
    }

    // 🔹 Evaluar un proyecto como director o evaluador
    @PostMapping("/evaluar/{idProyecto}")
    public String evaluarProyecto(@PathVariable String idProyecto, @RequestParam String nota, @RequestParam String evaluacion, @RequestParam boolean esDirector, Model model) {
        IdeasProyecto proyecto = proyectoRepo.findById(idProyecto).orElse(null);
        Profesor profesor = profesorRepo.findById(idProyecto).orElse(null);
        
        if (proyecto == null || profesor == null) {
            model.addAttribute("error", "Proyecto o Profesor no encontrado.");
            return "error";
        }

        if (esDirector) {
            proyecto.setEvaluacionDirector(evaluacion);
            proyecto.setCalificadirector(nota);
        } else {
            proyecto.setEvaluacionEvaluador(evaluacion);
            proyecto.setCalificaevaluador(nota);
        }

        proyectoRepo.save(proyecto);
        model.addAttribute("mensaje", "Evaluación guardada correctamente.");
        return "profesor/evaluaciones";
    }
    
    @GetMapping("/panelProfesores")
    public String mostrarPanelDocente() {
        return "panelProfesores"; // Vista HTML en templates/docente/panel.html
    }
}
