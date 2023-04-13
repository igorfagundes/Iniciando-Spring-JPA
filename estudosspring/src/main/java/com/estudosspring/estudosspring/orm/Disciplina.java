package com.estudosspring.estudosspring.orm;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "disciplinas")
public class Disciplina{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    private Integer semestre;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = true)
    private Professor professor;
    
    @ManyToMany
    @JoinTable(name = "disciplinas_alunos",
               joinColumns = @JoinColumn(name = "disciplinas_fk"),
               inverseJoinColumns = @JoinColumn(name = "aluno_fk"))
    List<Aluno> alunos;
    
    @Deprecated
    public Disciplina(){ }

    public Disciplina(String nome, Integer semestre, Professor professor) {
        this.nome = nome;
        this.semestre = semestre;
        this.professor = professor;
    }

    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getSemestre() {
        return semestre;
    }

    public void setSemestre(Integer semestre) {
        this.semestre = semestre;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Disciplina [id=" + id + ", nome=" + nome + ", semestre=" + semestre + ", professor=" + professor + "]";
    }





    
}