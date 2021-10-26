package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.service.AtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ator")
public class AtorController {

    @Autowired
    private AtorService atorService;

    @GetMapping
    public List<Ator> buscarAtores() {
        return atorService.listarTodos();
    }

    @GetMapping("/{nome}")
    public Ator getByNome(@PathVariable String nome) throws Exception {
        return atorService.buscarPorNome(nome);
    }

    @GetMapping("/by-filter")
    public List<Ator> buscarPorFiltro(@RequestParam Integer numeroOscars, Integer anoNasc) {
        return atorService.buscarPorFiltro(numeroOscars, anoNasc);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Ator cadastrarAtor(@RequestBody Ator ator) throws Exception {
        return atorService.cadastrar(ator);
    }

    @DeleteMapping("/{nome}")
    public void deletarAtor(@PathVariable String nome) throws Exception {
        atorService.deletar(nome);
    }

}
