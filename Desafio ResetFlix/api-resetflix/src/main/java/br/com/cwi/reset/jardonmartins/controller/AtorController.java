package br.com.cwi.reset.jardonmartins.controller;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.response.AtorEmAtividade;
import br.com.cwi.reset.jardonmartins.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/atores")
public class AtorController {

    @Autowired
    private AtorService atorService;


    @GetMapping
    public List<Ator> listarAtores() throws Exception {
        return atorService.consultarAtores();
    }


    @GetMapping(path = "/em_atividade")
    public List<AtorEmAtividade> listarAtoresEmAtividade(@RequestParam String filtroNome) throws Exception {
        return this.atorService.listarAtoresEmAtividade(filtroNome);
    }


    @GetMapping(path = "/{id}")
    public Ator consultarAtor(@PathVariable Integer id) throws Exception {
        return atorService.consultarAtor(id);
    }

    @Valid
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarAtor(@RequestBody AtorRequest atorRequest) throws Exception {
        atorService.criarAtor(atorRequest);
    }

    @Valid
    @PutMapping("/{id}")
    public void atualizarAtor(@PathVariable Integer id, @RequestBody AtorRequest atorRequest) throws Exception {
        atorService.atualizarAtor(id, atorRequest);
    }

    @DeleteMapping("/{id}")
    public void removerAtor(@PathVariable Integer id) throws Exception {
        atorService.removerAtor(id);
    }


}