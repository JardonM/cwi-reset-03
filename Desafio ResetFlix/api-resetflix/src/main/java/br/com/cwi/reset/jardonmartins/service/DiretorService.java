package br.com.cwi.reset.jardonmartins.service;


import br.com.cwi.reset.jardonmartins.domain.Diretor;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.DiretorRequest;


import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class DiretorService {
    private Integer id;

    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.id = 0;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws Exception {
        verificarNome(diretorRequest.getNome());
        verificarNascimento(diretorRequest.getDataNascimento());
        verificarAnoInicioAtv(diretorRequest.getAnoInicioAtividade(), diretorRequest.getDataNascimento());
        verificarDiretorRepetido(diretorRequest);
        this.id++;
        Diretor diretor = new Diretor(this.id, diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteDiretor(diretor);
    }

    public List<Diretor> listarDiretores() throws Exception {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if (diretores.size() == 0){
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
        return diretores;
    }

    public List<Diretor> listarDiretores(String nome) throws Exception {
        List<Diretor> diretores = listarDiretores();
        List<Diretor> diretoresPorNome = diretores.stream().filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        if (diretoresPorNome.size() == 0) {
            throw new FiltroNomeNaoEncontrado("Diretor", nome);
        }
        return diretoresPorNome;
    }

    public List<Diretor> consultarDiretores() throws Exception {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if(diretores.size() == 0) {
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
        return diretores;
    }

    public Diretor consultarDiretor(Integer id) throws Exception {
        verificarID(id);
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretorEncontrado = diretores.stream().filter(e -> Objects.equals(e.getId(), id)).collect(Collectors.toList());
        if (diretorEncontrado.size() == 0) {
            throw new ConsultaIdInvalidoException(TipoDominioException.DIRETOR.getSingular(), id);
        }
        return diretorEncontrado.get(0);
    }

    private void verificarID(Integer id) throws  Exception {
        if (id == null) {
            throw new IdNaoInformado();
        }
    }

    private void verificarNome(String nome) throws Exception {
        if ((nome == null) || (nome.isEmpty()) || nome.trim().isEmpty()) {
            throw new NomeNaoInformadoException();
        }
        if(!nome.contains(" ")) {
            throw new NomeSobrenomeObrigatorioException(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void verificarNascimento(LocalDate dataNasc) throws Exception {
        LocalDate dataAtual = LocalDate.now();
        if (dataNasc.isAfter(dataAtual)) {
            throw new NascidosNoFuturoException(TipoDominioException.DIRETOR.getPlural());
        }
    }

    private void verificarAnoInicioAtv(Integer anoInicio, LocalDate dataNasc) throws Exception {
        if(dataNasc.getYear() > anoInicio) {
            throw new AnoInicioAtividadeInvalidoException(TipoDominioException.DIRETOR.getSingular());
        }
    }

    private void verificarDiretorRepetido(DiretorRequest diretorRequest) throws Exception {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        for(Diretor diretor : diretores) {
            if(diretor.getNome().equalsIgnoreCase(diretorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.DIRETOR.getSingular(), diretorRequest.getNome());
            }
        }
    }

}
