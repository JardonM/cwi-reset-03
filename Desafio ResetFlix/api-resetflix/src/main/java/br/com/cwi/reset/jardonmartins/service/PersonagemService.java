package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.PersonagemAtor;
import br.com.cwi.reset.jardonmartins.domain.TipoAtuacao;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.PersonagemRequest;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class PersonagemService {

    private Integer id;
    private FakeDatabase fakeDatabase;
    private AtorService atorService;

    public PersonagemService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.id = 0;
        this.atorService = new AtorService(FakeDatabase.getInstance());
    }

    public void criarPersonagem(PersonagemRequest personagemRequest) throws Exception {
        verificaPersonagem(personagemRequest);
        this.id++;
        PersonagemAtor personagemAtor = new PersonagemAtor(this.id, atorService.consultarAtor(personagemRequest.getIdAtor()), personagemRequest.getNomePersonagem(), personagemRequest.getDescricaoPersonagem(), personagemRequest.getTipoAtuacao());
        fakeDatabase.persistePersonagem(personagemAtor);
    }



    public List<PersonagemAtor> listarPersonagens() throws Exception {
        List<PersonagemAtor> personagens = fakeDatabase.recuperaPersonagens();
        if(personagens.size() == 0) {
            throw new ListaVaziaException(TipoDominioException.PERSONAGEM.getSingular(), TipoDominioException.PERSONAGEM.getPlural());
        }
        return personagens;
    }

    public List<PersonagemAtor> listarPersonagensPorNome(String nome) throws Exception {
        List<PersonagemAtor> personagens = listarPersonagens();
        List<PersonagemAtor> personagensPorNome = personagens.stream().filter(e -> e.getNomePersonagem().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        if (personagensPorNome.size() == 0) {
            throw new FiltroNomeNaoEncontrado("Personagem", nome);
        }
        return personagensPorNome;
    }

    public List<PersonagemAtor> listarPersonagemPorAtor(String nome) throws Exception {
        List<PersonagemAtor> personagens = listarPersonagens();
        List<PersonagemAtor> personagemPorAtor = personagens.stream().filter(e -> e.getAtor().getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        if (personagemPorAtor.isEmpty()) {
            throw new FiltroNomeNaoEncontrado(TipoDominioException.ATOR.getSingular(), nome);
        }
        return personagemPorAtor;
    }

    public PersonagemAtor consultarPersonagem(Integer id) throws Exception {
        verificaID(id);
        List<PersonagemAtor> personagens = listarPersonagens();
        List<PersonagemAtor> personagemEncontrado = personagens.stream().filter(e -> Objects.equals(e.getId(), id)).collect(Collectors.toList());
        if(personagemEncontrado.isEmpty()) {
            throw new ConsultaIdInvalidoException(TipoDominioException.PERSONAGEM.getSingular(), id);
        }
        return personagemEncontrado.get(0);
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
            throw new NomeSobrenomeObrigatorioException(TipoDominioException.PERSONAGEM.getSingular());
        }
    }

    private void verificaAtor(Integer id) throws Exception {
        List<Ator> atores = atorService.consultarAtores();
        List<Ator> existeAtor = atores.stream().filter(e -> e.getId().equals(id)).collect(Collectors.toList());
        if(existeAtor.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ATOR.getSingular(), TipoDominioException.ATOR.getPlural());
        }
    }

    private void verificaDescricao(String descricao) throws Exception {
        if ((descricao == null) || (descricao.isEmpty()) || descricao.trim().isEmpty()) {
            throw new DescricaoNaoInformadaException();
        }
        if(!descricao.contains(" ")) {
            throw new DescricaoInvalidaException(TipoDominioException.PERSONAGEM.getSingular());
        }
    }
    public void verificaPersonagem(PersonagemRequest personagemRequest) throws Exception {
        verificarNome(personagemRequest.getNomePersonagem());
        verificaAtor(personagemRequest.getIdAtor());
        verificaDescricao(personagemRequest.getDescricaoPersonagem());
        verificaTipoAtuacao(personagemRequest.getTipoAtuacao());
    }

    private void verificaTipoAtuacao(TipoAtuacao tipoAtuacao) throws Exception {
        if(tipoAtuacao == null) {
            throw new TipoAtuacaoNaoInformadoException();
        }
    }
}
