package br.com.cesarschool.poo.titulos.entidades;
/*
 * Esta classe deve ter os seguintes atributos:
 * identificador, do tipo long
 * nome, do tipo String
 * autorizadoAcao, do tipo double
 * saldoAcao, do tipo double
 * saldoTituloDivida, do tipo double
 * 
 * Deve ter um construtor p�blico que inicializa os atributos identificador, nome
 * e autorizadoAcao. Deve ter m�todos set/get p�blicos para os atributos identificador, nome
 * e autorizadoAcao. O atributo identificador � read-only fora da classe.
 * 
 * Os atributos saldoAcao e saldoTituloDivida devem ter apenas m�todos get p�blicos.
 * 
 * Outros m�todos p�blicos:
 * 
 *  void creditarSaldoAcao(double valor): deve adicionar valor ao saldoAcao
 *  void debitarSaldoAcao(double valor): deve diminuir valor de saldoAcao
 *  void creditarSaldoTituloDivida(double valor): deve adicionar valor ao saldoTituloDivida
 *  void debitarSaldoTituloDivida(double valor): deve diminuir valor de saldoTituloDivida  
 */
public class EntidadeOperadora {

    private long indentificador;
    private String nome;
    private double autorizadoAcao;
    private double saldoAcao;
    private double saldoTituloDivida;

    public EntidadeOperadora(long indentificador, String nome, double autorizadoAcao) {
        this.indentificador = indentificador;
        this.nome = nome;
        this.autorizadoAcao = autorizadoAcao;
    }

    public long getIndentificador() {
        return indentificador;
    }

    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getAutorizadoAcao() {
        return autorizadoAcao;
    }

    public double getSaldoAcao() {
        return saldoAcao;
    }

    public double getSaldoTituloDivida() {
        return saldoTituloDivida;
    }

    public void setAutorizadoAcao(double autorizadoAcao) {
        this.autorizadoAcao = autorizadoAcao;
    }

    public void creditarSaldoAcao(double valor){
        saldoAcao += valor;
    }
    public void debitarSaldoAcao(double valor){
        saldoAcao -= valor;
    }
    public void creditarSaldoTituloDivida(double valor){
        saldoTituloDivida += valor;
    }
    public void debitarSaldoTituloDivida(double valor){
        saldoTituloDivida -= valor;
    }
}
