package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.Estudio;
import br.com.cwi.reset.jardonmartins.domain.StatusAtividade;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.request.EstudioRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EstudioService {
    private Integer id;

    private FakeDatabase fakeDatabase;

    public EstudioService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.id = 0;
    }

    public void criarEstudio(EstudioRequest estudioRequest) throws Exception {
        verificarNome(estudioRequest.getNome());
        verificarEstudioRepetido(estudioRequest);
        verificarDataCriacao(estudioRequest.getDataCriacao());
        this.id++;
        Estudio estudio = new Estudio(this.id, estudioRequest.getNome(), estudioRequest.getDescricao(), estudioRequest.getDataCriacao(), estudioRequest.getStatusAtividade());
        fakeDatabase.persisteEstudio(estudio);
    }

    public List<Estudio> listarEstudios() throws Exception {
        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        if (estudios.size() == 0) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }
        return estudios;
    }

    public List<Estudio> listarEstudios(String nome) throws Exception {
        List<Estudio> estudiosEmAtividade = listarEstudios();
        List<Estudio> estudiosEmAtividadePorNome = estudiosEmAtividade.stream().filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        if (estudiosEmAtividadePorNome.size() == 0) {
            throw new FiltroNomeNaoEncontrado("Estudio", nome);
        }
        return estudiosEmAtividadePorNome;
    }

    public List<Estudio> consultarEstudios() throws Exception {
        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        if (estudios.size() == 0) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }
        return estudios;
    }

    public Estudio consultarEstudios(Integer id) throws Exception {
        verificaID(id);
        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
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
        List<Estudio> estudios = fakeDatabase.recuperaEstudios();
        for(Estudio estudio : estudios) {
            if(estudio.getNome().equalsIgnoreCase(estudioRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.ESTUDIO.getSingular(), estudioRequest.getNome());
            }
        }

    }
}
