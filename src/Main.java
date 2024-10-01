import br.com.cesarschool.poo.titulos.entidades.Acao;
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
        Acao acaoEntidadeOperadora = new Acao(4, "APPLE", LocalDate.now(), 30.89);

        // adicionando ao txt
        System.out.println("Incluindo EntidadeOperadora: " + entidadeOperadora.incluirEntidadeOperadora(acaoEntidadeOperadora));

        // buscando o objeto
        System.out.println("Buscando EntidadeOperadora: " + entidadeOperadora.buscarEntidadeOperaradora(4));

        // alterando ele
        Acao acaoEntidadeOperadoraAlterada = new Acao(4, "IBM", LocalDate.now(), 32.89);
        System.out.println("Alterando EntidadeOperadora: " + entidadeOperadora.alterarEntidadeOperadora(acaoEntidadeOperadoraAlterada));

        // removendo objeto
        System.out.println("Excluindo EntidadeOperadora: " + entidadeOperadora.excluirEntidadeOperadora(4));
        System.out.println("Buscando pós exclusão: " + entidadeOperadora.buscarEntidadeOperaradora(4));

        System.out.println("Re-confirmando exclusão: " + entidadeOperadora.buscarEntidadeOperaradora(4));

    }

}
