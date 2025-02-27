package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioTituloDivida;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/*
 * Deve ser um singleton.
 * 
 * Deve ter um atributo repositorioTituloDivida, do tipo RepositorioTituloDivida, que deve 
 * ser inicializado na sua declara��o. Este atributo ser� usado exclusivamente
 * pela classe, n�o tendo, portanto, m�todos set e get.
 * 
 * M�todos: 
 * 
 * pivate String validar(TituloDivida titulo): deve validar os dados do objeto recebido nos seguintes termos: 
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 10 e 100 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 180 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O m�todo validar deve retornar null se o objeto estiver v�lido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inv�lido.
 * 
 * (1) - Identificador deve estar entre 1 e 99999.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 * (4) - Data de validade deve ter pelo menos 180 dias na frente da data atual.
 * (5) - Taxa de juros deve ser maior ou igual a zero.
 *
 * public String incluir(TituloDivida titulo): deve chamar o m�todo validar. Se ele 
 * retornar null, deve incluir titulo no reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do 
 * reposit�rio for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "T�tulo j� existente", se o retorno do validar for null
 * e o retorno do reposit�rio for false.
 *
 * public String alterar(TituloDivida titulo): deve chamar o m�todo validar. Se ele 
 * retornar null, deve alterar titulo no reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do 
 * reposit�rio for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "T�tulo inexistente", se o retorno do validar for null
 * e o retorno do reposit�rio for false.
 * 
 * public String excluir(int identificador): deve validar o identificador. 
 * Se este for v�lido, deve chamar o excluir do reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do excluir do reposit�rio for true.
 * (2) A mensagem "T�tulo inexistente", se o retorno do reposit�rio for false
 * ou se o identificador for inv�lido.
 * 
 * public TituloDivida buscar(int identificador): deve validar o identificador.
 * Se este for v�lido, deve chamar o buscar do reposit�rio, retornando o 
 * que ele retornar. Se o identificador for inv�lido, retornar null. 
 */
public class MediatorTituloDivida {

    private RepositorioTituloDivida repositorioTituloDivida =  new RepositorioTituloDivida();

    private static MediatorTituloDivida instancia;

    private MediatorTituloDivida() {
    }

    public static MediatorTituloDivida getInstancia() {
        if (instancia == null) {
            instancia = new MediatorTituloDivida();
        }
        return instancia;
    }

    private String validar(TituloDivida tituloDivida) {

        String erro = null;

        if (tituloDivida.getIdentificador() <= 0 || tituloDivida.getIdentificador() >= 100000) {
            erro = "Identificador deve estar entre 1 e 99999.";
        }

        if (tituloDivida.getNome() == null || tituloDivida.getNome().isBlank()) {
            erro = "Nome deve ser preenchido.";
        }

        else if (tituloDivida.getNome().length() < 10 || tituloDivida.getNome().length() > 100) {
            erro = "Nome deve ter entre 10 e 100 caracteres.";
        }

        LocalDate dataAtual = LocalDate.now();
        if (!tituloDivida.getDataDeValidade().isAfter(dataAtual.plusDays(180))) {
            erro = "Data de validade deve ter pelo menos 180 dias na frente da data atual.";

        }
        if (tituloDivida.getTaxaJuros() < 0) {
            erro = "Taxa de juros deve ser maior ou igual a zero.";
        }

        return erro;
    }

    public String incluir(TituloDivida tituloDivida) throws IOException {

        String mensagemValidacao = validar(tituloDivida);

        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }

        boolean incluido = repositorioTituloDivida.incluir(tituloDivida);

        if (incluido) {
            return null;
        }
        else {
            return "Titulo ja existente.";
        }
    }

    public String alterar(TituloDivida tituloDivida) throws IOException {

        String mensagemValidacao = validar(tituloDivida);

        if (mensagemValidacao != null) {
            return mensagemValidacao;
        }
        else if (repositorioTituloDivida.alterar(tituloDivida)) {
            return null;
        }
        else {
            return "Titulo ja existente";
        }

    }

    public String excluir(int identificador) throws IOException {

        return repositorioTituloDivida.excluir(identificador) ? null : "Titulo Inexistente";
    }

    public TituloDivida buscar(int identificador) throws IOException {

        if (identificador <= 0 || identificador > 100000) {
            return null;
        }
        return repositorioTituloDivida.buscar(identificador);

    }

    public TituloDivida[] buscarTodos() throws FileNotFoundException {
        return repositorioTituloDivida.buscarTodos();
    }



}
