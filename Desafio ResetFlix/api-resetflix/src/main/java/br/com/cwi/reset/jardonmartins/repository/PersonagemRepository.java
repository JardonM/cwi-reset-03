package br.com.cwi.reset.jardonmartins.repository;

import br.com.cwi.reset.jardonmartins.domain.PersonagemAtor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonagemRepository extends CrudRepository<PersonagemAtor, Integer> {
    List<PersonagemAtor> findAll();
}
