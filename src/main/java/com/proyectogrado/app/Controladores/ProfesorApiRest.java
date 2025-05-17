package com.proyectogrado.app.Controladores;

import com.proyectogrado.app.Entidades.Profesor;
import com.proyectogrado.app.Entidades.IdeasProyecto;
import com.proyectogrado.app.Repositorios.ProfesorRepositorio;
import com.proyectogrado.app.Repositorios.IdeasProyectoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesor")
@CrossOrigin(origins = "*")
public class ProfesorApiRest {

    @Autowired
    private ProfesorRepositorio profesorRepo;

    @Autowired
    private IdeasProyectoRepositorio proyectoRepo;

    // 🔹 Ver proyectos asignados al docente (director o evaluador)
    @GetMapping("/proyectos/{idProfesor}")
    public List<IdeasProyecto> verProyectos(@PathVariable String idProfesor) {
        return proyectoRepo.findByDirectorIdOrEvaluadorId(idProfesor, idProfesor);
    }

    // 🔹 Evaluar un proyecto como director o evaluador
    @PostMapping("/evaluar/{idProyecto}")
    public String evaluarProyecto(@PathVariable String idProyecto, @RequestParam String nota, @RequestParam String evaluacion, @RequestParam boolean esDirector) {
        Optional<IdeasProyecto> proyectoOpt = proyectoRepo.findById(idProyecto);
        if (!proyectoOpt.isPresent()) {
            return "Proyecto no encontrado.";
        }

        IdeasProyecto proyecto = proyectoOpt.get();

        if (esDirector) {
            proyecto.setEvaluacionDirector(evaluacion);
            proyecto.setCalificadirector(nota);
        } else {
            proyecto.setEvaluacionEvaluador(evaluacion);
            proyecto.setCalificaevaluador(nota);
        }

        proyectoRepo.save(proyecto);
        return "Evaluación guardada correctamente.";
    }

    // 🔹 Ver detalles de un docente (por ejemplo, si es director o evaluador)
    @GetMapping("/{idProfesor}")
    public Profesor verProfesor(@PathVariable String idProfesor) {
        return profesorRepo.findById(idProfesor).orElse(null);
    }
}
