package com.proyectoautos.app.Entidades;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "ideasproyecto")
public class IdeasAuto {

	@Id
	private String id;
	private String titulo;
	private Double precio;
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
	private Cliente estudiante;
	
	@DBRef
	private Empleado profesor;
	
	@DBRef
	private Empleado director;
	
	@DBRef
	private Empleado evaluador;
	
	@DBRef
	private Gerente coordinador;

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

	public Cliente getEstudiante() {
		return estudiante;
	}

	public void setEstudiante(Cliente estudiate) {
		this.estudiante = estudiate;
	}

	public Empleado getProfesor() {
		return profesor;
	}

	public void setProfesor(Empleado profesor) {
		this.profesor = profesor;
	}

	public Empleado getDirector() {
		return director;
	}

	public void setDirector(Empleado director) {
		this.director = director;
	}

	public Empleado getEvaluador() {
		return evaluador;
	}

	public void setEvaluador(Empleado evaluador) {
		this.evaluador = evaluador;
	}

	public Gerente getCoordinador() {
		return coordinador;
	}

	public void setCoordinador(Gerente coordinador) {
		this.coordinador = coordinador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	
	
	
	
	
	
}

	

