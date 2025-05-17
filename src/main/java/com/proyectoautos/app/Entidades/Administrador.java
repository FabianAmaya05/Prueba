package com.proyectoautos.app.Entidades;

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
	private List<Gerente> coordinadores;
	
	@DBRef
	private List<Empleado> profesores;
	
	@DBRef
	private List<Cliente> estudiantes;
	
	
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
	public List<Gerente> getCoordinadores() {
		return coordinadores;
	}
	public void setCoordinadores(List<Gerente> coordinadores) {
		this.coordinadores = coordinadores;
	}
	public List<Empleado> getProfesores() {
		return profesores;
	}
	public void setProfesores(List<Empleado> profesores) {
		this.profesores = profesores;
	}
	public List<Cliente> getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(List<Cliente> estudiantes) {
		this.estudiantes = estudiantes;
	}
	
	
}
