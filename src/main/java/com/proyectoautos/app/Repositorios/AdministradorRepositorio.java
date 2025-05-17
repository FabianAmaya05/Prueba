package com.proyectoautos.app.Repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectoautos.app.Entidades.Administrador;

public interface AdministradorRepositorio extends MongoRepository<Administrador, String> {

    Administrador findByUsuarioAndPassword(String usuario, String password);

	Administrador findByUsuario(String usuario);

}
