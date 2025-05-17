package com.proyectogrado.app.Controladores;

import java.util.List;
import java.util.Optional;

import com.proyectogrado.app.Entidades.Estudiante;
import com.proyectogrado.app.Entidades.IdeasProyecto;
import com.proyectogrado.app.Repositorios.IdeasProyectoRepositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/proyectos")
@CrossOrigin(origins = "*") // Habilita peticiones desde cualquier origen
public class IdeasProyectoApiRest {

	@Autowired
	private IdeasProyectoRepositorio repo;

	// 🔹 Ver todos los proyectos
	@GetMapping
	public List<IdeasProyecto> listarTodos() {
		return repo.findAll();
	}

	// 🔹 Crear nuevo proyecto (solo coordinador)
	@PostMapping("/nuevo")
	public IdeasProyecto crearProyecto(@RequestBody IdeasProyecto proyecto) {
		return repo.save(proyecto);
	}

	// 🔹 Estudiante ve proyectos disponibles para escoger
	@GetMapping("/disponibles")
	public List<IdeasProyecto> proyectosDisponibles() {
		return repo.findByEstudianteIsNull();
	}

	// 🔹 Estudiante escoge un proyecto
	@PostMapping("/escoger/{idProyecto}")
	public String escogerProyecto(@PathVariable String idProyecto, @RequestParam String estudianteId) {
		Optional<IdeasProyecto> opt = repo.findById(idProyecto);
		if (opt.isPresent()) {
			IdeasProyecto proyecto = opt.get();
			if (proyecto.getEstudiante() == null) {
				Estudiante estudiante = new Estudiante();
				estudiante.setId(estudianteId);
				proyecto.setEstudiante(estudiante);
				repo.save(proyecto);
				return "Proyecto asignado correctamente.";
			} else {
				return "El proyecto ya fue asignado a otro estudiante.";
			}
		}
		return "Proyecto no encontrado.";
	}

	// 🔹 Estudiante ve su proyecto
	@GetMapping("/estudiante/{idEstudiante}")
	public List<IdeasProyecto> verProyectoEstudiante(@PathVariable String idEstudiante) {
		return repo.findByEstudiante_Id(idEstudiante);
	}

	// 🔹 Docente ve sus proyectos (director o evaluador)
	@GetMapping("/docente/{idDocente}")
	public List<IdeasProyecto> verProyectosDocente(@PathVariable String idDocente) {
		return repo.findByDirectorIdOrEvaluadorId(idDocente, idDocente);
	}

	// 🔹 Director califica
	@PostMapping("/calificar-director/{idProyecto}")
	public String calificarDirector(@PathVariable String idProyecto, @RequestParam String nota,
			@RequestParam String evaluacion) {
		Optional<IdeasProyecto> opt = repo.findById(idProyecto);
		if (opt.isPresent()) {
			IdeasProyecto proyecto = opt.get();
			proyecto.setCalificadirector(nota);
			proyecto.setEvaluacionDirector(evaluacion);
			repo.save(proyecto);
			return "Evaluación del director guardada.";
		}
		return "Proyecto no encontrado.";
	}

	// 🔹 Evaluador califica
	@PostMapping("/calificar-evaluador/{idProyecto}")
	public String calificarEvaluador(@PathVariable String idProyecto, @RequestParam String nota,
			@RequestParam String evaluacion) {
		Optional<IdeasProyecto> opt = repo.findById(idProyecto);
		if (opt.isPresent()) {
			IdeasProyecto proyecto = opt.get();
			proyecto.setCalificaevaluador(nota);
			proyecto.setEvaluacionEvaluador(evaluacion);
			repo.save(proyecto);
			return "Evaluación del evaluador guardada.";
		}
		return "Proyecto no encontrado.";
	}

	// 🔹 Coordinador coloca evaluación final
	@PostMapping("/evaluacion-final/{idProyecto}")
	public String evaluacionFinal(@PathVariable String idProyecto, @RequestParam String evaluacion) {
		Optional<IdeasProyecto> opt = repo.findById(idProyecto);
		if (opt.isPresent()) {
			IdeasProyecto proyecto = opt.get();
			proyecto.setEvaluacionCoordinador(evaluacion);
			repo.save(proyecto);
			return "Evaluación del coordinador guardada.";
		}
		return "Proyecto no encontrado.";
	}

}
