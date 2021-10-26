package br.com.cwi.reset.projeto1.service;

import br.com.cwi.reset.projeto1.domain.Ator;
import br.com.cwi.reset.projeto1.exception.AtorJaExistenteException;
import br.com.cwi.reset.projeto1.exception.AtorNaoExistenteException;
import br.com.cwi.reset.projeto1.repository.AtorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AtorService {

    @Autowired
    private AtorRepository atorRepository;

    public Ator cadastrar(Ator ator) throws Exception {
        Ator atorJaExistente = atorRepository.findByNome(ator.getNome());
        if(atorJaExistente != null) {
            throw new AtorJaExistenteException("Ator com o nome " + ator.getNome() + " já existe");
        }
        return atorRepository.save(ator);
    }

    public List<Ator> listarTodos() {
        return atorRepository.findAll();
    }

    public Ator buscarPorNome(String nome) throws Exception {
        Ator ator = atorRepository.findByNome(nome);
        if(ator == null) {
            throw new AtorNaoExistenteException("Ator com o nome " + ator.getNome() + " não existe");
        }
        return ator;
    }

    public void deletar(String nome) throws Exception {
        Ator ator = buscarPorNome(nome);
        if(ator == null) {
            throw new AtorNaoExistenteException("Ator com o nome " + ator.getNome() + " não existe");
        }
        atorRepository.delete(ator);
    }

    public Ator atualizar(Ator ator) throws Exception {
        Ator atorJaCadastrado = buscarPorNome(ator.getNome());
        if(atorJaCadastrado == null) {
            throw new AtorNaoExistenteException("Ator com o nome " + ator.getNome() + " não existe");
        }
        return atorRepository.save(ator);
    }

    public Ator buscarPorOscar(Integer oscar) throws Exception {
        Ator ator = atorRepository.findByNumeroOscars(oscar);
        if(ator == null) {
            throw new AtorNaoExistenteException("Ator com " + oscar + " numero de oscar não encontrado");
        }
        return ator;
    }

    public List<Ator> buscarPorFiltro(Integer oscar, Integer anoNascimento) {
        LocalDate dataNascimento = LocalDate.of(anoNascimento, 1, 1);
        return atorRepository.findByNumeroOscarsGreaterThanAndDataNascimentoGreaterThan(oscar, dataNascimento);

    }


}
