package br.com.cwi.reset.jardonmartins.controller;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.service.AtorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    private AtorService atorService;

    public AtorController() {
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    @GetMapping
    public List<Ator> listarAtores() throws Exception {
        return atorService.consultarAtores();
    }

    @GetMapping(path = "/em_atividade")
    public List<Ator> listarAtoresEmAtividade() throws Exception {
        return atorService.listarAtoresEmAtividade();
    }

    @GetMapping(path = "/em_atividade/{filtroNome}")
    public List<Ator> listarAtoresEmAtividadePorNome(@PathVariable String filtroNome) throws Exception {
        return atorService.listarAtoresEmAtividade(filtroNome);
    }

    @GetMapping(path = "/{id}")
    public Ator consultarAtor(@PathVariable Integer id) throws Exception {
        return atorService.consultarAtor(id);
    }

    @PostMapping(path = "/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) throws Exception {
        atorService.criarAtor(atorRequest);
    }


}