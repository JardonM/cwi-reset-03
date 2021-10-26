package br.com.cwi.reset.jardonmartins.repository;

import br.com.cwi.reset.jardonmartins.domain.Estudio;
import br.com.cwi.reset.jardonmartins.domain.StatusAtividade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstudioRepository extends CrudRepository<Estudio, Integer> {
    List<Estudio> findAll();
    List<Estudio> findByStatusAtividade(String statusAtividade);
}
