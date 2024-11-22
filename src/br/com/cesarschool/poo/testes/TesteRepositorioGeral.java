package br.com.cesarschool.poo.testes;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;
import br.com.cesarschool.poo.titulos.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;

public class TesteRepositorioGeral extends TesteGeral {
    @Test
    public void testDAO00() {
        RepositorioGeral dao = new RepositorioAcao();
        DAOSerializadorObjetos dso = dao.getDao();
        Assertions.assertNotNull(dso);
    }
    @Test
    public void testDAO01() {
        Class<?> c = RepositorioGeral.class;
        Assertions.assertEquals(c.getModifiers(), 1025);
        try {
            Method m = c.getMethod("getClasseEntidade", null);
            Assertions.assertEquals(m.getModifiers(), 1025);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail();
        }
    }
    @Test
    public void testDAO02() {
        RepositorioGeral dao = new RepositorioAcao();
        Assertions.assertEquals(dao.getClasseEntidade(), Acao.class);
    }
    @Test
    public void testDAO03() {
        RepositorioGeral dao = new RepositorioTituloDivida();
        Assertions.assertEquals(dao.getClasseEntidade(), TituloDivida.class);

    }
    @Test
    public void testDAO04() {
        RepositorioGeral dao = new RepositorioEntidadeOperadora();
        Assertions.assertEquals(dao.getClasseEntidade(), EntidadeOperadora.class);

    }
    @Test
    public void testDAO05() {
        RepositorioGeral dao = new RepositorioTransacao();
        Assertions.assertEquals(dao.getClasseEntidade(), Transacao.class);

    }
}
