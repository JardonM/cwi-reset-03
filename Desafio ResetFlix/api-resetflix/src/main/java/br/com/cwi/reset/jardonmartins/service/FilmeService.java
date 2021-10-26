package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.domain.*;
import br.com.cwi.reset.jardonmartins.exception.*;
import br.com.cwi.reset.jardonmartins.repository.EstudioRepository;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.repository.FilmeRepository;
import br.com.cwi.reset.jardonmartins.request.FilmeRequest;
import br.com.cwi.reset.jardonmartins.request.PersonagemRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilmeService {


    @Autowired
    private FilmeRepository repository;
    @Autowired
    private AtorService atorService;
    @Autowired
    private DiretorService diretorService;
    @Autowired
    private EstudioService estudioService;
    @Autowired
    private PersonagemService personagemService;



    public void criarFilme(FilmeRequest filmeRequest) throws Exception {
        verificarNome(filmeRequest.getNome());
        verificarDataLanc(filmeRequest.getAnoLancamento());
        verificarCapaFilme(filmeRequest.getCapaFilme());
        verificarDiretor(filmeRequest.getIdDiretor());
        verificarGenero(filmeRequest.getGeneros());
        verificarEstudio(filmeRequest.getIdEstudio());
        verificaResumo(filmeRequest.getResumo());
        verificarPersonagens(filmeRequest.getPersonagens());
        verificarFilmeRepetido(filmeRequest);
        criarPersonagensDoFilme(filmeRequest.getPersonagens());
        Filme filme = new Filme (filmeRequest.getNome(), filmeRequest.getAnoLancamento(), filmeRequest.getCapaFilme(), filmeRequest.getGeneros(),  estudioService.consultarEstudios(filmeRequest.getIdEstudio()), diretorService.consultarDiretor(filmeRequest.getIdDiretor()), listarPersonagensDoFilme(filmeRequest.getPersonagens()) , filmeRequest.getResumo());
        repository.save(filme);
    }

    public List<Filme> listarFilmes() throws Exception {
        List<Filme> filmes = repository.findAll();
        if(filmes.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.FILME.getSingular(), TipoDominioException.FILME.getPlural());
        }
        return filmes;
    }

    public List<Filme> consultarFilmes(String nomeFilme,
                                    String nomeDiretor,
                                    String nomePersonagem,
                                    String nomeAtor) throws Exception{
        List<Filme> filmes = listarFilmes();
        List<Filme> filtrarNomePersonagem = listarFilmesPorPersonagem(nomePersonagem, filmes);
        List<Filme> filtrarNomeAtor = listarFilmesPorAtor(nomeAtor, filtrarNomePersonagem);
        List<Filme> filtrarNomeDiretor = listarFilmesPorDiretor(nomeDiretor, filtrarNomeAtor);
        List<Filme> filtroFinal = listarFilmesPorNome(nomeFilme, filtrarNomeDiretor);
        if(filtroFinal.isEmpty()){
            throw new NenhumFilmeEncontradoException(String.format(
                    "Filme não encontrado com os filtros nomeFilme=%s, nomeDiretor=%s, nomePersonagem=%s, nomeAtor=%s, favor informar outros filtros.",
                    nomeFilme,
                    nomeDiretor,
                    nomePersonagem,
                    nomeAtor));
        }
        return filtroFinal;
    }

    public List<Filme> listarFilmesPorNome(String nome, List<Filme> filmes) throws Exception {
        if(nome == null) {
            return filmes;
        }
        List<Filme> filmesEncontrados = filmes.stream().filter(e -> e.getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        if(filmesEncontrados.isEmpty()){
            return filmes;
        }
        return filmesEncontrados;
    }

    public List<Filme> listarFilmesPorDiretor(String nome, List<Filme> filmes) throws Exception {
        if(nome == null) {
            return filmes;
        }
        List<Filme> filmesEncontrados = filmes.stream().filter(e -> e.getDiretor().getNome().toLowerCase().contains(nome.toLowerCase())).collect(Collectors.toList());
        if(filmesEncontrados.isEmpty()){
            return filmes;
        }
        return filmesEncontrados;
    }

    public List<Filme> listarFilmesPorPersonagem(String nome, List<Filme> filmes) throws Exception {
        if(nome == null) {
            return filmes;
        }
        List<Filme> filmesEncontrados = new ArrayList<Filme>();
        for(Filme filme : filmes) {
            for(PersonagemAtor personagem : filme.getPersonagens()){
                if(personagem.getNomePersonagem().equalsIgnoreCase(nome)){
                    filmesEncontrados.add(filme);
                }
            }
        }
        if(filmesEncontrados.isEmpty()){
            return filmes;
        }
        return filmesEncontrados;
    }

    public List<Filme> listarFilmesPorAtor(String nome, List<Filme> filmes) throws Exception {
        if(nome == null) {
            return filmes;
        }
        List<Filme> filmesEncontrados = new ArrayList<Filme>();
        for(Filme filme : filmes) {
            for(PersonagemAtor personagem : filme.getPersonagens()){
                if(personagem.getAtor().getNome().equalsIgnoreCase(nome)){
                    filmesEncontrados.add(filme);
                }
            }
        }
        if(filmesEncontrados.isEmpty()){
            return filmes;
        }
        return filmesEncontrados;
    }

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

    public void removerFilme(Integer id) throws Exception {
        verificaID(id);
        List<Filme> filmes = repository.findAll();
        for(Filme filme : filmes) {
            if(filme.getId().equals(id)) {
                List<PersonagemAtor> personagensParaDeletar = filme.getPersonagens();
                personagemService.deletarPersonagens(personagensParaDeletar);
                repository.delete(filme);
            }
        }
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

    private void verificarDataLanc(Integer anoLanc) throws Exception {
        if(anoLanc == null) {
            throw new AnoLancamentoInvalidoException(TipoDominioException.FILME.getSingular());
        }
        LocalDate atual = LocalDate.now();
        if (anoLanc > atual.getYear()) {
            throw new AnoLancamentoInvalidoException(TipoDominioException.FILME.getSingular());
        }
    }

    private void verificarCapaFilme(String capaFilme) throws Exception {
        if ((capaFilme == null) || (capaFilme.isEmpty()) || capaFilme.trim().isEmpty()) {
            throw new CapaFilmeNaoInformadoException();
        }
    }

    private void verificarDiretor(Integer id) throws Exception {
        if(id == null) {
            throw new CampoNaoInformadoException("id do diretor");
        }
        List<Diretor> diretores = diretorService.listarDiretores();
        List<Diretor> existeDiretor = diretores.stream().filter(e -> e.getId().equals(id)).collect(Collectors.toList());
        if (existeDiretor.isEmpty()) {
            throw new ListaVaziaException(TipoDominioException.DIRETOR.getSingular(), TipoDominioException.DIRETOR.getPlural());
        }
    }

    private void verificarGenero(List<Genero> generos) throws Exception {
        if(generos.isEmpty()){
            throw new GeneroNaoInformadoException();
        }
        List<Genero> recebeGenero = new ArrayList<Genero>();
        for (Genero genero : generos) {
            if (recebeGenero.contains(genero)) {
                throw new GeneroRepetidoException();
            }
            recebeGenero.add(genero);
        }
    }

    private void verificarEstudio(Integer id) throws Exception {
        if(id == null) {
            throw new CampoNaoInformadoException("id do estudio");
        }
        List<Estudio> estudios = estudioService.listarEstudios();
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
        if(personagens.isEmpty()){
            throw new ListaVaziaException(TipoDominioException.PERSONAGEM.getSingular(), TipoDominioException.PERSONAGEM.getPlural());
        }
        for(PersonagemRequest personagem : personagens) {
            personagemService.verificaPersonagem(personagem);
        }
    }

    private void verificarFilmeRepetido(FilmeRequest filmeRequest) throws Exception {
        List<Filme> filmes = repository.findAll();
        for(Filme filme : filmes) {
            if((filme.getNome().equals(filmeRequest.getNome())) &&
            (filme.getDiretor().getNome().equals(diretorService.consultarDiretor(filmeRequest.getIdDiretor()).getNome())) &&
            (filme.getAnoLancamento().equals(filmeRequest.getAnoLancamento())))
            {
                throw new FilmeJaCadastradoException();
            }
        }
    }

}




