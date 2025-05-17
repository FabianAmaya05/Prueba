package com.proyectoautos.app.Entidades;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "profesor")
public class Empleado {
	
	@Id
	private String id;
	private int cedula;
	private String nombre;
	private String apellidos;
	private String usuario;
	private String password;
	
	private boolean isDirector;
	private boolean isEvaluador;
	
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public boolean isEvaluador() {
		return isEvaluador;
	}
	public void setEvaluador(boolean isEvaluador) {
		this.isEvaluador = isEvaluador;
	}
	public boolean isDirector() {
		return isDirector;
	}
	public void setDirector(boolean isDirector) {
		this.isDirector = isDirector;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
//	public Empleado orElseThrow() {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	

}