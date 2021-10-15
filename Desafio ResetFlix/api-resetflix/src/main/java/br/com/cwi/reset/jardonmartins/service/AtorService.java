package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AtorService {

    private Integer id;

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.id = 0;
    }

    public void criarAtor(AtorRequest atorRequest) throws Exception {
        verificarNome(atorRequest.getNome());
        verificarNascimento(atorRequest.getDataNascimento());
        verificarAnoInicioAtv(atorRequest.getAnoInicioAtividade(), atorRequest.getDataNascimento());
        verificarAtorRepetido(atorRequest);
        this.id++;
        Ator ator = new Ator(this.id, atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), atorRequest.getStatusCarreira());
        fakeDatabase.persisteAtor(ator);
    }

    public List<Ator> listarAtoresEmAtividade() throws Exception {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if (atores.size() == 0){
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
        return atores.stream().filter(e -> e.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)).collect(Collectors.toList());
    }

    public List<Ator> listarAtoresEmAtividade(String nome) throws Exception {
        List<Ator> atoresEmAtividade = listarAtoresEmAtividade();
        List<Ator> atoresEmAtividadePorNome = atoresEmAtividade.stream().filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        if (atoresEmAtividadePorNome.size() == 0) {
            throw new FiltroNomeNaoEncontrado("Ator", nome);
        }
        return atoresEmAtividadePorNome;
    }

    public List<Ator> consultarAtores() throws Exception {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if(atores.size() == 0) {
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
        return atores;
    }

    public Ator consultarAtor(Integer id) throws Exception {
        verificaID(id);
        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atorEncontrado = atores.stream().filter(e -> Objects.equals(e.getId(), id)).collect(Collectors.toList());
        if(atorEncontrado.size() == 0) {
            throw new ConsultaIdInvalidoException(TipoDominioException.ATOR.getSingular(), id);
        }
        return atorEncontrado.get(0);
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
        if(!nome.contains(" ")) {
            throw new NomeSobrenomeObrigatorioException(TipoDominioException.ATOR.getSingular());
        }
    }

    private void verificarNascimento(LocalDate dataNasc) throws Exception {
        LocalDate dataAtual = LocalDate.now();
        if (dataNasc.isAfter(dataAtual)) {
            throw new NascidosNoFuturoException(TipoDominioException.ATOR.getPlural());
        }
    }

    private void verificarAnoInicioAtv(Integer anoInicio, LocalDate dataNasc) throws Exception {
        if(dataNasc.getYear() > anoInicio) {
            throw new AnoInicioAtividadeInvalidoException(TipoDominioException.ATOR.getSingular());
        }
    }

    private void verificarAtorRepetido(AtorRequest atorRequest) throws Exception {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        for(Ator ator : atores) {
            if(ator.getNome().equalsIgnoreCase(atorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
            }
        }

    }


}