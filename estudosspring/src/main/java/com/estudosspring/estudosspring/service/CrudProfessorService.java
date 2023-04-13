package com.estudosspring.estudosspring.service;

import java.util.Optional;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import com.estudosspring.estudosspring.orm.Disciplina;
import com.estudosspring.estudosspring.orm.Professor;
import com.estudosspring.estudosspring.repositorio.ProfessorRepositorio;

import jakarta.transaction.Transactional;

@Service
public class CrudProfessorService {
    private ProfessorRepositorio professorRepositorio;

    public CrudProfessorService(ProfessorRepositorio professorRepositorio){
        this.professorRepositorio = professorRepositorio;
    }


    @Transactional
    public void menu(Scanner scan){
        Boolean isTrue = true;
        while(isTrue){
            System.out.println("Qual acao voce quer executar?");
            System.out.println("0 = voltar ao menu anterior");
            System.out.println("1 = cadastrar novo professor");
            System.out.println("2 = atualizar um professor");
            System.out.println("3 = visualizar todos  os professores");
            System.out.println("4 = deletar um professor");
            System.out.println("5 = visualizar um professor");

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
                this.visualizarUmProfessor(scan);
                break;
                default:
                isTrue  = false;

                break;
            }
        }
    }
    private void cadastrar(Scanner scan){
        System.out.print("Digite o nome do professor");
        String nome = scan.next();

        System.out.print("Digite o prontuario do professor");
        String protocolo = scan.next();

        Professor professor = new Professor(nome, protocolo);
        this.professorRepositorio.save(professor);
        System.out.println("professor salvo no BD");
    }
    private void atualizar(Scanner scan){
        System.out.print("Digite o id do professor a ser atualizado: ");
        Long id = scan.nextLong();

        Optional<Professor> optional = this.professorRepositorio.findById(id);

        if(optional.isPresent()){
            System.out.print("Digite o nome do professor");
            String nome = scan.next();
    
            System.out.print("Digite o prontuario do professor");
            String protocolo = scan.next();
            Professor professor = optional.get();
            professor.setNome(nome);
            professor.setProtocolo(protocolo);
            professorRepositorio.save(professor);
            System.out.println("professor atualizado com sucesso");
        }else{
            System.out.println("id do professor informado :" + id + " é invalido!");
        }
    }
    private void visualizar(){
        Iterable<Professor> professores = this.professorRepositorio.findAll();
        //alternativa 1
        for (Professor professor: professores) {
            System.out.println(professores);
        }

        //alternativa 2
        //professores.forEach(professor ->{
         //   System.out.println(professor);
        //});

        //alternativa 3
       // professores.forEach(System.out::println);
        System.out.println();
    }
  private void deletar(Scanner scan){
    System.out.print("Digite o id do professor a ser removido: ");
    Long id = scan.nextLong();
    this.professorRepositorio.deleteById(id);//lancara uma excption se nao achar uma ID na tabela
    System.out.println("professor deletado!\n");
  }
  @Transactional
  private void visualizarUmProfessor(Scanner scan){
    System.out.println("Id do professor: ");
    Long id = scan.nextLong();

    Optional<Professor> optional = professorRepositorio.findById(id);
    if(optional.isPresent()){
        Professor professor = optional.get();

        System.out.println("Professor {");
        System.out.println("ID: " + professor.getId());
        System.out.println("Nome: " + professor.getNome());
        System.out.println("Prontuario: " + professor.getProtocolo());
        System.out.println("Disciplinas [");
        for(Disciplina disciplina: professor.getDisciplinas()){
            System.out.println("\tID: " + disciplina.getId());
            System.out.println("\tNome: " + disciplina.getNome());
            System.out.println("\tSemestre: " + disciplina.getSemestre());
            System.out.println("");
        }
        System.out.println("]\n}");
    }
  }
}
