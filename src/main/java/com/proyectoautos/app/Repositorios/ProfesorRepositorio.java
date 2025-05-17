package com.proyectoautos.app.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectoautos.app.Entidades.Empleado;

public interface ProfesorRepositorio  extends MongoRepository<Empleado, String>{
	
	Empleado findByUsuarioAndPassword(String usuario, String password);
	Empleado findByid(String id);

	boolean existsByUsuario(String usuario);

}
