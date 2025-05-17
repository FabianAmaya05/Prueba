package com.proyectoautos.app.Repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectoautos.app.Entidades.Gerente;

public interface CoordinadorRepositorio extends MongoRepository<Gerente, String> {

	Gerente findByUsuarioAndPassword(String usuario, String password);
	boolean existsByUsuario(String usuario);

}
