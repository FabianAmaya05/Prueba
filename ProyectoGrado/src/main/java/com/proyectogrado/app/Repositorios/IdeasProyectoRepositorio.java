package com.proyectogrado.app.Repositorios;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectogrado.app.Entidades.IdeasProyecto;

public interface IdeasProyectoRepositorio extends MongoRepository<IdeasProyecto, String> {

	List<IdeasProyecto> findByEstudianteIsNull();
	List<IdeasProyecto> findByEstudiante_Id(String idEstudiante);
	List<IdeasProyecto> findByDirectorIdOrEvaluadorId(String id, String id2);
	

}
