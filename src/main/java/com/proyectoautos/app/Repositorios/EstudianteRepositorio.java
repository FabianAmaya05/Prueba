package com.proyectoautos.app.Repositorios;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectoautos.app.Entidades.Cliente;

public interface EstudianteRepositorio extends MongoRepository<Cliente, String>{

    Cliente findByUsuarioAndPassword(String usuario, String password);
	boolean existsByUsuario(String usuario);

}
