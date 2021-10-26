package br.com.cwi.reset.jardonmartins.repository;

import br.com.cwi.reset.jardonmartins.domain.Diretor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiretorRepository extends CrudRepository<Diretor, Integer> {
    List<Diretor> findAll();
    Diretor findByNome(String nome);
}
