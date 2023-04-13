package com.estudosspring.estudosspring.orm;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PreRemove;
import jakarta.persistence.Table;

@Entity
@Table(name = "professores")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false, unique = true)
    private String protocolo;
    
    @OneToMany(mappedBy = "professor", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Disciplina> disciplina;

    @Deprecated//sera usada por outras bibliotecas
    public Professor(){

    }
//construtor
    public Professor(String nome, String protocolo) {
        this.nome = nome;
        this.protocolo = protocolo;
    }
//getters e setters
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getProtocolo() {
        return protocolo;
    }
    public void setProtocolo(String protocolo) {
        this.protocolo = protocolo;
    }
    public List<Disciplina> getDisciplinas(){
        return disciplina;
    }
    public void setDisciplinas(List<Disciplina> disciplinas){
        this.disciplina = disciplina;
    }
    //on remove set null
    @PreRemove
    public void atualizaDisciplinasOnDelete(){
        System.out.println("***** AtualizaDisciplinasOnDelete *****");
        for(Disciplina disciplina: this.getDisciplinas()){
            disciplina.setProfessor(null);
        }
    }

    @Override
    public String toString() {
        return "Professor{" + "id=" + id + 
                          ", nome=" + nome + '\'' +
                          ", protocolo='" + protocolo + '\'' +
                          '}';
                            
    }
    
    
}
