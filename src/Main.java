import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {

        TituloDivida t1 = new TituloDivida(3, "Canada", LocalDate.now(), 20.00);

        System.out.println(new RepositorioTituloDivida().alterar(t1));

    }
}
