package com.proyectogrado.app.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectogrado.app.Entidades.Profesor;

public interface ProfesorRepositorio  extends MongoRepository<Profesor, String>{
	
	Profesor findByUsuarioAndPassword(String usuario, String password);
	Profesor findByid(String id);

	boolean existsByUsuario(String usuario);

}
