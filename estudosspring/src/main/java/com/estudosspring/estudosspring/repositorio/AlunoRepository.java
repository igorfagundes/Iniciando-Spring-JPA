package com.estudosspring.estudosspring.repositorio;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import com.estudosspring.estudosspring.orm.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Long>{
   List<Aluno> findByNomeStartingWith(String nome);
   List<Aluno> findByNomeStartingWithAndIdadeLessThanEqual(String nome, Integer idade);
}
