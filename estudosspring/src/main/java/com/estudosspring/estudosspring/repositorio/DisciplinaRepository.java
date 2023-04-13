package com.estudosspring.estudosspring.repositorio;

import org.springframework.data.repository.CrudRepository;

import com.estudosspring.estudosspring.orm.Disciplina;

public interface DisciplinaRepository extends CrudRepository<Disciplina, Long> {
    
}
