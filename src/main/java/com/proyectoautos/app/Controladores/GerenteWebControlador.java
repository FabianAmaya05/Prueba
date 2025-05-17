package com.proyectoautos.app.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.proyectoautos.app.Entidades.Empleado;
import com.proyectoautos.app.Entidades.Gerente;
import com.proyectoautos.app.Entidades.IdeasAuto;
import com.proyectoautos.app.Repositorios.CoordinadorRepositorio;
import com.proyectoautos.app.Repositorios.IdeasProyectoRepositorio;
import com.proyectoautos.app.Repositorios.ProfesorRepositorio;

@Controller
@RequestMapping("/coordinador")
public class GerenteWebControlador {

    @Autowired
    private CoordinadorRepositorio coordinadorRepositorio;

    @Autowired
    private IdeasProyectoRepositorio ideasProyectoRepositorio;

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    // Mostrar los proyectos de un coordinador
    @GetMapping("/{id}/proyectos")
    public String obtenerProyectos(@PathVariable String id, Model model) {
        Gerente coordinador = coordinadorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinador no encontrado"));
        model.addAttribute("coordinador", coordinador);
        model.addAttribute("proyectos", coordinador.getProyectos());
        return "proyectos"; // Nombre de la vista (HTML o Thymeleaf)
    }

    // Crear un nuevo proyecto
    @GetMapping("/{id}/proyectos/crear")
    public String mostrarFormularioCrearProyecto(@PathVariable String id, Model model) {
        Gerente coordinador = coordinadorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinador no encontrado"));
        model.addAttribute("coordinador", coordinador);
        model.addAttribute("proyecto", new IdeasAuto());
        return "crearProyecto"; // Vista para crear un proyecto
    }

    @PostMapping("/{id}/proyectos")
    public String crearProyecto(@PathVariable String id, @ModelAttribute IdeasAuto proyecto) {
        Gerente coordinador = coordinadorRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinador no encontrado"));
        proyecto.setFechaCreacion(new java.sql.Date(System.currentTimeMillis()));
        proyecto.setEvaluacionDirector("");
        proyecto.setEvaluacionEvaluador("");
        proyecto.setEvaluacionCoordinador("");
        proyecto.setCoordinador(coordinador);
        ideasProyectoRepositorio.save(proyecto);

        coordinador.getProyectos().add(proyecto);
        coordinadorRepositorio.save(coordinador);
        return "redirect:/coordinador/" + id + "/proyectos";
    }

    // Editar un proyecto
    @GetMapping("/proyecto/{idProyecto}/editar")
    public String mostrarFormularioEditarProyecto(@PathVariable String idProyecto, Model model) {
        IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        model.addAttribute("proyecto", proyecto);
        return "editarProyecto"; // Vista para editar un proyecto
    }

    @PostMapping("/proyecto/{idProyecto}/editar")
    public String editarProyecto(@PathVariable String idProyecto, @ModelAttribute IdeasAuto datosActualizados) {
        IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        proyecto.setTitulo(datosActualizados.getTitulo());
        proyecto.setDescripcion(datosActualizados.getDescripcion());
        proyecto.setFechaLimite(datosActualizados.getFechaLimite());
        proyecto.setEstado(datosActualizados.getEstado());
        ideasProyectoRepositorio.save(proyecto);
        return "redirect:/coordinador/" + proyecto.getCoordinador().getId() + "/proyectos";
    }

    // Eliminar un proyecto
    @GetMapping("/proyecto/{idProyecto}/eliminar")
    public String eliminarProyecto(@PathVariable String idProyecto) {
        IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        Gerente coordinador = proyecto.getCoordinador();
        if (coordinador != null) {
            coordinador.getProyectos().remove(proyecto);
            coordinadorRepositorio.save(coordinador);
        }

        ideasProyectoRepositorio.deleteById(idProyecto);
        return "redirect:/coordinador/" + coordinador.getId() + "/proyectos";
    }

    // Asignar director y evaluador
    @GetMapping("/proyecto/{idProyecto}/asignar")
    public String mostrarFormularioAsignarProfesores(@PathVariable String idProyecto, Model model) {
        IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));
        model.addAttribute("proyecto", proyecto);
        model.addAttribute("profesores", profesorRepositorio.findAll()); // Listar todos los profesores
        return "asignarProfesores"; // Vista para asignar director y evaluador
    }

    @PostMapping("/proyecto/{idProyecto}/asignar")
    public String asignarProfesores(@PathVariable String idProyecto, @RequestParam String idDirector,
                                    @RequestParam String idEvaluador) {

        if (idDirector.equals(idEvaluador)) {
            throw new IllegalArgumentException("Un profesor no puede ser director y evaluador al mismo tiempo.");
        }

        Empleado director = profesorRepositorio.findById(idDirector)
                .orElseThrow(() -> new RuntimeException("Director no encontrado"));
        Empleado evaluador = profesorRepositorio.findById(idEvaluador)
                .orElseThrow(() -> new RuntimeException("Evaluador no encontrado"));

        IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        proyecto.setDirector(director);
        proyecto.setEvaluador(evaluador);

        director.setDirector(true);
        evaluador.setEvaluador(true);

        profesorRepositorio.save(director);
        profesorRepositorio.save(evaluador);

        ideasProyectoRepositorio.save(proyecto);
        return "redirect:/coordinador/" + proyecto.getCoordinador().getId() + "/proyectos";
    }

    // Aprobar proyecto final
    @GetMapping("/proyecto/{idProyecto}/aprobar-final")
    public String aprobarProyectoFinal(@PathVariable String idProyecto) {
        IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
                .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

        if ("Aprobado".equals(proyecto.getCalificadirector()) && "Aprobado".equals(proyecto.getCalificaevaluador())) {
            proyecto.setEstado("Aprobado final");
            ideasProyectoRepositorio.save(proyecto);
            return "redirect:/coordinador/" + proyecto.getCoordinador().getId() + "/proyectos";
        } else {
            throw new IllegalStateException("El director y el evaluador deben aprobar primero.");
        }
    }
    
	
    @GetMapping("/panelGerente")
    public String mostrarPanelCoordinador() {
        return "panelGerente"; // Asumiendo que usas Thymeleaf o JSP
    }

}
