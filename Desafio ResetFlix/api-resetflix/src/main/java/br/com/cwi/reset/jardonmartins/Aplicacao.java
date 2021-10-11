package br.com.cwi.reset.jardonmartins;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.Diretor;
import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;
import br.com.cwi.reset.jardonmartins.exception.AtorException;
import br.com.cwi.reset.jardonmartins.exception.DiretorException;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.request.DiretorRequest;
import br.com.cwi.reset.jardonmartins.service.AtorService;
import br.com.cwi.reset.jardonmartins.service.DiretorService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) throws AtorException, DiretorException {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        atorService.criarAtor(atorRequest);

        List<Ator> atores = fakeDatabase.recuperaAtores();

        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());

        System.out.println(atorService.consultarAtor(3).getNome());

        for(Ator ator : atorService.listarAtoresEmAtividade("Will")) {
            System.out.println(ator.getNome());
        }

        for(Ator ator : atorService.consultarAtores()) {
            System.out.println(ator.getNome());
        }

        DiretorService diretorService = new DiretorService(fakeDatabase);
        DiretorRequest diretorRequest = new DiretorRequest("Diretão Balacobaco", LocalDate.of(1950, Month.AUGUST, 20), 2000);
        diretorService.cadastrarDiretor(diretorRequest);

        for(Diretor diretor : diretorService.listarDiretores("Dire")){
            System.out.println(diretor.getNome());
        }

        for(Diretor diretor : diretorService.listarDiretores()) {
            System.out.println(diretor.getNome());
        }


    }
}