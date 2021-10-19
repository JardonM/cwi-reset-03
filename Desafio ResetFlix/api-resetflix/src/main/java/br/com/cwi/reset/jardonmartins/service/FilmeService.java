package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;

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
    }
}
