package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;

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
public class RepositorioAcao {

	File arquivoAcao = new File("./Acao.txt");

	public boolean incluir(Acao acao) throws IOException {

		if (buscar(acao.getIdentificador()) != null) {
			return false;
		}

		FileWriter escreverLinha = new FileWriter(arquivoAcao);

		String identificadorString = String.valueOf(acao.getIdentificador());
		String dataString = String.valueOf(acao.getDataDeValidade());
		String valorString = String.valueOf(acao.getValorUnitario());

		String linhaCompleta = identificadorString + ";" + acao.getNome() + ";" + dataString + ";" + valorString + ";";

		escreverLinha.write(linhaCompleta);
		escreverLinha.close();

		return true;
	}
	// OK
	//confirmar com monitor
	public boolean alterar(Acao acao) throws IOException {
		if (buscar(acao.getIdentificador()) == null){
			return false;
		}
		Scanner SCAN = new Scanner(arquivoAcao);
		Scanner SCANNERTECLADO = new Scanner(System.in);
		List<String> linhasDoArquivo = new ArrayList<>();

		while (SCAN.hasNextLine()){
			if (!SCAN.nextLine().startsWith(String.valueOf(acao.getIdentificador()))){
				linhasDoArquivo.add(SCAN.nextLine());
			}
			else {
				incluir(new Acao(SCANNERTECLADO.nextInt(), SCANNERTECLADO.nextLine(), LocalDate.now(), SCANNERTECLADO.nextDouble()));
			}
		}

		return true;
	}


	public boolean excluir(int identificador) throws IOException {
		if (buscar(identificador) == null){
			return false;
		}
		Scanner SCAN = new Scanner(arquivoAcao);
		List<String> linhasDoArquivo = new ArrayList<>();

		while (SCAN.hasNextLine()){
			if (!SCAN.nextLine().startsWith(String.valueOf(identificador))){
				linhasDoArquivo.add(SCAN.nextLine());
			}
		}

		for (String linha : linhasDoArquivo){
			String[] arrayLinha = linha.split(";", 4);

			int identificadorArray = Integer.parseInt(arrayLinha[0]);
			LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
			double valorUnitarioArray = Double.parseDouble(arrayLinha[3]);

			incluir(new Acao(identificadorArray, arrayLinha[1], dataArray, valorUnitarioArray));

		}


		return true;
	}

	public Acao buscar(int identificador) throws FileNotFoundException {

		Scanner scan = new Scanner(arquivoAcao);
		while (scan.hasNextLine()) {

			String[] arrayLinha = scan.nextLine().split(";", 4);

			int identificadorArray = Integer.parseInt(arrayLinha[0]);
			LocalDate dataArray = LocalDate.parse(arrayLinha[2]);
			double valorUnitarioArray = Double.parseDouble(arrayLinha[3]);

			if (identificador == identificadorArray) {
				scan.close();
				return new Acao(identificadorArray, arrayLinha[1], dataArray, valorUnitarioArray);
			}
		}
		scan.close();
		return null;
	}
	// OK

}
