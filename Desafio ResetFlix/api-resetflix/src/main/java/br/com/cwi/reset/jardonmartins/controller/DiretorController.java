package br.com.cwi.reset.jardonmartins.controller;


import br.com.cwi.reset.jardonmartins.domain.Diretor;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.DiretorRequest;
import br.com.cwi.reset.jardonmartins.service.DiretorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    @Autowired
    private DiretorService diretorService;


    @GetMapping
    public List<Diretor> listarDiretoresPorNome(@RequestParam String filtroNome) throws Exception {
        return this.diretorService.listarDiretores(filtroNome);
    }


    @GetMapping("/{id}")
    public Diretor consultarDiretor(@PathVariable Integer id) throws Exception {
        return this.diretorService.consultarDiretor(id);
    }

    @Valid
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarDiretor(@RequestBody DiretorRequest diretorRequest) throws Exception {
        diretorService.cadastrarDiretor(diretorRequest);
    }
    @Valid
    @PutMapping("{id}")
    public void atualizarDiretor(@PathVariable Integer id, @RequestBody DiretorRequest diretorRequest) throws Exception {
        diretorService.atualizarDiretor(id, diretorRequest);
    }

    @DeleteMapping("{id}")
    public void deletarDiretor(@PathVariable Integer id) throws Exception {
        diretorService.removerDiretor(id);
    }

}
