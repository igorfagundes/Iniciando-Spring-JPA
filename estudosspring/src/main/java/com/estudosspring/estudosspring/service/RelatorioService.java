package com.estudosspring.estudosspring.service;
import com.estudosspring.estudosspring.repositorio.AlunoRepository;
import org.springframework.stereotype.Service;
import com.estudosspring.estudosspring.orm.Aluno;
import java.util.Scanner;
import java.util.List;

@Service
public class RelatorioService {
    private AlunoRepository alunoRepository;

    public RelatorioService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }
    
    
    public void menu(Scanner scan){
        Boolean isTrue = true;
        while(isTrue){
            System.out.println("Qual o relatório você deseja?");
            System.out.println("0 = voltar ao menu anterior");
            System.out.println("1 = Alunos por nome");
            System.out.println("2 = Alunos por nome e idade menor ou igual");

            int opcao = scan.nextInt();

            switch(opcao){
                case 1:
                this.alunosPorNome(scan);
                break;
                case 2:
                this.alunosPorNomeIdadeMenorOuIgual(scan);
                break;
                default:
                isTrue  = false;

                break;
            }
        }
    }
    private void alunosPorNome(Scanner scan){
        
        System.out.println("Digite um nome");
        String nome = scan.next();

        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWith(nome);

        for(Aluno aluno : alunos){
            alunos.forEach(System.out::println);
        }
    }
    private void alunosPorNomeIdadeMenorOuIgual(Scanner scan){
        
        System.out.println("Digite um nome");
        String nome = scan.next();

        System.out.println("Digite a idade");
        Integer idade = scan.nextInt();

        List<Aluno> alunos = this.alunoRepository.findByNomeStartingWithAndIdadeLessThanEqual(nome, idade);

        for(Aluno aluno : alunos){
            alunos.forEach(System.out::println);
        }
    }
}
