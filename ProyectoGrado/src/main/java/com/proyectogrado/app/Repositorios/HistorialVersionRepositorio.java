package com.proyectogrado.app.Repositorios;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectogrado.app.Entidades.HistorialVersion;

public interface HistorialVersionRepositorio extends MongoRepository<HistorialVersion, String>{

	List<HistorialVersion> findByProyectoId(String proyectoId);
}
