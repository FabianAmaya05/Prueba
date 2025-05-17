package com.proyectoautos.app.Controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.proyectoautos.app.Entidades.Cliente;
import com.proyectoautos.app.Entidades.IdeasAuto;
import com.proyectoautos.app.Repositorios.IdeasProyectoRepositorio;

@Controller
@RequestMapping("/proyectos")
public class IdeasProyectoWebControlador {

	@Autowired
	private IdeasProyectoRepositorio repo;

	@GetMapping("/coordinador/creados")
	public String verProyectosCreados(Model model) {
		model.addAttribute("proyectos", repo.findAll()); // O podrías usar un filtro si necesitas
		return "lista-autos-creados";
	}
	
	/*@GetMapping("/proyectos/nuevo")
	public String nuevoProyecto(Model model) {
	    model.addAttribute("proyecto", new IdeasProyecto());
	    return "formulario-proyectos"; // Esto carga formulario-proyectos.html
	}
*/

	@GetMapping("/editar/{id}")
	public String editarProyecto(@PathVariable String id, Model model) {
	    IdeasAuto proyecto = repo.findById(id).orElse(null);
	    if (proyecto == null) {
	        // Manejo de error: redirigir o mostrar un mensaje adecuado
	        return "redirect:/error";
	    }
	    model.addAttribute("proyecto", proyecto);
	    return "formulario-proyecto"; // reutiliza el mismo formulario
	}

	

	// Vista para editar proyecto
	/*
	 * @GetMapping("/editar/{id}") public String editarProyectoForm(@PathVariable
	 * String id, Model model) { IdeasProyecto proyecto =
	 * repo.findById(id).orElse(null); if (proyecto != null) {
	 * model.addAttribute("proyecto", proyecto); return
	 * "formulario-editar-proyecto"; } return "redirect:/proyectos/coordinador"; }
	 */
	
	
	// Actualizar proyecto
	@PostMapping("/actualizar")
	public String actualizarProyecto(@ModelAttribute IdeasAuto proyecto, Model model) {
		repo.save(proyecto);
		model.addAttribute("editado", "Proyecto actualizado correctamente");
		return "redirect:/proyectos/coordinador";
	}

	// Eliminar proyecto
	@GetMapping("/eliminar/{id}")
	public String eliminarProyecto(@PathVariable String id, Model model) {
		repo.deleteById(id);
		model.addAttribute("eliminado", "Proyecto eliminado correctamente");
		return "redirect:/proyectos/coordinador/creados";
	}

	// Vista para el coordinador - crear proyecto
	@GetMapping("/coordinador/nuevo")
	public String nuevoProyectoForm(Model model) {
		model.addAttribute("proyecto", new IdeasAuto());
		// Agrega listas de profesores para elegir director/evaluador
		return "formulario-proyecto";
	}

	@PostMapping("/coordinador/guardar")
	public String guardarNuevoProyecto(@ModelAttribute IdeasAuto proyecto, Model model) {
	    repo.save(proyecto);
	    model.addAttribute("exito", "Proyecto guardado correctamente");
	    return "redirect:/proyectos/coordinador/creados"; // o el destino que quieras después de guardar
	}


	// Vista para el coordinador - ver todos los proyectos
	@GetMapping("/coordinador")
	public String listaCoordinador(Model model) {
		model.addAttribute("proyectos", repo.findAll());
		return "lista-proyectos-coordinador";
	}
	
	@GetMapping("/coordinador/seleccionados")
	public String proyectosSeleccionados(Model model) {
	    List<IdeasAuto> seleccionados = repo.findByEstudianteIsNull();
	    model.addAttribute("proyectos", seleccionados);
	    return "proyectos-seleccionados";
	}


	// Vista para el estudiante - ver proyectos disponibles para elegir
	@GetMapping("/disponibles")
	public String proyectosDisponibles(Model model) {
		model.addAttribute("proyectos", repo.findByEstudianteIsNull());
		return "proyectos-disponibles";
	}

	// El estudiante escoge un proyecto
	@PostMapping("/escoger/{id}")
	public String escogerProyecto(@PathVariable String id, @RequestParam String estudianteId) {
	    IdeasAuto proyecto = repo.findById(id).orElse(null);
	    if (proyecto != null && proyecto.getEstudiante() == null) {
	        // Asignar el proyecto al estudiante
	        Cliente estudiante = new Cliente(); // Crear una instancia del estudiante
	        estudiante.setId(estudianteId); // Asignar el ID del estudiante
	        proyecto.setEstudiante(estudiante); // Asignar el estudiante al proyecto
	        repo.save(proyecto); // Guardar el proyecto actualizado
	    }
	    // Redirigir a la vista donde el estudiante puede ver sus proyectos
	    return "redirect:/proyectos/estudiante/" + estudianteId;
	}


	// Estudiante ve su proyecto elegido
	@GetMapping("/estudiante/{id}")
	public String verProyectoEstudiante(@PathVariable String id, Model model) {
		List<IdeasAuto> proyectos = repo.findByEstudiante_Id(id);
		model.addAttribute("proyectos", proyectos);
		return "proyecto-estudiante";
	}
	
	
	@GetMapping("/asignar/{id}")
	public String asignarDirectorEvaluador(@PathVariable String id, Model model) {
	    IdeasAuto proyecto = repo.findById(id).orElse(null);
	    if (proyecto == null) return "redirect:/proyectos/coordinador";

	    model.addAttribute("proyecto", proyecto);
	    // Aquí podrías agregar la lista de profesores si los tienes
	    return "formulario-asignar";
	}

	@PostMapping("/asignar/{id}")
	public String guardarAsignacion(@PathVariable String id,
	                                 @RequestParam String directorId,
	                                 @RequestParam String evaluadorId) {
	    IdeasAuto proyecto = repo.findById(id).orElse(null);
	    if (proyecto != null) {
	        proyecto.getDirector().setId(directorId);
	        proyecto.getEvaluador().setId(evaluadorId);
	        repo.save(proyecto);
	    }
	    return "redirect:/proyectos/coordinador/seleccionados";
	}


	// Docente (director/evaluador) ve sus proyectos
	@GetMapping("/docente/{id}")
	public String verProyectosDocente(@PathVariable String id, Model model) {
		List<IdeasAuto> proyectos = repo.findByDirectorIdOrEvaluadorId(id, id);
		model.addAttribute("proyectos", proyectos);
		return "proyectos-docente";
	}

	// Guardar calificación de director
	@PostMapping("/calificar-director/{id}")
	public String calificarDirector(@PathVariable String id, @RequestParam String nota,
			@RequestParam String evaluacion) {
		IdeasAuto proyecto = repo.findById(id).orElse(null);
		if (proyecto != null) {
			proyecto.setCalificadirector(nota);
			proyecto.setEvaluacionDirector(evaluacion);
			repo.save(proyecto);
		}
		return "redirect:/proyectos/docente/" + proyecto.getDirector().getId();
	}

	// Guardar calificación de evaluador
	@PostMapping("/calificar-evaluador/{id}")
	public String calificarEvaluador(@PathVariable String id, @RequestParam String nota,
			@RequestParam String evaluacion) {
		IdeasAuto proyecto = repo.findById(id).orElse(null);
		if (proyecto != null) {
			proyecto.setCalificaevaluador(nota);
			proyecto.setEvaluacionEvaluador(evaluacion);
			repo.save(proyecto);
		}
		return "redirect:/proyectos/docente/" + proyecto.getEvaluador().getId();
	}

	// Coordinador coloca evaluación final
	@PostMapping("/evaluacion-final/{id}")
	public String evaluacionCoordinador(@PathVariable String id, @RequestParam String evaluacion) {
		IdeasAuto proyecto = repo.findById(id).orElse(null);
		if (proyecto != null) {
			proyecto.setEvaluacionCoordinador(evaluacion);
			repo.save(proyecto);
		}
		return "redirect:/proyectos/coordinador";
	}

}
