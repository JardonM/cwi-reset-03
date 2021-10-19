package br.com.cwi.reset.jardonmartins.controller;


import br.com.cwi.reset.jardonmartins.domain.Filme;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.FilmeRequest;
import br.com.cwi.reset.jardonmartins.service.FilmeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private FilmeService filmeService;

    public FilmeController() {
        this.filmeService = new FilmeService(FakeDatabase.getInstance());
    }

    @GetMapping
    public List<Filme> listarFilmes() throws Exception {
        return filmeService.listarFilmes();
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarFilme(@RequestBody FilmeRequest filmeRequest) throws Exception {
        filmeService.criarFilme(filmeRequest);
    }
}
