package com.proyectogrado.app.Controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.proyectogrado.app.Entidades.Administrador;
import com.proyectogrado.app.Entidades.Coordinador;
import com.proyectogrado.app.Entidades.Estudiante;
import com.proyectogrado.app.Entidades.Profesor;
import com.proyectogrado.app.Repositorios.AdministradorRepositorio;
import com.proyectogrado.app.Repositorios.CoordinadorRepositorio;
import com.proyectogrado.app.Repositorios.EstudianteRepositorio;
import com.proyectogrado.app.Repositorios.ProfesorRepositorio;

@Controller
public class AdministradorWebControlador {

	@Autowired
	private AdministradorRepositorio administradorRepositorio;

	@Autowired
	private ProfesorRepositorio profesorRepositorio;

	@Autowired
	private CoordinadorRepositorio coordinadorRepositorio;

	@Autowired
	private EstudianteRepositorio estudianteRepositorio;

	// ----- ADMINISTRADOR -----

	@GetMapping("/administrador/lista")
	public String verAdministradores(Model model) {
		model.addAttribute("listaAdministradores", administradorRepositorio.findAll());
		model.addAttribute("administrador", new Administrador());
		return "verAdministrador";
	}

	@GetMapping("/administrador/agregar")
	public String formularioNuevoAdministrador(Model model) {
		model.addAttribute("administrador", new Administrador());
		return "formAdministrador";
	}

	@PostMapping("/administrador/guardar")
	public String guardarAdministrador(Administrador administrador, RedirectAttributes redirectAttributes) {
		administradorRepositorio.save(administrador);
		redirectAttributes.addFlashAttribute("exito", "Administrador guardado exitosamente.");
		return "redirect:/administrador/lista";
	}

	@GetMapping("/administrador/editar/{id}")
	public String editarAdministrador(@PathVariable String id, Model model) {
		Administrador admin = administradorRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
		model.addAttribute("administrador", admin);
		return "formAdministrador";
	}

	@GetMapping("/administrador/eliminar/{id}")
	public String eliminarAdministrador(@PathVariable String id, RedirectAttributes redirectAttributes) {
		administradorRepositorio.deleteById(id);
		redirectAttributes.addFlashAttribute("eliminado", "Administrador eliminado exitosamente.");
		return "redirect:/administrador/lista";
	}

	@GetMapping("/administrador/{id}/form")
	public String formulario(@PathVariable String id, Model model) {
		Administrador admin = administradorRepositorio.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("ID inválido: " + id));
		model.addAttribute("administrador", admin);
		model.addAttribute("profesores", admin.getProfesores());
		model.addAttribute("coordinadores", admin.getCoordinadores());
		model.addAttribute("estudiantes", admin.getEstudiantes());
		return "formAdministrador";
	}

	// ----- PROFESORES -----

	@GetMapping("/administrador/listaProfesores")
	public String verListaProfesores(Model model) {
		model.addAttribute("listaProfesores", profesorRepositorio.findAll());
		model.addAttribute("profesor", new Profesor());
		return "verProfesores";
	}

	@GetMapping("/administrador/profesores")
	public String verProfesores(Model model) {
		// Aquí puedes obtener los profesores de todos los administradores si es
		// necesario
		model.addAttribute("profesores", profesorRepositorio.findAll());
		return "verProfesores";
	}

	@GetMapping("/administrador/profesor/agregar")
	public String mostrarFormularioAgregarProfesor(Model model) {
		model.addAttribute("profesor", new Profesor());
		return "formProfesor";
	}

	@PostMapping("/administrador/profesor/guardar")
	public String agregarProfesor(@ModelAttribute Profesor profesor, RedirectAttributes redirectAttributes) {
	    // Verificar si ya existe un profesor con el mismo usuario
	    if (profesorRepositorio.existsByUsuario(profesor.getUsuario())) {
	        redirectAttributes.addFlashAttribute("error", "El profesor con ese usuario ya existe.");
	        return "redirect:/administrador/profesores"; // O donde desees redirigir
	    }
	    profesorRepositorio.save(profesor);
	    redirectAttributes.addFlashAttribute("mensaje", "Profesor agregado exitosamente.");
	    return "redirect:/administrador/listaProfesores";
	}

	@GetMapping("/profesor/editar/{id}")
	public String editarProfesor(@PathVariable String id, Model model) {
		Profesor profesor = profesorRepositorio.findById(id).orElseThrow();
		model.addAttribute("profesor", profesor);
		return "formProfesor";
	}

	@PostMapping("/profesor/actualizar")
	public String actualizarProfesor(Profesor profesor, RedirectAttributes redirectAttributes) {
		profesorRepositorio.save(profesor);
		redirectAttributes.addFlashAttribute("mensaje", "Profesor actualizado correctamente.");
		return "redirect:/administrador/listaProfesores";
	}

	@GetMapping("/profesor/eliminar/{id}")
	public String eliminarProfesor(@PathVariable String id, RedirectAttributes redirectAttributes) {
		profesorRepositorio.deleteById(id);
		redirectAttributes.addFlashAttribute("mensaje", "Profesor eliminado correctamente.");
		return "redirect:/administrador/listaProfesores";
	}
	
	

	// ----- COORDINADORES -----

	@GetMapping("/administrador/listaCoordinadores")
	public String verListaCoordinadores(Model model) {
		model.addAttribute("listaCoordinadores", coordinadorRepositorio.findAll());
		model.addAttribute("coordinador", new Coordinador());
		return "verCoordinadores";
	}

	@GetMapping("/administrador/coordinadores")
	public String verCoordinadores(Model model) {
		model.addAttribute("coordinadores", coordinadorRepositorio.findAll());
		return "verCoordinadores";
	}

	@GetMapping("/administrador/coordinador/agregar")
	public String mostrarFormularioAgregarCoordinador(Model model) {
		model.addAttribute("coordinador", new Coordinador());
		return "formCoordinador";
	}

	@PostMapping("/administrador/coordinador/guardar")
	public String agregarCoordinador(@ModelAttribute Coordinador coordinador, RedirectAttributes redirectAttributes) {
		if (coordinadorRepositorio.existsByUsuario(coordinador.getUsuario())) {
			redirectAttributes.addFlashAttribute("error", "El coordinador con ese usuario ya existe.");
			return "redirect:/administrador/coordinadores";
		}
		coordinadorRepositorio.save(coordinador);
		redirectAttributes.addFlashAttribute("mensaje", "Coordinador agregado exitosamente.");
		return "redirect:/administrador/listaCoordinadores";
	}

	@GetMapping("/coordinador/editar/{id}")
	public String editarCoordinador(@PathVariable String id, Model model) {
		Coordinador coordinador = coordinadorRepositorio.findById(id).orElseThrow();
		model.addAttribute("coordinador", coordinador);
		return "formCoordinador";
	}

	@PostMapping("/coordinador/actualizar")
	public String actualizarCoordinador(@ModelAttribute Coordinador coordinador, RedirectAttributes redirectAttributes) {
		coordinadorRepositorio.save(coordinador);
		redirectAttributes.addFlashAttribute("mensaje", "Coordinador actualizado correctamente.");
		return "redirect:/administrador/listaCoordinadores";
	}

	@GetMapping("/coordinador/eliminar/{id}")
	public String eliminarCoordinador(@PathVariable String id, RedirectAttributes redirectAttributes) {
		coordinadorRepositorio.deleteById(id);
		redirectAttributes.addFlashAttribute("mensaje", "Coordinador eliminado correctamente.");
		return "redirect:/administrador/listaCoordinadores";
	}


	// ----- ESTUDIANTES -----

	@GetMapping("/administrador/listaEstudiantes")
	public String verListaEstudiantes(Model model) {
		model.addAttribute("listaEstudiantes", estudianteRepositorio.findAll());
		model.addAttribute("estudiante", new Estudiante());
		return "verEstudiantes";
	}

	@GetMapping("/administrador/estudiantes")
	public String verEstudiantes(Model model) {
		model.addAttribute("estudiantes", estudianteRepositorio.findAll());
		return "verEstudiantes";
	}

	@GetMapping("/administrador/estudiante/agregar")
	public String mostrarFormularioAgregarEstudiante(Model model) {
		model.addAttribute("estudiante", new Estudiante());
		return "formEstudiante";
	}

	@PostMapping("/administrador/estudiante/guardar")
	public String agregarEstudiante(@ModelAttribute Estudiante estudiante, RedirectAttributes redirectAttributes) {
		if (estudianteRepositorio.existsByUsuario(estudiante.getUsuario())) {
			redirectAttributes.addFlashAttribute("error", "El estudiante con ese usuario ya existe.");
			return "redirect:/administrador/estudiantes";
		}
		estudianteRepositorio.save(estudiante);
		redirectAttributes.addFlashAttribute("mensaje", "Estudiante agregado exitosamente.");
		return "redirect:/administrador/listaEstudiantes";
	}

	@GetMapping("/estudiante/editar/{id}")
	public String editarEstudiante(@PathVariable String id, Model model) {
		Estudiante estudiante = estudianteRepositorio.findById(id).orElseThrow();
		model.addAttribute("estudiante", estudiante);
		return "formEstudiante";
	}

	@PostMapping("/estudiante/actualizar")
	public String actualizarEstudiante(@ModelAttribute Estudiante estudiante, RedirectAttributes redirectAttributes) {
		estudianteRepositorio.save(estudiante);
		redirectAttributes.addFlashAttribute("mensaje", "Estudiante actualizado correctamente.");
		return "redirect:/administrador/listaEstudiantes";
	}

	@GetMapping("/estudiante/eliminar/{id}")
	public String eliminarEstudiante(@PathVariable String id, RedirectAttributes redirectAttributes) {
		estudianteRepositorio.deleteById(id);
		redirectAttributes.addFlashAttribute("mensaje", "Estudiante eliminado correctamente.");
		return "redirect:/administrador/listaEstudiantes";
	}


	// ----- LOGIN -----

	  @GetMapping("/panelAdministrador")
	    public String mostrarPanel() {
	        return "panelAdministrador"; // Asumiendo que usas Thymeleaf o JSP
	    }
}
