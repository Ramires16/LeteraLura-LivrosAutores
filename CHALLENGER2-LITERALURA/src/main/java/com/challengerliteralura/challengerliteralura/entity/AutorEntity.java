package com.challengerliteralura.challengerliteralura.entity;

import com.challengerliteralura.challengerliteralura.model.Autor;
import com.challengerliteralura.challengerliteralura.util.CorrentesUteis;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Autor")
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer dataNascimento;
    private Integer dataFalescimento;


    @OneToOne
    @JoinTable(
            name = "livro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private LivroEntity livros;


    public AutorEntity() {

    }

    public AutorEntity(Autor autor) {
        this.nome = CorrentesUteis.limitarLongitud(autor.name(), 200);

        if (autor.birthYear() == null)
            this.dataNascimento = 1980;
        else
            this.dataNascimento = autor.birthYear();

        if (autor.deathYear() == null)
            this.dataFalescimento = 3022;
        else
            this.dataFalescimento = autor.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataFalescimento() {
        return dataFalescimento;
    }

    public void setDataFalescimento(Integer dataFalescimento) {
        this.dataFalescimento = dataFalescimento;
    }


    @Override
    public String toString() {
        return "AutorEntity [id=" + id + ", nome=" + nome + ", dataNascimento=" + dataNascimento
                + ", dataFalescimento=" + dataFalescimento + ", livro="  + livros + "]";
    }

    public LivroEntity getLivros() {
        return livros;
    }

    public void setLivros(LivroEntity livros) {
        this.livros = livros;
    }

}