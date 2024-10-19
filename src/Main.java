import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTransacao;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) throws IOException {

        EntidadeOperadora ec = new EntidadeOperadora(4, "Visa", true);
        EntidadeOperadora ed = new EntidadeOperadora(5, "Ourocard", true);
        Acao acao = new Acao(1,"CESAR", LocalDate.now(), 30.332);
        ec.creditarSaldoAcao(20);
        ed.creditarSaldoAcao(20);
        TituloDivida td = new TituloDivida(7, "USA", LocalDate.of(2024, 10, 7), 20.0);

        new RepositorioEntidadeOperadora().incluirEntidadeOperadora(ec);
        new RepositorioEntidadeOperadora().incluirEntidadeOperadora(ed);
        new RepositorioAcao().incluir(acao);
        new RepositorioTituloDivida().incluir(td);
        Transacao transacao = new Transacao(ec, ed, acao, td, 200.00, LocalDateTime.now());
        new RepositorioTransacao().incluir(transacao);
        System.out.println(ec.getSaldoAcao());
        System.out.println(ed.getSaldoAcao());
        Transacao[] t1 = new RepositorioTransacao().buscarPorEntidadeCredora(4);

        for (Transacao t : t1){
            System.out.println(t.getEntidadeCredito().getNome());
            System.out.println(t.getEntidadeDebito().getNome());
            System.out.println(t.getAcao().getNome());
            System.out.println(t.getTituloDivida().getNome());
            System.out.println(t.getValorOperacao());
            System.out.println(t.getDataHoraOperacao());
        }

    }

}
