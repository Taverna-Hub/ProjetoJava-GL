package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
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
 * Deve gravar em e ler de um arquivo texto chamado TituloDivida.txt os dados dos objetos do tipo
 * TituloDivida. Seguem abaixo exemplos de linhas (identificador, nome, dataValidade, taxaJuros).
 *
    1;BRASIL;2024-12-12;10.5
    2;EUA;2026-01-01;1.5
    3;FRANCA;2027-11-11;2.5 
 * 
 * A inclus�o deve adicionar uma nova linha ao arquivo. N�o � permitido incluir 
 * identificador repetido. Neste caso, o m�todo deve retornar false. Inclus�o com 
 * sucesso, retorno true.
 * 
 * A altera��o deve substituir a linha atual por uma nova linha. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Altera��o com sucesso, retorno true.  
 *   
 * A exclus�o deve apagar a linha atual do arquivo. A linha deve ser 
 * localizada por identificador que, quando n�o encontrado, enseja retorno false. 
 * Exclus�o com sucesso, retorno true.
 * 
 * A busca deve localizar uma linha por identificador, materializar e retornar um 
 * objeto. Caso o identificador n�o seja encontrado no arquivo, retornar null.   
 */

public class RepositorioTituloDivida {
	File arquivoTitulo = new File("src/BDs/TituloDivida.txt");

	public boolean incluir(TituloDivida tituloDivida) throws IOException {
		if (buscar(tituloDivida.getIdentificador()) != null) {
			return false;
		}
		FileWriter escreverLinha = new FileWriter(arquivoTitulo, true);


		String identificadorString = String.valueOf(tituloDivida.getIdentificador());
		String dataString = String.valueOf(tituloDivida.getDataDeValidade());
		String valorString = String.valueOf(tituloDivida.getTaxaJuros());

		String linhaCompleta = identificadorString + ";" + tituloDivida.getNome() + ";" + dataString + ";" + valorString + ";\n";

		escreverLinha.write(linhaCompleta);
		escreverLinha.close();

		return true;
	}

	public boolean alterar(TituloDivida tituloDivida) throws IOException {

		if (buscar(tituloDivida.getIdentificador()) == null){
			return false;
		}

		Scanner SCAN = new Scanner(arquivoTitulo);
		List<String> linhasDoArquivo = new ArrayList<>();

		while (SCAN.hasNextLine()){
			linhasDoArquivo.add(SCAN.nextLine());

		}
		FileWriter escreverLinha = new FileWriter(arquivoTitulo);

		for (String linha : linhasDoArquivo){

			String[] arrayLinha = linha.split(";");

			int identificadorArray = Integer.parseInt(arrayLinha[0]);
			LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
			double TaxaDeJurosArray = Double.parseDouble(arrayLinha[3]);
			if (tituloDivida.getIdentificador() != identificadorArray){
				incluir(new TituloDivida(identificadorArray, arrayLinha[1], dataArray, TaxaDeJurosArray));

			}
			else {
				incluir(tituloDivida);

			}

		}

		return true;
	}

	public boolean excluir(int identificador) throws IOException {
		if (buscar(identificador) == null){
			return false;
		}

		Scanner SCAN = new Scanner(arquivoTitulo);
		List<String> linhasDoArquivo = new ArrayList<>();

		while (SCAN.hasNextLine()){
			linhasDoArquivo.add(SCAN.nextLine());

		}
		FileWriter escreverLinha = new FileWriter(arquivoTitulo);

		for (String linha : linhasDoArquivo){

			String[] arrayLinha = linha.split(";");

			int identificadorArray = Integer.parseInt(arrayLinha[0]);
			LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
			double taxaDeJurosArray = Double.parseDouble(arrayLinha[3]);
			if (identificador != identificadorArray){
				incluir(new TituloDivida(identificadorArray, arrayLinha[1], dataArray, taxaDeJurosArray));

			}

		}

		return true;
	}

	public TituloDivida buscar(int identificador) throws FileNotFoundException {
		Scanner scan = new Scanner(arquivoTitulo);
		while (scan.hasNextLine()) {

			String[] arrayLinha = scan.nextLine().split(";");

			int identificadorArray = Integer.parseInt(arrayLinha[0]);
			LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
			double TaxaDeJurosArray = Double.parseDouble(arrayLinha[3]);

			if (identificador == identificadorArray) {
				scan.close();
				return new TituloDivida(identificadorArray, arrayLinha[1], dataArray, TaxaDeJurosArray);
			}
		}
		scan.close();
		return null;
	}

	public List<TituloDivida> buscarTodos () throws FileNotFoundException {
		Scanner scan = new Scanner(arquivoTitulo);
		List<TituloDivida> listaTitulos = new ArrayList<>();
		while (scan.hasNextLine()) {

			String[] arrayLinha = scan.nextLine().split(";");

			int identificadorArray = Integer.parseInt(arrayLinha[0]);
			LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
			double TaxaDeJurosArray = Double.parseDouble(arrayLinha[3]);

			listaTitulos.add(new TituloDivida(identificadorArray, arrayLinha[1], dataArray, TaxaDeJurosArray));


		}
		scan.close();
		return listaTitulos;
	}
}
