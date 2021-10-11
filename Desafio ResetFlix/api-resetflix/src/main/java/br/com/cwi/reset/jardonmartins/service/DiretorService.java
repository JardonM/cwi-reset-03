package br.com.cwi.reset.jardonmartins.service;


import br.com.cwi.reset.jardonmartins.domain.Diretor;
import br.com.cwi.reset.jardonmartins.exception.DiretorException;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.DiretorRequest;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiretorService {
    private Integer id;

    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.id = 0;
    }

    public void cadastrarDiretor(DiretorRequest diretorRequest) throws DiretorException {
        try {
            verificarNome(diretorRequest.getNome());
            verificarNascimento(diretorRequest.getDataNascimento());
            verificarAnoInicioAtv(diretorRequest.getAnoInicioAtividade(), diretorRequest.getDataNascimento());
            verificarDiretorRepetido(diretorRequest);
        } catch (DiretorException exception) {
            System.out.println(exception.getMessage());
        }
        this.id++;
        Diretor diretor = new Diretor(this.id, diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        fakeDatabase.persisteDiretor(diretor);
    }

    public List<Diretor> listarDiretores() throws DiretorException {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if (diretores.size() == 0){
            throw new DiretorException("Nenhum ator cadastrado, favor cadastrar diretores.");
        }
        return diretores;
    }

    public List<Diretor> listarDiretores(String nome) throws DiretorException {
        List<Diretor> diretores = listarDiretores();
        List<Diretor> diretoresPorNome = new ArrayList<Diretor>();
        for (Diretor diretor : diretores) {
            if(diretor.getNome().equalsIgnoreCase(nome)) {
                diretoresPorNome.add(diretor);
            }
        }
        if (diretoresPorNome.size() == 0) {
            throw new DiretorException("Nenhum diretor encontrado com o filtro " + nome + ", favor informar outro filtro.");
        }
        return diretoresPorNome;
    }

    public List<Diretor> consultarDiretores() {
        return fakeDatabase.recuperaDiretores();
    }

    public Diretor consultarDiretor(Integer id) throws DiretorException {
        if (id == null) {
            throw new DiretorException("Campo obrigatório não informado. Favor informar o campo id.");
        }
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        List<Diretor> diretorEncontrado = new ArrayList<Diretor>();
        for(Diretor diretor : diretores) {
            if(Objects.equals(diretor.getId(), id)) {
                diretorEncontrado.add(diretor);
            }
        }
        if(diretorEncontrado.size() == 0) {
            throw new DiretorException("Nenhum diretor encontrado com o parâmetro id=" + id + ", favor verifique os parâmetros informados.");
        }
        return diretorEncontrado.get(0);
    }

    private void verificarNome(String nome) throws DiretorException {
        if ((nome == null) || (nome.isEmpty()) || nome.trim().isEmpty()) {
            throw new DiretorException("Campo obrigatório não informado. Favor informar o campo Nome.");
        }
        if(!nome.contains(" ")) {
            throw new DiretorException("Deve ser informado no mínimo nome e sobrenome para o diretor.");
        }
    }

    private void verificarNascimento(LocalDate dataNasc) throws DiretorException {
        LocalDate dataAtual = LocalDate.now();
        if (dataAtual.isAfter(dataNasc)) {
            throw new DiretorException("Não é possível cadastrar diretores não nascidos.");
        }
    }

    private void verificarAnoInicioAtv(Integer anoInicio, LocalDate dataNasc) throws DiretorException {
        if(dataNasc.getYear() > anoInicio) {
            throw new DiretorException("Ano de início de atividade inválido para o diretor cadastrado.");
        }
    }

    private void verificarDiretorRepetido(DiretorRequest diretorRequest) throws DiretorException {
        List<Diretor> diretores = fakeDatabase.recuperaDiretores();
        if(diretores.contains(diretorRequest)) {
            throw new DiretorException("Já existe um diretor cadastrado para o nome " + diretorRequest.getNome() + ".");
        }
    }

}
