package br.com.cwi.reset.projeto1.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ator")
public class Ator extends Pessoa {

    private Integer numeroOscars;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Ator(String nome, LocalDate dataNascimento, Integer numeroOscars, Genero genero) {
        super(nome, dataNascimento, genero);
        this.numeroOscars = numeroOscars;
    }

    public Ator() {

    }

    public Integer getNumeroOscars() {
        return numeroOscars;
    }

    public void setNumeroOscars(Integer numeroOscars) {
        this.numeroOscars = numeroOscars;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}