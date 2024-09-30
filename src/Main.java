import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {
        Acao a1 = new Acao(4, "CAPS", LocalDate.now(), 20.00);
        boolean a = new RepositorioAcao().incluir(a1);
        System.out.println(a);
//       boolean b = new RepositorioAcao().excluir(2);
//       System.out.println(b);

    }
}
