package com.estudosspring.estudosspring.service;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.stereotype.Service;
import com.estudosspring.estudosspring.orm.Aluno;
import com.estudosspring.estudosspring.orm.Disciplina;
import com.estudosspring.estudosspring.repositorio.AlunoRepository;
import jakarta.transaction.Transactional;

@Service
public class CrudAlunosService {
        private AlunoRepository alunoRepository;
    
        public CrudAlunosService(AlunoRepository alunoRepository){
            this.alunoRepository = alunoRepository;
        }
    
    
        @Transactional
        public void menu(Scanner scan){
            Boolean isTrue = true;
            while(isTrue){
                System.out.println("Qual acao voce quer executar?");
                System.out.println("0 = voltar ao menu anterior");
                System.out.println("1 = cadastrar novo aluno");
                System.out.println("2 = atualizar um aluno");
                System.out.println("3 = visualizar todos  os alunos");
                System.out.println("4 = deletar um aluno");
                System.out.println("5 = visualizar um aluno");
    
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
                    this.visualizarUmAluno(scan);
                    break;
                    default:
                    isTrue  = false;
    
                    break;
                }
            }
        }
        //cadastrar
        private void cadastrar(Scanner scan){
            Scanner scann = new Scanner(System.in);
            System.out.print("Digite o nome do aluno: ");
            String nome = scann.nextLine();
            //scan.nextLine();
            System.out.print("Digite a idade do aluno: ");
            Integer idade = scann.nextInt();
    
            Aluno aluno = new Aluno();
            aluno.setNome(nome);
            aluno.setIdade(idade);
            this.alunoRepository.save(aluno);
            System.out.println("aluno salvo no BD");
        }
        //atualizar
        private void atualizar(Scanner scan){
            System.out.print("Digite o id do aluno a ser atualizado: ");
            Long id = scan.nextLong();
    
            Optional<Aluno> optional = this.alunoRepository.findById(id);
    
            if(optional.isPresent()){
                System.out.print("Digite o nome do aluno");
                String nome = scan.next();
                
                System.out.print("Digite a idade do aluno");
                Integer idade = scan.nextInt();

                Aluno aluno = optional.get();
                aluno.setNome(nome);
                aluno.setIdade(idade);
                alunoRepository.save(aluno);
                System.out.println("aluno atualizado com sucesso");
            }else{
                System.out.println("id do aluno informado :" + id + " é invalido!");
            }
        }
        private void visualizar(){
            Iterable<Aluno> alunos = this.alunoRepository.findAll();
            //alternativa 1
            for (Aluno aluno: alunos) {
                System.out.println(alunos);
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
        System.out.print("Digite o id do aluno a ser removido: ");
        Long id = scan.nextLong();
        this.alunoRepository.deleteById(id);//lancara uma exception se nao achar uma ID na tabela
        System.out.println("aluno deletado!\n");
      }
      @Transactional
      private void visualizarUmAluno(Scanner scan){
        System.out.println("Id do aluno: ");
        Long id = scan.nextLong();
    
        Optional<Aluno> optional = alunoRepository.findById(id);
        if(optional.isPresent()){
            Aluno aluno = optional.get();
    
            System.out.println("Aluno {");
            System.out.println("ID: " + aluno.getId());
            System.out.println("Nome: " + aluno.getNome());
            System.out.println("Idade: " + aluno.getIdade());
            System.out.println("Disciplinas: [");

            if(aluno.getDisciplinas() != null){
                for(Disciplina disciplina: aluno.getDisciplinas()){
                    System.out.println("\tDisciplina: " + disciplina.getNome());
                    System.out.println("\tSemestre: " + disciplina.getSemestre());
                    System.out.println("");
            }
            System.out.println("]");
        }
      }else{
        System.out.println("O Id do aluno " + id + " é invalido!");
      }
    }
}