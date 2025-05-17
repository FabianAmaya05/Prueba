package com.proyectogrado.app.Entidades;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "ideasproyecto")
public class IdeasProyecto {

	@Id
	private String id;
	private String titulo;
	private String descripcion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaCreacion;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaLimite;
	private String estado;
	private String calificadirector;
	private String calificaevaluador;
	
    private String evaluacionDirector;  // Evaluación del Director
    private String evaluacionEvaluador; // Evaluación del Evaluador
    private String evaluacionCoordinador; // Evaluación del Coordinador
	
	@DBRef
	private Estudiante estudiante;
	
	@DBRef
	private Profesor profesor;
	
	@DBRef
	private Profesor director;
	
	@DBRef
	private Profesor evaluador;
	
	@DBRef
	private Coordinador coordinador;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaLimite() {
		return fechaLimite;
	}

	public void setFechaLimite(Date fechaLimite) {
		this.fechaLimite = fechaLimite;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCalificadirector() {
		return calificadirector;
	}

	public void setCalificadirector(String calificadirector) {
		this.calificadirector = calificadirector;
	}

	public String getCalificaevaluador() {
		return calificaevaluador;
	}

	public void setCalificaevaluador(String calificaevaluador) {
		this.calificaevaluador = calificaevaluador;
	}

	public String getEvaluacionDirector() {
		return evaluacionDirector;
	}

	public void setEvaluacionDirector(String evaluacionDirector) {
		this.evaluacionDirector = evaluacionDirector;
	}

	public String getEvaluacionEvaluador() {
		return evaluacionEvaluador;
	}

	public void setEvaluacionEvaluador(String evaluacionEvaluador) {
		this.evaluacionEvaluador = evaluacionEvaluador;
	}

	public String getEvaluacionCoordinador() {
		return evaluacionCoordinador;
	}

	public void setEvaluacionCoordinador(String evaluacionCoordinador) {
		this.evaluacionCoordinador = evaluacionCoordinador;
	}

	public Estudiante getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Estudiante estudiate) {
		this.estudiante = estudiate;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Profesor getDirector() {
		return director;
	}

	public void setDirector(Profesor director) {
		this.director = director;
	}

	public Profesor getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Profesor evaluador) {
		this.evaluador = evaluador;
	}

	public Coordinador getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(Coordinador coordinador) {
		this.coordinador = coordinador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	
	
	
	
	
	
}

	

