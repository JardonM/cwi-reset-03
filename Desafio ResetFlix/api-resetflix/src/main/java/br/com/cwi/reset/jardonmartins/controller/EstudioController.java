package br.com.cwi.reset.jardonmartins.controller;


import br.com.cwi.reset.jardonmartins.domain.Estudio;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.EstudioRequest;
import br.com.cwi.reset.jardonmartins.service.EstudioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estudios")
public class EstudioController {

    @Autowired
    private EstudioService estudioService;




    @GetMapping
    public List<Estudio> consultarEstudiosPorNome(@RequestParam String filtroNome) throws Exception {
        return this.estudioService.listarEstudios(filtroNome);
    }


    @GetMapping("/{id}")
    public Estudio consultarEstudio(@PathVariable Integer id) throws Exception {
        return this.estudioService.consultarEstudios(id);
    }

    @Valid
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void criarEstudio(@RequestBody EstudioRequest estudioRequest) throws Exception {
        estudioService.criarEstudio(estudioRequest);
    }


}
