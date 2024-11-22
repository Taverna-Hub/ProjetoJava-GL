package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

/*
 * Deve ser um singleton.
 *
 * Deve ter um atributo repositorioAcao, do tipo RepositorioAcao, que deve
 * ser inicializado na sua declaracao. Este atributo sera usado exclusivamente
 * pela classe, nao tendo, portanto, metodos set e get.
 *
 * Metodos:
 *
 * pivate String validar(Acao acao): deve validar os dados do objeto recebido nos seguintes termos:
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 10 e 100 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 30 dias (4).
 * valorUnitario: deve ser maior que zero (5).
 * O metodo validar deve retornar null se o objeto estiver valido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver invalido.
 *
 * (1) - Identificador deve estar entre 1 e 99999.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 * (4) - Data de validade deve ter pelo menos 30 dias na frente da data atual.
 * (5) - Valor unitario deve ser maior que zero.
 *
 * public String incluir(Acao acao): deve chamar o metodo validar. Se ele
 * retornar null, deve incluir acao no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Acao ja existente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String alterar(Acao acao): deve chamar o metodo validar. Se ele
 * retornar null, deve alterar acao no repositório. Retornos possíveis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do
 * repositório for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Acao inexistente", se o retorno do validar for null
 * e o retorno do repositório for false.
 *
 * public String excluir(int identificador): deve validar o identificador.
 * Se este for valido, deve chamar o excluir do repositório. Retornos possíveis:
 * (1) null, se o retorno do excluir do repositório for true.
 * (2) A mensagem "Acao inexistente", se o retorno do repositório for false
 * ou se o identificador for invalido.
 *
 * public Acao buscar(int identificador): deve validar o identificador.
 * Se este for valido, deve chamar o buscar do repositório, retornando o
 * que ele retornar. Se o identificador for invalido, retornar null.
 */
public class MediatorAcao {

    private final RepositorioAcao repositorioAcao = new RepositorioAcao();

    private static MediatorAcao instancia;

    private MediatorAcao(){
    }

    public static MediatorAcao getInstancia() {
        if (instancia == null) {
            instancia = new MediatorAcao();
        }
        return instancia;
    }
    private String validar(Acao acao){

        String erro = null;

        if (99999 < acao.getIdentificador() || acao.getIdentificador() <= 0){
            erro = "Identificador deve estar entre 1 e 99999.";
        }

        else if (acao.getNome() == null || acao.getNome().isBlank()) {
            erro = "Nome deve ser preenchido.";
        }

        else if (acao.getNome().length() < 10 || acao.getNome().length() > 100) {
            erro = "Nome deve ter entre 10 e 100 caracteres.";
        }

        else if (ChronoUnit.DAYS.between(LocalDate.now(), acao.getDataDeValidade()) < 30) {
            erro = "Data de validade deve ter pelo menos 30 dias na frente da data atual.";
        }

        else if (acao.getValorUnitario() <= 0) {
            erro = "Valor unitario deve ser maior que zero.";
        }

        return erro;
    }

    public String incluir(Acao acao) throws IOException {

        String mensagemValidacao = validar(acao);

        if (mensagemValidacao == null){

            if (!(repositorioAcao.incluir(acao))){
            mensagemValidacao = "Acao ja existente";
        }
        }
        return mensagemValidacao;
    }

    public String alterar(Acao acao) throws IOException {
        String mensagemValidacao = validar(acao);

        if (mensagemValidacao == null){

            if (!(repositorioAcao.alterar(acao))){
                mensagemValidacao = "Acao inexistente";
            }
        }

        return mensagemValidacao;
    }

    public String excluir(int identificador) throws IOException {

        if (99999 <= identificador || identificador <= 0){
            return null;
        }
        return repositorioAcao.excluir(identificador) ? null : "Acao inexistente";
    }

    public Acao buscar(int identificador) throws FileNotFoundException {

        if (99999 <= identificador || identificador <= 0){
            return null;
        }
        return repositorioAcao.buscar(identificador);
    }

    public Acao[] buscarTodos() throws FileNotFoundException {
        return repositorioAcao.buscarTodos();
    }
}
