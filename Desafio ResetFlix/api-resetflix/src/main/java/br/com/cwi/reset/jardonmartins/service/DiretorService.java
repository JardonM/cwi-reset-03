package br.com.cwi.reset.jardonmartins.service;

import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;

public class DiretorService {

    private FakeDatabase fakeDatabase;

    public DiretorService(FakeDatabase fakeDatabase) {
        this.fakeDatabase = fakeDatabase;
    }


}
