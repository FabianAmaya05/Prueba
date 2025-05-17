package com.proyectoautos.app.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyectoautos.app.Entidades.Empleado;
import com.proyectoautos.app.Entidades.Gerente;
import com.proyectoautos.app.Entidades.IdeasAuto;
import com.proyectoautos.app.Repositorios.CoordinadorRepositorio;
import com.proyectoautos.app.Repositorios.IdeasProyectoRepositorio;
import com.proyectoautos.app.Repositorios.ProfesorRepositorio;

@RestController
@RequestMapping("/api/coordinador")
public class GerenteApiRest {

	@Autowired
	private CoordinadorRepositorio coordinadorRepositorio;

	@Autowired
	private IdeasProyectoRepositorio ideasProyectoRepositorio;

	@Autowired
	private ProfesorRepositorio profesorRepositorio;

	// Obtener todos los proyectos de un coordinador
	@GetMapping("/{id}/proyectos")
	public List<IdeasAuto> obtenerProyectos(@PathVariable String id) {
		Gerente coordinador = coordinadorRepositorio.findById(id)
				.orElseThrow(() -> new RuntimeException("Coordinador no encontrado"));
		return coordinador.getProyectos();
	}

	@PostMapping("/{id}/proyectos")
	public Gerente crearProyecto(@PathVariable String id, @RequestBody IdeasAuto proyecto) {
	    Gerente coordinador = coordinadorRepositorio.findById(id)
	            .orElseThrow(() -> new RuntimeException("Coordinador no encontrado"));

	    proyecto.setFechaCreacion(new java.sql.Date(System.currentTimeMillis()));
	    
	    // No asignamos un valor predeterminado a las evaluaciones
	    // Se mantienen vacíos hasta que se actualicen después
	    proyecto.setEvaluacionDirector("");  
	    proyecto.setEvaluacionEvaluador("");
	    proyecto.setEvaluacionCoordinador("");
	    
	    proyecto.setCoordinador(coordinador);

	    ideasProyectoRepositorio.save(proyecto);

	    coordinador.getProyectos().add(proyecto);
	    return coordinadorRepositorio.save(coordinador);
	}


	// Editar un proyecto
	@PutMapping("/proyecto/{idProyecto}")
	public IdeasAuto editarProyecto(@PathVariable String idProyecto, @RequestBody IdeasAuto datosActualizados) {
		IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
				.orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

		proyecto.setTitulo(datosActualizados.getTitulo());
		proyecto.setDescripcion(datosActualizados.getDescripcion());
		proyecto.setFechaLimite(datosActualizados.getFechaLimite());
		proyecto.setEstado(datosActualizados.getEstado());
		// Puedes agregar más campos aquí si lo deseas

		return ideasProyectoRepositorio.save(proyecto);
	}

	// Eliminar un proyecto
	@DeleteMapping("/proyecto/{idProyecto}")
	public void eliminarProyecto(@PathVariable String idProyecto) {
		IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
				.orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

		// Remover referencia del coordinador
		Gerente coordinador = proyecto.getCoordinador();
		if (coordinador != null) {
			coordinador.getProyectos().remove(proyecto);
			coordinadorRepositorio.save(coordinador);
		}

		ideasProyectoRepositorio.deleteById(idProyecto);
	}

	// Asignar director y evaluador
	@PutMapping("/proyecto/{idProyecto}/asignar")
	public IdeasAuto asignarProfesores(@PathVariable String idProyecto, @RequestParam String idDirector,
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

		// Marcar los roles
		director.setDirector(true);
		evaluador.setEvaluador(true);

		profesorRepositorio.save(director);
		profesorRepositorio.save(evaluador);

		return ideasProyectoRepositorio.save(proyecto);
	}

	@PutMapping("/proyecto/{idProyecto}/aprobar-final")
	public IdeasAuto aprobarProyectoFinal(@PathVariable String idProyecto) {
	    // Buscar el proyecto en la base de datos
	    IdeasAuto proyecto = ideasProyectoRepositorio.findById(idProyecto)
	            .orElseThrow(() -> new RuntimeException("Proyecto no encontrado"));

	    // Verificamos si tanto el director como el evaluador aprobaron el proyecto
	    if ("Aprobado".equals(proyecto.getCalificadirector()) && "Aprobado".equals(proyecto.getCalificaevaluador())) {
	        // Si ambos aprobaron, se marca como aprobado final
	        proyecto.setEstado("Aprobado final");
	        return ideasProyectoRepositorio.save(proyecto);
	    } else {
	        // Si no, se lanza un error
	        throw new IllegalStateException("El director y el evaluador deben aprobar primero.");
	    }
	}


}
