package br.com.cesarschool.poo.titulos.mediators;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioAcao;
import br.com.cesarschool.poo.titulos.repositorios.RepositorioEntidadeOperadora;

import java.io.FileNotFoundException;
import java.io.IOException;

/*
 * Deve ser um singleton.
 * 
 * Deve ter um atributo repositorioEntidadeOperadora, do tipo RepositorioEntidadeOperadora, que deve
 * ser inicializado na sua declara��o. Este atributo ser� usado exclusivamente
 * pela classe, n�o tendo, portanto, m�todos set e get.
 * 
 * M�todos: 
 * 
 * pivate String validar(EntidadeOperadora): deve validar os dados do objeto recebido nos seguintes termos: 
 * identificador: deve ser maior que zero e menor que 100000 (1)
 * nome: deve ser preenchido, diferente de branco e de null (2). deve ter entre 5 e 60 caracteres (3).
 * data de validade: deve ser maior do que a data atual mais 180 dias (4). 
 * valorUnitario: deve ser maior que zero (5). 
 * O m�todo validar deve retornar null se o objeto estiver v�lido, e uma mensagem pertinente (ver abaixo)
 * se algum valor de atributo estiver inv�lido.
 * 
 * (1) - Identificador deve estar entre 100 e 1000000.
 * (2) - Nome deve ser preenchido.
 * (3) - Nome deve ter entre 10 e 100 caracteres.
 *
 * public String incluir(EntidadeOperadora entidade): deve chamar o m�todo validar. Se ele 
 * retornar null, deve incluir entidade no reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do validar for null e o retorno do incluir do 
 * reposit�rio for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Entidade j� existente", se o retorno do validar for null
 * e o retorno do reposit�rio for false. 
 *
 * public String alterar(EntidadeOperadora entidade): deve chamar o m�todo validar. Se ele 
 * retornar null, deve alterar entidade no reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do validar for null e o retorno do alterar do 
 * reposit�rio for true.
 * (2) a mensagem retornada pelo validar, se o retorno deste for diferente
 * de null.
 * (3) A mensagem "Entidade inexistente", se o retorno do validar for null
 * e o retorno do reposit�rio for false.
 * 
 * public String excluir(int identificador): deve validar o identificador. 
 * Se este for v�lido, deve chamar o excluir do reposit�rio. Retornos poss�veis:
 * (1) null, se o retorno do excluir do reposit�rio for true.
 * (2) A mensagem "Entidade inexistente", se o retorno do reposit�rio for false
 * ou se o identificador for inv�lido.
 * 
 * public EntidadeOperadora buscar(int identificador): deve validar o identificador.
 * Se este for v�lido, deve chamar o buscar do reposit�rio, retornando o 
 * que ele retornar. Se o identificador for inv�lido, retornar null. 
 */
public class MediatorEntidadeOperadora {
    private final RepositorioEntidadeOperadora repositorioEntidadeOperadora;

    private static MediatorEntidadeOperadora instancia;

    private MediatorEntidadeOperadora(){
        this.repositorioEntidadeOperadora = new RepositorioEntidadeOperadora();
    }

    public static MediatorEntidadeOperadora getInstancia() {
        if (instancia == null) {
            instancia = new MediatorEntidadeOperadora();
        }
        return instancia;
    }

    private String validar(EntidadeOperadora entidadeOperadora){

        String error = null;
        if (entidadeOperadora.getIdentificador() >= 1000000 || entidadeOperadora.getIdentificador() <= 100){
            error = "Identificador deve estar entre 100 e 1000000.";
        }
        else if (entidadeOperadora.getNome().isBlank() || entidadeOperadora.getNome() == null) {
            error = "Nome deve ser preenchido.";
        }
        else if (entidadeOperadora.getNome().length() < 10 || entidadeOperadora.getNome().length() > 100) {
            error = "Nome deve ter entre 10 e 100 caracteres.";
        }

        return error;
    }

    public String incluir(EntidadeOperadora entidade) throws IOException {
        String mensagemValidacao = validar(entidade);
        if (mensagemValidacao == null){
            if (!(repositorioEntidadeOperadora.incluirEntidadeOperadora(entidade))){
                mensagemValidacao = "Entidade já existente";
            }
        }

        return mensagemValidacao;
    }
    public String alterar(EntidadeOperadora entidade) throws IOException {
        String mensagemValidacao = validar(entidade);

        if (mensagemValidacao == null){
            if (!(repositorioEntidadeOperadora.alterarEntidadeOperadora(entidade))){
                mensagemValidacao = "Ação inexistente";
            }
        }

        return mensagemValidacao;
    }

    public String excluir(int identificador) throws IOException {
        if (99999 <= identificador || identificador <= 0){
            return null;
        }
        return repositorioEntidadeOperadora.excluirEntidadeOperadora(identificador) ? null : "Ação inexistente";
    }

    public EntidadeOperadora buscar(long identificador) throws FileNotFoundException {
        if (99999 <= identificador || identificador <= 0){
            return null;
        }
        return repositorioEntidadeOperadora.buscarEntidadeOperadora(identificador);
    }


}
