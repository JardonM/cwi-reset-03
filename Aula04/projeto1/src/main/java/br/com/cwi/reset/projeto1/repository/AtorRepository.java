package br.com.cwi.reset.projeto1.repository;

import br.com.cwi.reset.projeto1.domain.Ator;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface AtorRepository extends CrudRepository<Ator, Integer> {
    Ator findByNome(String nome);
    Ator findByNumeroOscars(Integer numeroOscars);
    Ator findByDataNascimento(LocalDate dataNascimento);
    List<Ator> findAll();
    List<Ator> findByNumeroOscarsGreaterThanAndDataNascimentoGreaterThan(Integer numeroOscars, LocalDate dataNascimento);

}
