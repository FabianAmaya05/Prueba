package com.proyectoautos.app.Controladores;

import com.proyectoautos.app.Entidades.Empleado;
import com.proyectoautos.app.Entidades.IdeasAuto;
import com.proyectoautos.app.Repositorios.IdeasProyectoRepositorio;
import com.proyectoautos.app.Repositorios.ProfesorRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/profesor")
@CrossOrigin(origins = "*")
public class EmpleadoApiRest {

    @Autowired
    private ProfesorRepositorio profesorRepo;

    @Autowired
    private IdeasProyectoRepositorio proyectoRepo;

    // 🔹 Ver proyectos asignados al docente (director o evaluador)
    @GetMapping("/proyectos/{idProfesor}")
    public List<IdeasAuto> verProyectos(@PathVariable String idProfesor) {
        return proyectoRepo.findByDirectorIdOrEvaluadorId(idProfesor, idProfesor);
    }

    // 🔹 Evaluar un proyecto como director o evaluador
    @PostMapping("/evaluar/{idProyecto}")
    public String evaluarProyecto(@PathVariable String idProyecto, @RequestParam String nota, @RequestParam String evaluacion, @RequestParam boolean esDirector) {
        Optional<IdeasAuto> proyectoOpt = proyectoRepo.findById(idProyecto);
        if (!proyectoOpt.isPresent()) {
            return "Proyecto no encontrado.";
        }

        IdeasAuto proyecto = proyectoOpt.get();

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
    public Empleado verProfesor(@PathVariable String idProfesor) {
        return profesorRepo.findById(idProfesor).orElse(null);
    }
}
