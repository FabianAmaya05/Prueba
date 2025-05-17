package com.proyectoautos.app.Repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectoautos.app.Entidades.Usuario;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {

    Usuario findByUsuarioAndPassword(String usuario, String password);

}
