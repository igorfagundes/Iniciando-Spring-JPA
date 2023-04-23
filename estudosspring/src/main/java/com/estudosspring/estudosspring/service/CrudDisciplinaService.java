package com.estudosspring.estudosspring.service;

import java.util.*;
import java.util.HashSet;

import org.springframework.stereotype.Service;

import com.estudosspring.estudosspring.orm.Aluno;
import com.estudosspring.estudosspring.orm.Disciplina;
import com.estudosspring.estudosspring.orm.Professor;
import com.estudosspring.estudosspring.repositorio.AlunoRepository;
import com.estudosspring.estudosspring.repositorio.DisciplinaRepository;
import com.estudosspring.estudosspring.repositorio.ProfessorRepositorio;

@Service
public class CrudDisciplinaService {
    private DisciplinaRepository disciplinaRepository; 
    private ProfessorRepositorio professorRepositorio;
    private AlunoRepository alunoRepository;

    public CrudDisciplinaService(DisciplinaRepository disciplinaRepositorio,
                                 ProfessorRepositorio professorRepositorio,
                                 AlunoRepository alunoRepository) {
        this.professorRepositorio = professorRepositorio;
        this.disciplinaRepository = disciplinaRepositorio;
        this.alunoRepository = alunoRepository;
    }



    public void menu(Scanner scan){
        Boolean isTrue = true;
        while(isTrue){
            System.out.println("Qual acao voce quer executar?");
            System.out.println("0 = voltar ao menu anterior");
            System.out.println("1 = cadastrar nova disciplina");
            System.out.println("2 = atualizar uma Disciplina");
            System.out.println("3 = visualizar todas disciplinas");
            System.out.println("4 = deletar uma disciplina");
            System.out.println("5 = matricular alunos");

            int opcao = scan.nextInt();

            switch(opcao){
                case 1:
                this.cadastrar(scan);
                break;
                case 2:
                this.atualizar(scan);
                break;
                case 3:
                this.visualizar();
                break;
                case 4:
                this.deletar(scan);
                break;
                case 5:
                this.matricularAlunos(scan);
                break;
                default:
                isTrue  = false;

                break;
            }
        }
    }
    private void cadastrar(Scanner scan){
        System.out.print("Digite o nome da disciplina");
        String nome = scan.next();

        System.out.print("Semestre: ");
        Integer semestre = scan.nextInt();

        System.out.println("Professor ID");
        Long professorId = scan.nextLong();
        
        Optional<Professor> optional = this.professorRepositorio.findById(professorId);

        if(optional.isPresent()){
            Professor professor = optional.get();

            Set<Aluno> alunos = this.matricular(scan);
            
            Disciplina disciplina = new Disciplina(nome, semestre, professor);
            disciplina.setAlunos(alunos);
            disciplina.setProfessor(professor);
            disciplinaRepository.save(disciplina);
            System.out.println("Salvo\n");
        }else{
            System.out.println("Professor ID :" + professorId + " invalido!");
        }
    }
    //atualizar
    private void atualizar(Scanner scan){
        System.out.print("Digite o id da disciplina a ser atualizada: ");
        Long id = scan.nextLong();

        Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

        if(optionalDisciplina.isPresent()){
            Disciplina disciplina = optionalDisciplina.get();
            System.out.print("Nome da disciplina");
            String nome = scan.next();
    
            System.out.print("Semestre");
            Integer semestre = scan.nextInt();
            
            System.out.println("Professor ID: ");
            Long professorId = scan.nextLong();
            
            Optional<Professor> optionalProfessor = this.professorRepositorio.findById(professorId);
            if(optionalProfessor.isPresent()){
                Professor professor = optionalProfessor.get();

                Set<Aluno> alunos = this.matricular(scan);

                disciplina.setNome(nome);
                disciplina.setSemestre(semestre);
                disciplina.setProfessor(professor);
                
                disciplinaRepository.save(disciplina);
                System.out.println("Atualizado!\n");
        }else{
            System.out.println("Professor ID :" + professorId + " invalido!");
        }
    }
    else{
        System.out.println("Disciplina Id " + id + " invalido!");
    }
}
//visualizar todos
    private void visualizar(){
        Iterable<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        //alternativa 1
        for (Disciplina disciplina : disciplinas) {
           
            System.out.println(disciplina);
        }

        //alternativa 2
        //professores.forEach(professor ->{
         //   System.out.println(professor);
        //});

        //alternativa 3
       // professores.forEach(System.out::println);
        System.out.println();
    }
    //deletar ou remover
  private void deletar(Scanner scan){
    System.out.print("Digite o id da disciplina a ser removida: ");
    Long id = scan.nextLong();
    this.disciplinaRepository.deleteById(id);//lancara uma excption se nao achar uma ID na tabela
    System.out.println("disciplina deletada!\n");
  }
  //matricular alunos
  private void matricularAlunos(Scanner scan){
    System.out.println("Digite o Id da Disciplina para matricular alunos: ");
    Long id = scan.nextLong();

    Optional<Disciplina> optionalDisciplina = this.disciplinaRepository.findById(id);

    if(optionalDisciplina.isPresent()){
        Disciplina disciplina = optionalDisciplina.get();
        Set<Aluno> novosAlunos = this.matricular(scan);
        disciplina.getAlunos().addAll(novosAlunos);
        this.disciplinaRepository.save(disciplina);
    }else{
        System.out.println("O id da disciplina informada: " + id + " é inválido\n");
    }
  }
  //matricular
  private Set<Aluno> matricular(Scanner scan){
    Boolean isTrue = true;
    Set<Aluno> alunos = new HashSet<>();
    while(isTrue){
        System.out.println("ID do aluno a ser matriculado (digite 0 para sair): ");
        Long alunoId = scan.nextLong();

        if(alunoId > 0){
            System.out.println("alunoId: " + alunoId);
            Optional<Aluno> optional = this.alunoRepository.findById(alunoId);
            if(optional.isPresent()){
                alunos.add(optional.get());
            }else{
                System.out.println("Nenhum aluno possui Id " + alunoId + " !");
            }
        }else{
            isTrue = false;
        }
    }
    return alunos;
  }
}


