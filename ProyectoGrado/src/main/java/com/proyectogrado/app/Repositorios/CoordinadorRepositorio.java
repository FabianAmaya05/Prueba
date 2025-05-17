package com.proyectogrado.app.Repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectogrado.app.Entidades.Coordinador;

public interface CoordinadorRepositorio extends MongoRepository<Coordinador, String> {

	Coordinador findByUsuarioAndPassword(String usuario, String password);
	boolean existsByUsuario(String usuario);

}
