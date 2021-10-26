package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.Estudio;
import br.com.cwi.reset.jardonmartins.domain.StatusAtividade;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.EstudioRepository;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.request.EstudioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EstudioService {

    @Autowired
    private EstudioRepository repository;

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {
        verificarNome(estudioRequest.getNome());
        verificarEstudioRepetido(estudioRequest);
        verificarDataCriacao(estudioRequest.getDataCriacao());
        Estudio estudio = new Estudio(estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        repository.save(estudio);
    }

    public List<Estudio> listarEstudios() throws Exception {
        List<Estudio> estudios = repository.findAll();
        if (estudios.size() == 0) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }
        return estudios;
    }

    public List<Estudio> listarEstudios(String nome) throws Exception {
        final List<Estudio> estudiosCadastrados = repository.findAll();
        final List<Estudio> estudios = new ArrayList<>();

        if (estudiosCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }

        if (nome != null) {
            for (Estudio estudio : estudiosCadastrados) {
                if (estudio.getNome().toLowerCase(Locale.ROOT).contains(nome.toLowerCase(Locale.ROOT))) {
                    estudios.add(estudio);
                }
            }
        } else {
            estudios.addAll(estudiosCadastrados);
        }

        if (estudios.isEmpty()) {
            throw new FiltroNomeNaoEncontrado("Estúdio", nome);
        }

        return estudios;
    }


    public Estudio consultarEstudios(Integer id) throws Exception {
        verificaID(id);
        List<Estudio> estudios = listarEstudios();
        List<Estudio> estudiosEncontrados = estudios.stream().filter(e -> Objects.equals(e.getId(), id)).collect(Collectors.toList());
        if (estudiosEncontrados.size() == 0) {
            throw new ConsultaIdInvalidoException(TipoDominioException.ESTUDIO.getSingular(), id);
        }
        return estudiosEncontrados.get(0);
    }

    private void verificaID(Integer id) throws Exception {
        if (id == null) {
            throw new IdNaoInformado();
        }
    }

    private void verificarNome(String nome) throws Exception {
        if ((nome == null) || (nome.isEmpty()) || nome.trim().isEmpty()) {
            throw new NomeNaoInformadoException();
        }
    }

    private void verificarDataCriacao(LocalDate dataCriacao) throws Exception {
        LocalDate dataAtual = LocalDate.now();
        if (dataCriacao.isAfter(dataAtual)) {
            throw new NascidosNoFuturoException(TipoDominioException.ESTUDIO.getPlural());
        }
    }


    private void verificarEstudioRepetido(EstudioRequest estudioRequest) throws Exception {
        List<Estudio> estudios = repository.findAll();
        for(Estudio estudio : estudios) {
            if(estudio.getNome().equalsIgnoreCase(estudioRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.ESTUDIO.getSingular(), estudioRequest.getNome());
            }
        }

    }
}
