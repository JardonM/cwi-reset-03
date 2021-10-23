package br.com.cwi.reset.projeto1.controller;

import br.com.cwi.reset.projeto1.domain.Pet;
import br.com.cwi.reset.projeto1.exception.PetJaExistenteException;
import br.com.cwi.reset.projeto1.service.PetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService = new PetService();

    @GetMapping
    public List<Pet> getPet() {
        return petService.listarTodos();
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Pet> getById(@PathVariable String nome) {
        Pet pet = buscarPetPeloNome(nome);

        if (pet == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(pet);
    }

    private Pet buscarPetPeloNome(String nome) {
        return petService.buscarPorNome(nome);
    }

    @PostMapping
    public Pet cadastrarPet(@RequestBody Pet pet) throws PetJaExistenteException {
        petService.salvar(pet);
        return pet;
    }

    @PutMapping
    public void atualizarPet(@RequestBody Pet pet) throws Exception {
        petService.atualizar(pet);
    }

    @DeleteMapping("/{nome}")
    public void deletarPet(@PathVariable String nome) throws Exception {
        petService.deletar(nome);
    }

}
