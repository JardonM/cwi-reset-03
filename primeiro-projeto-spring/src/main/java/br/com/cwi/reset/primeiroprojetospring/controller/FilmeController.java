package br.com.cwi.reset.primeiroprojetospring.controller;

import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/filmes")
public class FilmeController {




//    private Filme senhorDosAneis = new Filme(1, "A Sociedade do Anel", LocalDate.of(2001,01,01), "Capa do Filme");
//    private Filme senhorDosAneis2 = new Filme(2, "As Duas Torres", 2002, "Capa do Filme");
    List<Filme> filmes = new ArrayList<>();


    private Filme verificarId(Integer id) {
        for(Filme filme : filmes) {
            if(filme.getId().equals(id)){
                return filme;
            }
        }
        return null;
    }

    private Filme verificarNome(String nome) {
        for(Filme filme : filmes) {
            if(filme.getNome().equals(nome)){
                return filme;
            }
        }
        return null;
    }


    @GetMapping
    public List<Filme> buscarFilmes() {
        return filmes;
    }

    @GetMapping("{nome}")
    public Filme buscaFilmePorNome(@PathVariable String nome) {
        return verificarNome(nome);
    }

//    @GetMapping(path = "/sociedade")
//    public Filme getSenhorDosAneis() {
//        return senhorDosAneis;
//    }
//
//    @GetMapping(path = "/duastorres")
//    public Filme getSenhorDosAneis2() {
//        return  senhorDosAneis2;
//    }

    @PostMapping(path = "/cadastrar")
    public ResponseEntity<Filme> cadastrarFilme(@RequestBody Filme filme) {
        Filme existeFilme = verificarNome(filme.getNome());
        if(existeFilme != null) {
            return ResponseEntity.badRequest().build();
        }
        this.filmes.add(filme);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public Filme atualizarFilme(@RequestBody Filme filme) {
        Filme filmeEncontrado = verificarNome(filme.getNome());
        if(filmeEncontrado != null) {
            filmes.remove(filmeEncontrado);
            filmes.add(filme);
        }
        return  filme;
    }

    @DeleteMapping("/{nome}")
    public void deletarFilme(@PathVariable String nome) {
        Filme filmeEncontrado = verificarNome(nome);
        if(filmeEncontrado != null) {
            filmes.remove(filmeEncontrado);
        }
    }

}
