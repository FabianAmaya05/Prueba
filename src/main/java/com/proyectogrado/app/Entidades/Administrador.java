package com.proyectogrado.app.Entidades;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "administrador")
public class Administrador {

	@Id
	private String id;
	private int cedula;
	private String nombres;
	private String apellidos;
	private String usuario;
	private String password;
	
	@DBRef
	private List<Coordinador> coordinadores;
	
	@DBRef
	private List<Profesor> profesores;
	
	@DBRef
	private List<Estudiante> estudiantes;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getCedula() {
		return cedula;
	}
	public void setCedula(int cedula) {
		this.cedula = cedula;
	}
	public String getNombres() {
		return nombres;
	}
	public void setNombres(String nombres) {
		this.nombres = nombres;
	}
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Coordinador> getCoordinadores() {
		return coordinadores;
	}
	public void setCoordinadores(List<Coordinador> coordinadores) {
		this.coordinadores = coordinadores;
	}
	public List<Profesor> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<Profesor> profesores) {
		this.profesores = profesores;
	}
	public List<Estudiante> getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(List<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	
}
