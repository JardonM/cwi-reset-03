package br.com.cwi.reset.jardonmartins.repository;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AtorRepository extends CrudRepository<Ator, Integer> {
    List<Ator> findAll();
    Ator findByNome(String nome);

}
