package com.proyectoautos.app.Repositorios;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.proyectoautos.app.Entidades.IdeasAuto;

public interface IdeasProyectoRepositorio extends MongoRepository<IdeasAuto, String> {

	List<IdeasAuto> findByEstudianteIsNull();
	List<IdeasAuto> findByEstudiante_Id(String idEstudiante);
	List<IdeasAuto> findByDirectorIdOrEvaluadorId(String id, String id2);
	

}
