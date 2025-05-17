package com.proyectogrado.app.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.proyectogrado.app.Entidades.Estudiante;
import com.proyectogrado.app.Entidades.IdeasProyecto;
import com.proyectogrado.app.Repositorios.EstudianteRepositorio;
import com.proyectogrado.app.Repositorios.IdeasProyectoRepositorio;

@RestController
@RequestMapping("/api/estudiante")
public class EstudianteApiRest {

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @Autowired
    private IdeasProyectoRepositorio ideasProyectoRepositorio;

    // Ver proyectos disponibles (aquellos que no tengan estudiante asignado)
    @GetMapping("/proyectos-disponibles")
    public List<IdeasProyecto> obtenerProyectosDisponibles() {
        return ideasProyectoRepositorio.findByEstudianteIsNull();  // Devuelve solo los proyectos sin estudiante asignado
    }

    // Estudiante selecciona un proyecto
    @PostMapping("/{idEstudiante}/seleccionar-proyecto/{idProyecto}")
    public IdeasProyecto seleccionarProyecto(@PathVariable String idEstudiante, @PathVariable String idProyecto) {
        Estudiante estudiante = estudianteRepositorio.findById(idEstudiante)
                .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

        IdeasProyecto proyecto = ideasProyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        if (proyecto.getEstudiante() != null) {
            throw new IllegalStateException("Este proyecto ya fue asignado a otro estudiante.");
        }

        // Asignar el proyecto al estudiante
        proyecto.setEstudiante(estudiante);

        return ideasProyectoRepositorio.save(proyecto);
    }

    // Ver proyecto asignado al estudiante
    @GetMapping("/{idEstudiante}/proyecto")
    public IdeasProyecto verProyectoAsignado(@PathVariable String idEstudiante) {
        List<IdeasProyecto> proyectos = ideasProyectoRepositorio.findByEstudiante_Id(idEstudiante);

        if (proyectos.isEmpty()) {
            throw new RuntimeException("El estudiante no tiene un proyecto asignado.");
        }

        return proyectos.get(0);  // Se asume que un estudiante solo tiene un proyecto
    }
}
