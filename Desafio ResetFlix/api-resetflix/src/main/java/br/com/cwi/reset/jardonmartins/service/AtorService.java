package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;
import br.com.cwi.reset.jardonmartins.exception.AtorException;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AtorService {

    private Integer id;

    private FakeDatabase fakeDatabase;

    public AtorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.id = 0;
    }

    public void criarAtor(AtorRequest atorRequest) throws AtorException {
        try {
            verificarNome(atorRequest.getNome());
            verificarNascimento(atorRequest.getDataNascimento());
            verificarAnoInicioAtv(atorRequest.getAnoInicioAtividade(), atorRequest.getDataNascimento());
            verificarAtorRepetido(atorRequest);
        } catch (AtorException exception) {
            System.out.println(exception.getMessage());
        }
        this.id++;
        Ator ator = new Ator(this.id, atorRequest.getNome(), atorRequest.getDataNascimento(), atorRequest.getAnoInicioAtividade(), atorRequest.getStatusCarreira());
        fakeDatabase.persisteAtor(ator);
    }

    public List<Ator> listarAtoresEmAtividade() throws AtorException {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if (atores.size() == 0){
            throw new AtorException("Nenhum ator cadastrado, favor cadastrar atores.");
        }
        List<Ator> atoresEmAtividade = new ArrayList<>();
        for (Ator ator : atores) {
            if (ator.getStatusCarreira().equals(StatusCarreira.EM_ATIVIDADE)) {
                atoresEmAtividade.add(ator);
            }
        }
        return atoresEmAtividade;
    }

    public List<Ator> listarAtoresEmAtividade(String nome) throws AtorException {
        List<Ator> atoresEmAtividade = listarAtoresEmAtividade();
        List<Ator> atoresEmAtividadePorNome = new ArrayList<>();
        for (Ator ator : atoresEmAtividade) {
            if(ator.getNome().toLowerCase().contains(nome.toLowerCase())) {
                atoresEmAtividadePorNome.add(ator);
            }
        }
        if (atoresEmAtividadePorNome.size() == 0) {
            throw new AtorException("Nenhum ator encontrado com o filtro " + nome + ", favor informar outro filtro.");
        }
        return atoresEmAtividadePorNome;
    }

    public List<Ator> consultarAtores() {
       return fakeDatabase.recuperaAtores();
    }

    public Ator consultarAtor(Integer id) throws AtorException {
        if (id == null) {
            throw new AtorException("Campo obrigatório não informado. Favor informar o campo id.");
        }
        List<Ator> atores = fakeDatabase.recuperaAtores();
        List<Ator> atorEncontrado = new ArrayList<>();
        for(Ator ator : atores) {
            if(Objects.equals(ator.getId(), id)) {
                atorEncontrado.add(ator);
            }
        }
        if(atorEncontrado.size() == 0) {
            throw new AtorException("Nenhum ator encontrado com o parâmetro id=" + id + ", favor verifique os parâmetros informados.");
        }
        return atorEncontrado.get(0);
    }

    private void verificarNome(String nome) throws AtorException {
        if ((nome == null) || (nome.isEmpty()) || nome.trim().isEmpty()) {
            throw new AtorException("Campo obrigatório não informado. Favor informar o campo Nome.");
        }
        if(!nome.contains(" ")) {
            throw new AtorException("Deve ser informado no mínimo nome e sobrenome para o ator.");
        }
    }

    private void verificarNascimento(LocalDate dataNasc) throws AtorException {
        LocalDate dataAtual = LocalDate.now();
        if (dataNasc.isAfter(dataAtual)) {
            throw new AtorException("Não é possível cadastrar atores não nascidos.");
        }
    }

    private void verificarAnoInicioAtv(Integer anoInicio, LocalDate dataNasc) throws AtorException {
        if(dataNasc.getYear() > anoInicio) {
            throw new AtorException("Ano de início de atividade inválido para o ator cadastrado.");
        }
    }

    private void verificarAtorRepetido(AtorRequest atorRequest) throws AtorException {
        List<Ator> atores = fakeDatabase.recuperaAtores();
        if(atores.contains(atorRequest)) {
            throw new AtorException("Já existe um ator cadastrado para o nome " + atorRequest.getNome() + ".");
        }
    }


}