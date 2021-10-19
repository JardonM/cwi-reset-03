package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.*;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.FilmeRequest;
import br.com.cwi.reset.jardonmartins.request.PersonagemRequest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilmeService {

    private Integer id;
    private FakeDatabase fakeDatabase;
    private AtorService atorService;
    private DiretorService diretorService;
    private EstudioService estudioService;
    private PersonagemService personagemService;

    public FilmeService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
        this.atorService = new AtorService(FakeDatabase.getInstance());
        this.diretorService = new DiretorService(FakeDatabase.getInstance());
        this.estudioService = new EstudioService(FakeDatabase.getInstance());
        this.personagemService = new PersonagemService(FakeDatabase.getInstance());
        this.id = 0;
    }

    public void criarFilme(FilmeRequest filmeRequest) throws Exception {
        verificarNome(filmeRequest.getNome());
        verificarDataLanc(filmeRequest.getDataLancamento());
        verificarCapaFilme(filmeRequest.getCapaFilme());
        verificarDiretor(filmeRequest.getIdDiretor());
        verificarGeneroRepetido(filmeRequest.getGeneros());
        verificarEstudio(filmeRequest.getIdEstudio());
        verificaResumo(filmeRequest.getResumo());
        verificarPersonagens(filmeRequest.getPersonagens());
        criarPersonagensDoFilme(filmeRequest.getPersonagens());
        this.id++;
        Filme filme = new Filme (this.id, filmeRequest.getNome(), filmeRequest.getDataLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(), diretorService.consultarDiretor(filmeRequest.getIdDiretor()), estudioService.consultarEstudios(filmeRequest.getIdEstudio()), listarPersonagensDoFilme(filmeRequest.getPersonagens()) , filmeRequest.getResumo());
        fakeDatabase.persisteFilme(filme);
    }

    public List<Filme> listarFilmes() throws Exception {
        List<Filme> filmes = fakeDatabase.recuperaFilmes();
        if(filmes.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }
        return filmes;
    }

    public List<Filme> 

    private void criarPersonagensDoFilme(List<PersonagemRequest> personagemRequests) throws Exception {
        for(PersonagemRequest personagemRequest : personagemRequests) {
            personagemService.criarPersonagem(personagemRequest);
        }
    }

    private List<PersonagemAtor> listarPersonagensDoFilme(List<PersonagemRequest> personagemRequests) throws Exception {
        List<PersonagemAtor> personagensDoFilme = personagemService.listarPersonagens();
        for(PersonagemRequest personagemRequest : personagemRequests) {
            personagensDoFilme.stream().filter(e -> personagemRequest.getNomePersonagem().equals(e.getNomePersonagem())).collect(Collectors.toList());
        }
        return personagensDoFilme;
    }

    private void verificarNome(String nome) throws Exception {
        if ((nome == null) || (nome.isEmpty()) || nome.trim().isEmpty()) {
            throw new NomeNaoInformadoException();
        }
    }

    private void verificarDataLanc(LocalDate dataLanc) throws Exception {
        LocalDate atual = LocalDate.now();
        if (dataLanc.isAfter(atual)) {
            throw new AnoLancamentoInvalidoException(TipoDominioException.FILME.getSingular());
        }
    }

    private void verificarCapaFilme(String capaFilme) throws Exception {
        if ((capaFilme == null) || (capaFilme.isEmpty()) || capaFilme.trim().isEmpty()) {
            throw new CapaFilmeNaoInformadoException();
        }
    }

    private void verificarDiretor(Integer id) throws Exception {
        List<Diretor> diretores = diretorService.consultarDiretores();
        List<Diretor> existeDiretor = diretores.stream().filter(e -> e.getId().equals(id)).collect(Collectors.toList());
        if (existeDiretor.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
    }

    private void verificarGeneroRepetido(List<Genero> generos) throws GeneroRepetidoException {
        List<Genero> recebeGenero = new ArrayList<Genero>();
        for (Genero genero : generos) {
            if (recebeGenero.contains(genero)) {
                throw new GeneroRepetidoException();
            }
            recebeGenero.add(genero);
        }
    }

    private void verificarEstudio(Integer id) throws Exception {
        List<Estudio> estudios = estudioService.consultarEstudios();
        List<Estudio> existeEstudio = estudios.stream().filter(e -> e.getId().equals(id)).collect(Collectors.toList());
        if (existeEstudio.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.ESTUDIO.getSingular(), TipoDominioException.ESTUDIO.getPlural());
        }
    }

    private void verificaResumo(String resumo) throws Exception {
        if ((resumo == null) || (resumo.isEmpty()) || resumo.trim().isEmpty()) {
            throw new ResumoNaoInformadoException();
        }
        if (!resumo.contains(" ")) {
            throw new ResumoInvalidoException(TipoDominioException.FILME.getSingular());
        }
    }

    private void verificarPersonagens(List<PersonagemRequest> personagens) throws Exception {
        for(PersonagemRequest personagem : personagens) {
            personagemService.verificaPersonagem(personagem);
        }
    }

}




