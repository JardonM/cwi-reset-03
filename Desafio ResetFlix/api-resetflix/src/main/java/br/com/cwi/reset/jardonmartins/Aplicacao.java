package br.com.cwi.reset.jardonmartins;

import br.com.cwi.reset.jardonmartins.domain.Ator;
import br.com.cwi.reset.jardonmartins.domain.StatusCarreira;
import br.com.cwi.reset.jardonmartins.repository.FakeDatabase;
import br.com.cwi.reset.jardonmartins.request.AtorRequest;
import br.com.cwi.reset.jardonmartins.request.DiretorRequest;
import br.com.cwi.reset.jardonmartins.service.AtorService;
import br.com.cwi.reset.jardonmartins.service.DiretorService;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class Aplicacao {

    public static void main(String[] args) throws Exception {
        FakeDatabase fakeDatabase = new FakeDatabase();

        AtorService atorService = new AtorService(fakeDatabase);

        String nome = "Will Smith";
        LocalDate dataNascimento = LocalDate.of(1968, Month.SEPTEMBER, 25);
        StatusCarreira statusCarreira = StatusCarreira.EM_ATIVIDADE;
        Integer anoInicioAtividade = 1986;
        AtorRequest atorRequest = new AtorRequest(nome, dataNascimento, statusCarreira, anoInicioAtividade);

        atorService.criarAtor(atorRequest);

        DiretorService diretorService = new DiretorService(fakeDatabase);
        DiretorRequest diretorRequest = new DiretorRequest("Diretão Balacobaco", LocalDate.of(1950, Month.AUGUST, 20), 2000);
        diretorService.cadastrarDiretor(diretorRequest);

        List<Ator> atores = fakeDatabase.recuperaAtores();

        System.out.println("Deve conter 1 ator, quantidade encontrada: " + atores.size());
        System.out.println("Primeiro ator deve ser 'Will Smith', valor encontrado: " + atores.get(0).getNome());

        System.out.println(atorService.consultarAtor(1).getNome());
        atorService.listarAtoresEmAtividade("Will").stream().forEach(e -> System.out.println(e.getNome()));
        atorService.consultarAtores().stream().forEach(e -> System.out.println(e.getNome()));



        diretorService.listarDiretores("Dire").stream().forEach(e -> System.out.println(e.getNome()));
        diretorService.listarDiretores().stream().forEach(e -> System.out.println(e.getNome()));
        System.out.println(diretorService.consultarDiretor(1).getNome());



    }
}