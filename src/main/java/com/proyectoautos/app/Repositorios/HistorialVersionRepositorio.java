package com.proyectoautos.app.Repositorios;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectoautos.app.Entidades.HistorialVersion;

public interface HistorialVersionRepositorio extends MongoRepository<HistorialVersion, String>{

	List<HistorialVersion> findByProyectoId(String proyectoId);
}
