package br.com.cwi.reset.jardonmartins.controller;


import br.com.cwi.reset.jardonmartins.domain.Diretor;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.DiretorRequest;
import br.com.cwi.reset.jardonmartins.service.DiretorService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diretores")
public class DiretorController {

    private DiretorService diretorService;

    public DiretorController() {this.diretorService = new DiretorService(FakeDatabase.getInstance());}

    @GetMapping("/buscar")
    public List<Diretor> listarDiretores() throws Exception {
        return this.diretorService.listarDiretores();
    }

    @GetMapping("/buscar/nome/{filtroNome}")
    public List<Diretor> listarDiretoresPorNome(@PathVariable String filtroNome) throws Exception {
        return this.diretorService.listarDiretores(filtroNome);
    }

    @GetMapping("/buscar/id/{id}")
    public Diretor consultarDiretor(@PathVariable Integer id) throws Exception {
        return this.diretorService.consultarDiretor(id);
    }

    @PostMapping(path = "/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarDiretor(@RequestBody DiretorRequest diretorRequest) throws Exception {
        diretorService.cadastrarDiretor(diretorRequest);
    }

}
