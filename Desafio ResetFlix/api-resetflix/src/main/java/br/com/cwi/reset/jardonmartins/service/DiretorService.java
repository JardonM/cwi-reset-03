package br.com.cwi.reset.jardonmartins.service;


import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.Diretor;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.DiretorRepository;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.request.DiretorRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class DiretorService {

    @Autowired
    private DiretorRepository repository;


    public void cadastrarDiretor(DiretorRequest diretorRequest) throws Exception {
        verificarNome(diretorRequest.getNome());
        verificarNascimento(diretorRequest.getDataNascimento());
        verificarAnoInicioAtv(diretorRequest.getAnoInicioAtividade(), diretorRequest.getDataNascimento());
        verificarDiretorRepetido(diretorRequest);
        Diretor diretor = new Diretor(diretorRequest.getNome(), diretorRequest.getDataNascimento(), diretorRequest.getAnoInicioAtividade());
        repository.save(diretor);
    }

    public List<Diretor> listarDiretores() throws Exception {
        List<Diretor> diretores = repository.findAll();
        if (diretores.isEmpty()){
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
        return diretores;
    }

    public List<Diretor> listarDiretores(String nome) throws Exception {
        final List<Diretor> diretoresCadastrados = repository.findAll();

        if (diretoresCadastrados.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }

        final List<Diretor> retorno = new ArrayList<>();

        if (nome != null) {
            for (Diretor diretor : diretoresCadastrados) {
                final boolean containsFilter = diretor.getNome().toLowerCase(Locale.ROOT).contains(nome.toLowerCase(Locale.ROOT));
                if (containsFilter) {
                    retorno.add(diretor);
                }
            }
        } else {
            retorno.addAll(diretoresCadastrados);
        }

        if (retorno.isEmpty()) {
            throw new FiltroNomeNaoEncontrado("Diretor", nome);
        }

        return retorno;
    }


    public Diretor consultarDiretor(Integer id) throws Exception {
        verificarID(id);
        List<Diretor> diretores = listarDiretores();
        List<Diretor> diretorEncontrado = diretores.stream().filter(e -> Objects.equals(e.getId(), id)).collect(Collectors.toList());
        if (diretorEncontrado.size() == 0) {
            throw new ConsultaIdInvalidoException(TipoDominioException.DIRETOR.getSingular(), id);
        }
        return diretorEncontrado.get(0);
    }

    public void atualizarDiretor(Integer id, DiretorRequest diretorRequest) throws Exception {
        verificarID(id);
        List<Diretor> diretores = repository.findAll();
        for(Diretor diretor : diretores) {
            if(diretor.getId().equals(id)) {
                diretor.setNome(diretorRequest.getNome());
                diretor.setAnoInicioAtividade(diretorRequest.getAnoInicioAtividade());
                diretor.setDataNascimento(diretorRequest.getDataNascimento());
                repository.save(diretor);
            }
        }
    }

    public void removerDiretor(Integer id) throws Exception {
        verificarID(id);
        List<Diretor> diretores = repository.findAll();
        for(Diretor diretor : diretores) {
            if(diretor.getId().equals(id)) {
                repository.delete(diretor);
            }
        }
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
        List<Diretor> diretores = repository.findAll();
        for(Diretor diretor : diretores) {
            if(diretor.getNome().equalsIgnoreCase(diretorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.DIRETOR.getSingular(), diretorRequest.getNome());
            }
        }
    }

}
