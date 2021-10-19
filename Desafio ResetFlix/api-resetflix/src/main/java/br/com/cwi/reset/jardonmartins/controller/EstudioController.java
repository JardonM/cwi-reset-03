package br.com.cwi.reset.jardonmartins.controller;


import br.com.cwi.reset.jardonmartins.domain.Estudio;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.EstudioRequest;
import br.com.cwi.reset.jardonmartins.service.EstudioService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    private EstudioService estudioService;

    public EstudioController() {this.estudioService = new EstudioService(FakeDatabase.getInstance());}

    @GetMapping
    public List<Estudio> consultarEstudios() throws Exception {
        return estudioService.consultarEstudios();
    }

    @GetMapping("/buscar/nome/{filtroNome}")
    public List<Estudio> consultarEstudiosPorNome(@PathVariable String filtroNome) throws Exception {
        return this.estudioService.listarEstudios(filtroNome);
    }

    @GetMapping("/buscar/id/{id}")
    public Estudio consultarEstudio(@PathVariable Integer id) throws Exception {
        return this.estudioService.consultarEstudios(id);
    }

    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) throws Exception {
        estudioService.criarEstudio(estudioRequest);
    }


}
