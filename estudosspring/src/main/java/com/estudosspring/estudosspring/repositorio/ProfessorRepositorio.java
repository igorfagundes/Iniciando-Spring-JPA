package com.estudosspring.estudosspring.repositorio;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.estudosspring.estudosspring.orm.Professor;

@Repository
public interface ProfessorRepositorio extends CrudRepository<Professor, Long>{
    
}
