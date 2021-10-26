package br.com.cwi.reset.jardonmartins.controller;


import br.com.cwi.reset.jardonmartins.domain.Filme;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.FilmeRequest;
import br.com.cwi.reset.jardonmartins.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping
    public List<Filme> consultarFilmes(
            @RequestParam String nomeFilme,
            @RequestParam String nomeDiretor,
            @RequestParam String nomePersonagem,
            @RequestParam String nomeAtor) throws Exception {
        return filmeService.consultarFilmes(nomeFilme, nomeDiretor, nomePersonagem, nomeAtor);
    }

    @Valid
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody FilmeRequest filmeRequest) throws Exception {
        filmeService.criarFilme(filmeRequest);
    }

    @DeleteMapping("/{id}")
    public void deletarFIlme(@PathVariable Integer id) throws Exception {
        filmeService.removerFilme(id);
    }
}
