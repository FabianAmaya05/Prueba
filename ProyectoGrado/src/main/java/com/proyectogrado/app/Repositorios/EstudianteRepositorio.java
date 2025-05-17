package com.proyectogrado.app.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectogrado.app.Entidades.Estudiante;

public interface EstudianteRepositorio extends MongoRepository<Estudiante, String>{

    Estudiante findByUsuarioAndPassword(String usuario, String password);
	boolean existsByUsuario(String usuario);

}
