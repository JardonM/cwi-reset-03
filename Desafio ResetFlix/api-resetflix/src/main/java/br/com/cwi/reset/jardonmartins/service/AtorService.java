package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.AtorRepository;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.response.AtorEmAtividade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AtorService {

    @Autowired
    private AtorRepository repository;


    public void criarAtor(AtorRequest atorRequest) throws Exception {
        verificarNome(atorRequest.getNome());
        verificarNascimento(atorRequest.getDataNascimento());
        verificarAnoInicioAtv(atorRequest.getAnoInicioAtividade(), atorRequest.getDataNascimento());
        verificarAtorRepetido(atorRequest);
        Ator ator = new Ator(atorRequest.getNome(), atorRequest.getDataNascimento(),  atorRequest.getStatusCarreira(), atorRequest.getAnoInicioAtividade());
        repository.save(ator);
    }

    public List<AtorEmAtividade> listarAtoresEmAtividade(String filtroNome) throws Exception {
        final List<Ator> atoresCadastrados = consultarAtores();
        final List<AtorEmAtividade> retorno = new ArrayList<>();
        if (filtroNome != null) {
            for (Ator ator : atoresCadastrados) {
                final boolean containsFilter = ator.getNome().toLowerCase(Locale.ROOT).contains(filtroNome.toLowerCase(Locale.ROOT));
                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (containsFilter && emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        } else {
            for (Ator ator : atoresCadastrados) {
                final boolean emAtividade = StatusCarreira.EM_ATIVIDADE.equals(ator.getStatusCarreira());
                if (emAtividade) {
                    retorno.add(new AtorEmAtividade(ator.getId(), ator.getNome(), ator.getDataNascimento()));
                }
            }
        }

        if (retorno.isEmpty()) {
            throw new FiltroNomeNaoEncontrado("Ator", filtroNome);
        }

        return retorno;
    }

    public List<Ator> consultarAtores() throws Exception {
        List<Ator> atores = repository.findAll();
        if(atores.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
        return atores;
    }

    public Ator consultarAtor(Integer id) throws Exception {
        verificaID(id);
        List<Ator> atores = consultarAtores();
        List<Ator> atorEncontrado = atores.stream().filter(e -> Objects.equals(e.getId(), id)).collect(Collectors.toList());
        if(atorEncontrado.size() == 0) {
            throw new ConsultaIdInvalidoException(TipoDominioException.ATOR.getSingular(), id);
        }
        return atorEncontrado.get(0);
    }

    public void atualizarAtor(Integer id, AtorRequest atorRequest) throws Exception {
        verificaID(id);
        List<Ator> atores = repository.findAll();
        for(Ator ator : atores) {
            if(ator.getId().equals(id)){
                ator.setNome(atorRequest.getNome());
                ator.setAnoInicioAtividade(atorRequest.getAnoInicioAtividade());
                ator.setDataNascimento(atorRequest.getDataNascimento());
                ator.setStatusCarreira(atorRequest.getStatusCarreira());
                repository.save(ator);
            }
        }
    }

    public void removerAtor(Integer id) throws Exception {
        verificaID(id);
        List<Ator> atores = repository.findAll();
        for(Ator ator : atores) {
            if(ator.getId().equals(id)) {
                repository.delete(ator);
            }
        }
    }

    private void verificaID(Integer id) throws Exception {
        if (id == null) {
            throw new IdNaoInformado();
        }
    }

    private void verificarNome(String nome) throws Exception {
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
        List<Ator> atores = repository.findAll();
        for(Ator ator : atores) {
            if(ator.getNome().equalsIgnoreCase(atorRequest.getNome())) {
                throw new CadastroDuplicadoException(TipoDominioException.ATOR.getSingular(), atorRequest.getNome());
            }
        }

    }


}