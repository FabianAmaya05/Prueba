package com.proyectoautos.app.Controladores;

import com.proyectoautos.app.Entidades.*;
import com.proyectoautos.app.Repositorios.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administrador")
public class AdministradorApiRest {

    @Autowired
    private AdministradorRepositorio administradorRepositorio;

    @Autowired
    private ProfesorRepositorio profesorRepositorio;

    @Autowired
    private CoordinadorRepositorio coordinadorRepositorio;

    @Autowired
    private EstudianteRepositorio estudianteRepositorio;

    @GetMapping
    public List<Administrador> obtenerAdministradores() {
        return administradorRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public Administrador obtenerAdministrador(@PathVariable String id) {
        return administradorRepositorio.findById(id).orElseThrow();
    }

    // ==================== PROFESORES ====================
    @PostMapping("/{id}/profesores")
    public Administrador agregarProfesor(@PathVariable String id, @RequestBody Empleado profesor) {
        Administrador admin = administradorRepositorio.findById(id).orElseThrow();
        profesorRepositorio.save(profesor);
        admin.getProfesores().add(profesor);
        return administradorRepositorio.save(admin);
    }

    @PutMapping("/profesor/{id}")
    public Empleado actualizarProfesor(@PathVariable String id, @RequestBody Empleado profesorActualizado) {
        Empleado profesor = profesorRepositorio.findById(id).orElseThrow();
        profesor.setNombre(profesorActualizado.getNombre());
        profesor.setApellidos(profesorActualizado.getApellidos());
        profesor.setUsuario(profesorActualizado.getUsuario());
        profesor.setCedula(profesorActualizado.getCedula());
        return profesorRepositorio.save(profesor);
    }

    @DeleteMapping("/profesor/{id}")
    public void eliminarProfesor(@PathVariable String id) {
        profesorRepositorio.deleteById(id);
    }

    // ==================== COORDINADORES ====================
    @PostMapping("/{id}/coordinadores")
    public Administrador agregarCoordinador(@PathVariable String id, @RequestBody Gerente coordinador) {
        Administrador admin = administradorRepositorio.findById(id).orElseThrow();
        coordinadorRepositorio.save(coordinador);
        admin.getCoordinadores().add(coordinador);
        return administradorRepositorio.save(admin);
    }

    @PutMapping("/coordinador/{id}")
    public Gerente actualizarCoordinador(@PathVariable String id, @RequestBody Gerente coordinadorActualizado) {
        Gerente coordinador = coordinadorRepositorio.findById(id).orElseThrow();
        coordinador.setNombres(coordinadorActualizado.getNombres());
        coordinador.setApellidos(coordinadorActualizado.getApellidos());
        coordinador.setUsuario(coordinadorActualizado.getUsuario());
        coordinador.setCedula(coordinadorActualizado.getCedula());
        return coordinadorRepositorio.save(coordinador);
    }

    @DeleteMapping("/coordinador/{id}")
    public void eliminarCoordinador(@PathVariable String id) {
        coordinadorRepositorio.deleteById(id);
    }

    // ==================== ESTUDIANTES ====================
    @PostMapping("/{id}/estudiantes")
    public Administrador agregarEstudiante(@PathVariable String id, @RequestBody Cliente estudiante) {
        Administrador admin = administradorRepositorio.findById(id).orElseThrow();
        estudianteRepositorio.save(estudiante);
        admin.getEstudiantes().add(estudiante);
        return administradorRepositorio.save(admin);
    }

    @PutMapping("/estudiante/{id}")
    public Cliente actualizarEstudiante(@PathVariable String id, @RequestBody Cliente estudianteActualizado) {
        Cliente estudiante = estudianteRepositorio.findById(id).orElseThrow();
        estudiante.setNombres(estudianteActualizado.getNombres());
        estudiante.setApellidos(estudianteActualizado.getApellidos());
        estudiante.setUsuario(estudianteActualizado.getUsuario());
        estudiante.setCedula(estudianteActualizado.getCedula());
        return estudianteRepositorio.save(estudiante);
    }

    @DeleteMapping("/estudiante/{id}")
    public void eliminarEstudiante(@PathVariable String id) {
        estudianteRepositorio.deleteById(id);
    }
}
