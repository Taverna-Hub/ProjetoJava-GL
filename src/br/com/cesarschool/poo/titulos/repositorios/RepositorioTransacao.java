package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.String.valueOf;

/*
 * Deve gravar em e ler de um arquivo texto chamado Transacao.txt os dados dos objetos do tipo
 * Transacao. Seguem abaixo exemplos de linhas 
 * De entidadeCredito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De entidadeDebito: identificador, nome, autorizadoAcao, saldoAcao, saldoTituloDivida.
 * De acao: (identificador, nome, dataValidade, valorUnitario) OU null
 * De tituloDivida: (identificador, nome, dataValidade, taxaJuros) OU null.
 * valorOperacao, dataHoraOperacao
 *
 *   002192;BCB;true;0.00;1890220034.0; 001112;BOFA;true;12900000210.00;3564234127.0; 1;PETROBRAS;2024-12-12;30.33; null; 100000.0;2024-01-01 12:22:21
 *   002192;BCB;false;0.00;1890220034.0;001112;BOFA;true;12900000210.00;3564234127.0;null;3;FRANCA;2027-11-11;2.5;100000.0;2024-01-01 12:22:21
 *
 * A inclus�o deve adicionar uma nova linha ao arquivo. 
 * 
 * A busca deve retornar um array de transa��es cuja entidadeCredito tenha identificador igual ao
 * recebido como par�metro.  
 */
public class RepositorioTransacao {
	File arquivoTransacao = new File("src/Bds/Transacao.txt");

	public void incluir(Transacao transacao) throws IOException {

			boolean ehAcao = true;
		if (transacao.getAcao() == null){
			ehAcao = false;
		}

		FileWriter escreverLinha = new FileWriter(arquivoTransacao, true);

		EntidadeOperadora entidadeCredito = new RepositorioEntidadeOperadora().buscar(transacao.getEntidadeCredito().getIdentificador());
		EntidadeOperadora entidadeDebito = new RepositorioEntidadeOperadora().buscar(transacao.getEntidadeDebito().getIdentificador());
		Acao acao;
		TituloDivida tituloDivida;
		if (ehAcao){
			 acao = new RepositorioAcao().buscar(transacao.getAcao().getIdentificador());
			 tituloDivida = null;
		}
		else {
			 acao = null;
			 tituloDivida = new RepositorioTituloDivida().buscar(transacao.getTituloDivida().getIdentificador());

		}
		double valorOperacao = transacao.getValorOperacao();
		LocalDateTime dataHoraOperacao = transacao.getDataHoraOperacao();

		String entidadeCreditoString =
				entidadeCredito.getIdentificador() + ";"
				+ entidadeCredito.getNome()  + ";"
				+ entidadeCredito.getAutorizadoAcao()  + ";"
				+ entidadeCredito.getSaldoAcao()  + ";"
				+ entidadeCredito.getSaldoTituloDivida();
		String entidadeDebitoString =
				entidadeDebito.getIdentificador() + ";"
				+ entidadeDebito.getNome()  + ";"
				+ entidadeDebito.getAutorizadoAcao()  + ";"
				+ entidadeDebito.getSaldoAcao()  + ";"
				+ entidadeDebito.getSaldoTituloDivida();
		String acaoString = null;
		String tituloDividaString = null;
				if (ehAcao){
		 acaoString =
				acao.getIdentificador() + ";"
				+ acao.getNome()  + ";"
				+ acao.getDataDeValidade()  + ";"
				+ acao.getValorUnitario();
				}
		if (!ehAcao){
		 tituloDividaString =
				tituloDivida.getIdentificador()  + ";"
				+ tituloDivida.getNome()  + ";"
				+ tituloDivida.getDataDeValidade()  + ";"
				+ tituloDivida.getTaxaJuros();
		}



		String LinhaCompleta =
				entidadeCreditoString + ";"
				+ entidadeDebitoString + ";"
				+ acaoString + ";"
				+ tituloDividaString + ";"
				+ valorOperacao + ";"
				+ dataHoraOperacao.toString() + ";";
		escreverLinha.write(LinhaCompleta + "\n");
		escreverLinha.close();

	}

	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) throws IOException {
		EntidadeOperadora entidadeCredito = new RepositorioEntidadeOperadora().buscar(identificadorEntidadeCredito);

		if (entidadeCredito == null){
			return null;
		}
		int cont = 0;
		Scanner SCAN = new Scanner(arquivoTransacao);
		List<String[]> listaCredora = new ArrayList<>() ;

		while (SCAN.hasNextLine()){
			String[] arrayLinha = SCAN.nextLine().split(";");
			if (Integer.parseInt(arrayLinha[0]) == entidadeCredito.getIdentificador()){
				listaCredora.add(arrayLinha);
				cont++;
			}
		}
		SCAN.close();

		Transacao[] arrayDeTransacoes = new Transacao[cont];
		int i = 0;
		for (String[] linha : listaCredora){

			long identificadorEntidadeDebito = Integer.parseInt(linha[5]);
			int identificadorAcao = Integer.parseInt(linha[10]);
			int identificadorTituloDivida = Integer.parseInt(linha[14]);
			double valorOperacao = Double.parseDouble(linha[18]);
			LocalDateTime horaOperacao = LocalDateTime.parse(linha[19]);

			EntidadeOperadora entidadeDebito =  new RepositorioEntidadeOperadora().buscar(identificadorEntidadeDebito);
			Acao acao = new RepositorioAcao().buscar(identificadorAcao);
			TituloDivida tituloDivida = new RepositorioTituloDivida().buscar(identificadorTituloDivida);



			arrayDeTransacoes[i] = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, horaOperacao);
			i++;
		}
		return arrayDeTransacoes;
	}
	public Transacao[] buscarPorEntidadeDevedora(int identificadorEntidadeDebito) throws IOException {
		EntidadeOperadora entidadeDebito = new RepositorioEntidadeOperadora().buscar(identificadorEntidadeDebito);

		if (entidadeDebito == null){
			return null;
		}
		int cont = 0;
		Scanner SCAN = new Scanner(arquivoTransacao);
		List<String[]> listaCredora = new ArrayList<>() ;

		while (SCAN.hasNextLine()){
			String[] arrayLinha = SCAN.nextLine().split(";");
			if (Integer.parseInt(arrayLinha[5]) == entidadeDebito.getIdentificador()){
				listaCredora.add(arrayLinha);
				cont++;
			}
		}
		SCAN.close();

		Transacao[] arrayDeTransacoes = new Transacao[cont];
		int i = 0;
		for (String[] linha : listaCredora){

			long identificadorEntidadeCredito = Integer.parseInt(linha[0]);
			int identificadorAcao = Integer.parseInt(linha[10]);
			int identificadorTituloDivida = Integer.parseInt(linha[14]);
			double valorOperacao = Double.parseDouble(linha[18]);
			LocalDateTime horaOperacao = LocalDateTime.parse(linha[19]);

			EntidadeOperadora entidadeCredito =  new RepositorioEntidadeOperadora().buscar(identificadorEntidadeCredito);
			Acao acao = new RepositorioAcao().buscar(identificadorAcao);
			TituloDivida tituloDivida = new RepositorioTituloDivida().buscar(identificadorTituloDivida);



			arrayDeTransacoes[i] = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, horaOperacao);
			i++;
		}
		return arrayDeTransacoes;
	}
}
