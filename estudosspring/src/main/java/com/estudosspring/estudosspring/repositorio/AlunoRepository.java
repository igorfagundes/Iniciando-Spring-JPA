package com.estudosspring.estudosspring.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.estudosspring.estudosspring.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
    
}
