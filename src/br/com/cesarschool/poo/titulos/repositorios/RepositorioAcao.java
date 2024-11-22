package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * Deve gravar em e ler de um arquivo texto chamado Acao.txt os dados dos objetos do tipo
 * Acao. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, valorUnitario)
 * 
    1;PETROBRAS;2024-12-12;30.33
    2;BANCO DO BRASIL;2026-01-01;21.21
    3;CORREIOS;2027-11-11;6.12 
 * 
 * A inclusão deve adicionar uma nova linha ao arquivo. Não é permitido incluir
 * identificador repetido. Neste caso, o método deve retornar false. Inclusão com
 * sucesso, retorno true.
 * 
 * A alteração deve substituir a linha atual por uma nova linha. A linha deve ser
 * localizada por identificador que, quando n�o encontrado, seja retorno false.
 * Alteração com sucesso, retorno true.
 *   
 * A exclusão deve apagar a linha atual do arquivo. A linha deve ser
 * localizada por identificador que, quando não encontrado, seja retorno false.
 * Exclusão com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador não seja encontrado no arquivo, retornar null.
 */
public class RepositorioAcao extends RepositorioGeral{

	public RepositorioAcao() {
		super(Acao.class);
	}

	public boolean incluir(Acao acao) {
		return false;
	}

	public boolean alterar(Acao acao){
		return false;
	}

	public boolean excluir(int identificador) {
		return false;
	}

	public Acao buscar(int identificador) {
		return null;
	}

	public Acao[] buscarTodos () {
		return null;
	}

}
