import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {

        TituloDivida t1 = new TituloDivida(3, "Canada", LocalDate.now(), 20.00);

        System.out.println(new RepositorioTituloDivida().alterarTituloDivida(t1));

        RepositorioEntidadeOperadora entidadeOperadora = new RepositorioEntidadeOperadora();

        // validando funcionamento RepositorioEntidadeOperadora
        // criando objeto
        EntidadeOperadora acaoEntidadeOperadora = new EntidadeOperadora(4, "CBF", 35.00);
        acaoEntidadeOperadora.creditarSaldoAcao(20);
        acaoEntidadeOperadora.creditarSaldoTituloDivida(30);
        // adicionando ao txt
        System.out.println("Incluindo EntidadeOperadora: " + entidadeOperadora.incluirEntidadeOperadora(acaoEntidadeOperadora));

        // buscando o objeto
        System.out.println("Buscando EntidadeOperadora: " + entidadeOperadora.buscarEntidadeOperaradora(4).hashCode());

        // alterando ele
        EntidadeOperadora ae2 = new EntidadeOperadora(1, "Cobol", 25.00);
        ae2.debitarSaldoAcao(25);
        ae2.debitarSaldoTituloDivida(65);

        System.out.println("Alterando EntidadeOperadora: " + entidadeOperadora.alterarEntidadeOperadora(ae2));

        // removendo objeto
        System.out.println("Excluindo EntidadeOperadora: " + entidadeOperadora.excluirEntidadeOperadora(4));
        System.out.println("Buscando pós exclusão: " + entidadeOperadora.buscarEntidadeOperaradora(4));

        System.out.println("Re-confirmando exclusão: " + entidadeOperadora.buscarEntidadeOperaradora(4));

    }

}
