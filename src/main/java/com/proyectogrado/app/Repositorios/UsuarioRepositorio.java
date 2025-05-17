package com.proyectogrado.app.Repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectogrado.app.Entidades.Usuario;

public interface UsuarioRepositorio extends MongoRepository<Usuario, String> {

    Usuario findByUsuarioAndPassword(String usuario, String password);

}
