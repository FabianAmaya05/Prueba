package com.proyectoautos.app.Controladores;

import com.proyectoautos.app.Entidades.Empleado;
import com.proyectoautos.app.Entidades.IdeasAuto;
import com.proyectoautos.app.Repositorios.IdeasProyectoRepositorio;
import com.proyectoautos.app.Repositorios.ProfesorRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/profesor")
public class EmpleadoWebControlador {

    @Autowired
    private ProfesorRepositorio profesorRepo;

    @Autowired
    private IdeasProyectoRepositorio proyectoRepo;

    // 🔹 Ver proyectos asignados al docente (director o evaluador)
    @GetMapping("/proyectos/{idProfesor}")
    public String verProyectos(@PathVariable String idProfesor, Model model) {
        Empleado profesor = profesorRepo.findById(idProfesor).orElse(null);
        
        if (profesor == null) {
            model.addAttribute("error", "Profesor no encontrado.");
            return "error";
        }

        List<IdeasAuto> proyectos = proyectoRepo.findByDirectorIdOrEvaluadorId(idProfesor, idProfesor);
        model.addAttribute("proyectos", proyectos);
        model.addAttribute("profesor", profesor);
        return "profesor/proyectos";
    }

    // 🔹 Evaluar un proyecto como director o evaluador
    @PostMapping("/evaluar/{idProyecto}")
    public String evaluarProyecto(@PathVariable String idProyecto, @RequestParam String nota, @RequestParam String evaluacion, @RequestParam boolean esDirector, Model model) {
        IdeasAuto proyecto = proyectoRepo.findById(idProyecto).orElse(null);
        Empleado profesor = profesorRepo.findById(idProyecto).orElse(null);
        
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
    

    
    @GetMapping("/panelEmpleado")
    public String mostrarPanelEmpleado() {
        return "panelEmpleado"; // Vista HTML en templates/docente/panel.html
    }
}
