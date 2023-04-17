package com.estudosspring.estudosspring;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.estudosspring.estudosspring.orm.Professor;
import com.estudosspring.estudosspring.repositorio.ProfessorRepositorio;
import com.estudosspring.estudosspring.service.CrudDisciplinaService;
import com.estudosspring.estudosspring.service.CrudProfessorService;
import com.estudosspring.estudosspring.service.CrudAlunosService;

@SpringBootApplication
public class EstudosspringApplication implements CommandLineRunner{
	private CrudDisciplinaService disciplinaService;
	private CrudProfessorService professorService;
	private CrudAlunosService alunosService;


//construtor
	public EstudosspringApplication(CrudProfessorService professorService,
									CrudDisciplinaService disciplinaService,
									CrudAlunosService alunosService){
		this.professorService = professorService;
		this.disciplinaService = disciplinaService;
		this.alunosService = alunosService;
	}	
	public static void main(String[] args) {
		SpringApplication.run(EstudosspringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Boolean isTrue = true;
		Scanner scan = new Scanner(System.in);
		while(isTrue){
			System.out.println("Qual entidade voce deseja interagir?");
			System.out.println(" 0 = SAIR");
			System.out.println(" 1 = PROFESSOR");
			System.out.println(" 2 = DISCIPLINA");
			System.out.println(" 3 = ALUNO");

			int opcao = scan.nextInt();
			switch(opcao){
				case 1:
				this.professorService.menu(scan);
				break;
				case 2:
				this.disciplinaService.menu(scan);
				break;
				case 3:
				this.alunosService.menu(scan);
				break;
				default:
				isTrue = false;
				break;
			}
		}
	}

}
