package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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

	public Transacao[] buscarPorEntidadeCredora(int identificadorEntidadeCredito) {
		ArrayList<Transacao> listTransacoes = new ArrayList<>();
		Acao acao = null;
		TituloDivida tituloDivida = null;

		try (
				Scanner leitor = new Scanner(arquivoTransacao);
		){
			while ((leitor.hasNextLine())) {
			String transacaoAtual = leitor.nextLine();
				String[] transacaoLista = transacaoAtual.split(";");
				int identificadorCredoraAtual = Integer.parseInt(transacaoLista[0]);

				if (identificadorCredoraAtual == identificadorEntidadeCredito) {

					String nomeCredor = transacaoLista[1];
					boolean autorizadoAcaoCredor = Boolean.parseBoolean(transacaoLista[2]);
					double saldoAcaoCredito = Double.parseDouble(transacaoLista[3]);
					double saldoTituloDividaCredito = Double.parseDouble(transacaoLista[4]);
					EntidadeOperadora entidadeCredito = new EntidadeOperadora(
							identificadorEntidadeCredito,
							nomeCredor,
							autorizadoAcaoCredor
					);
					entidadeCredito.creditarSaldoAcao(saldoAcaoCredito);
					entidadeCredito.creditarSaldoTituloDivida(saldoTituloDividaCredito);


					int identificadorDebitorAtual = Integer.parseInt(transacaoLista[5]);
					String nomeDebitor = transacaoLista[6];
					boolean autorizadoAcaoDebitor = Boolean.parseBoolean(transacaoLista[7]);
					double saldoAcaoDebito = Double.parseDouble(transacaoLista[8]);
					double saldoTituloDividaDebito = Double.parseDouble(transacaoLista[9]);
					EntidadeOperadora entidadeDebito = new EntidadeOperadora(
							identificadorDebitorAtual,
							nomeDebitor,
							autorizadoAcaoDebitor
					);
					entidadeDebito.creditarSaldoAcao(saldoAcaoDebito);
					entidadeDebito.creditarSaldoTituloDivida(saldoTituloDividaDebito);

					if (transacaoLista[10].equals("null")) {
						int identificadorTituloDividaAtual = Integer.parseInt(transacaoLista[11]);
						String nomeTituloDivida = transacaoLista[12];
						String dataDeValidadeTituloDividaNaoFormatada = transacaoLista[13];
						LocalDate dataDeValidadeTituloDivida = LocalDate.parse(dataDeValidadeTituloDividaNaoFormatada);
						double taxaJurosTituloDivida = Double.parseDouble(transacaoLista[14]);
						tituloDivida = new TituloDivida(
								identificadorTituloDividaAtual,
								nomeTituloDivida,
								dataDeValidadeTituloDivida,
								taxaJurosTituloDivida
						);
					} else {
						int identificadorAcaoAtual = Integer.parseInt(transacaoLista[10]);
						String nomeAcao = transacaoLista[11];
						String dataDeValidadeAcaoNaoFormatada = transacaoLista[12];
						LocalDate dataDeValidadeAcao = LocalDate.parse(dataDeValidadeAcaoNaoFormatada);
						Double valorUnitarioAcao = Double.parseDouble(transacaoLista[13]);
						acao = new Acao(
								identificadorAcaoAtual,
								nomeAcao,
								dataDeValidadeAcao,
								valorUnitarioAcao
						);
					}

					double valorOperacao = Double.parseDouble(transacaoLista[15]);
					String dataHoraOperacaoNaoFormatado = transacaoLista[16];
					LocalDateTime dataHoraOperacao = LocalDateTime.parse(dataHoraOperacaoNaoFormatado);

					Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
					listTransacoes.add(transacao);
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado" );
		} catch (IOException e) {
			System.out.println("Ocorreu um erro de I/O");
		}

		Transacao[] arrayTransacoes = new Transacao[listTransacoes.size()];
		arrayTransacoes = listTransacoes.toArray(arrayTransacoes);

		return arrayTransacoes;
	}

	public Transacao[] buscarPorEntidadeDevedora(int identificadorEntidadeDebito) {
		ArrayList<Transacao> listTransacoes = new ArrayList<>();
		Acao acao = null;
		TituloDivida tituloDivida = null;

		try (
				Scanner leitor = new Scanner(arquivoTransacao);
		){
			while ((leitor.hasNextLine())) {
				String transacaoAtual = leitor.nextLine();
				String[] transacaoLista = transacaoAtual.split(";");
				int identificadorDevedoraAtual = Integer.parseInt(transacaoLista[5]);

				if (identificadorDevedoraAtual == identificadorEntidadeDebito) {

					int identificadorCredorAtual = Integer.parseInt(transacaoLista[0]);
					String nomeCreditor = transacaoLista[1];
					boolean autorizadoAcaoCreditor = Boolean.parseBoolean(transacaoLista[2]);
					double saldoAcaoCredito = Double.parseDouble(transacaoLista[3]);
					double saldoTituloDividaCredito = Double.parseDouble(transacaoLista[4]);
					EntidadeOperadora entidadeCredito = new EntidadeOperadora(
							identificadorCredorAtual,
							nomeCreditor,
							autorizadoAcaoCreditor
					);
					entidadeCredito.creditarSaldoAcao(saldoAcaoCredito);
					entidadeCredito.creditarSaldoTituloDivida(saldoTituloDividaCredito);

					String nomeDebitor = transacaoLista[6];
					boolean autorizadoAcaoDebitor = Boolean.parseBoolean(transacaoLista[7]);
					double saldoAcaoDebito = Double.parseDouble(transacaoLista[8]);
					double saldoTituloDividaDebito = Double.parseDouble(transacaoLista[9]);
					EntidadeOperadora entidadeDebito = new EntidadeOperadora(
							identificadorDevedoraAtual,
							nomeDebitor,
							autorizadoAcaoDebitor
					);
					entidadeDebito.creditarSaldoAcao(saldoAcaoDebito);
					entidadeDebito.creditarSaldoTituloDivida(saldoTituloDividaDebito);


					if (transacaoLista[10].equals("null")) {
						int identificadorTituloDividaAtual = Integer.parseInt(transacaoLista[11]);
						String nomeTituloDivida = transacaoLista[12];
						String dataDeValidadeTituloDividaNaoFormatada = transacaoLista[13];
						LocalDate dataDeValidadeTituloDivida = LocalDate.parse(dataDeValidadeTituloDividaNaoFormatada);
						double taxaJurosTituloDivida = Double.parseDouble(transacaoLista[14]);
						tituloDivida = new TituloDivida(
								identificadorTituloDividaAtual,
								nomeTituloDivida,
								dataDeValidadeTituloDivida,
								taxaJurosTituloDivida
						);
					} else {
						int identificadorAcaoAtual = Integer.parseInt(transacaoLista[10]);
						String nomeAcao = transacaoLista[11];
						String dataDeValidadeAcaoNaoFormatada = transacaoLista[12];
						LocalDate dataDeValidadeAcao = LocalDate.parse(dataDeValidadeAcaoNaoFormatada);
						Double valorUnitarioAcao = Double.parseDouble(transacaoLista[13]);
						acao = new Acao(
								identificadorAcaoAtual,
								nomeAcao,
								dataDeValidadeAcao,
								valorUnitarioAcao
						);
					}


					double valorOperacao = Double.parseDouble(transacaoLista[15]);
					String dataHoraOperacaoNaoFormatado = transacaoLista[16];
					LocalDateTime dataHoraOperacao = LocalDateTime.parse(dataHoraOperacaoNaoFormatado);

					Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao, tituloDivida, valorOperacao, dataHoraOperacao);
					listTransacoes.add(transacao);
				}

			}

		} catch (FileNotFoundException e) {
			System.out.println("Arquivo não encontrado" );
		} catch (IOException e) {
			System.out.println("Ocorreu um erro de I/O");
		}

		Transacao[] arrayTransacoes = new Transacao[listTransacoes.size()];
		arrayTransacoes = listTransacoes.toArray(arrayTransacoes);

		return arrayTransacoes;
	}
	public List<Transacao> buscarTodos () throws FileNotFoundException {

		Scanner scan = new Scanner(arquivoTransacao);
		List<Transacao> listaTransacoes = new ArrayList<>();

		while (scan.hasNextLine()){

			String[] arrayLinha = scan.nextLine().split(";");

			long identificadorEntidadeCredito = Integer.parseInt(arrayLinha[0]);
			EntidadeOperadora entidadeCredito = new RepositorioEntidadeOperadora().buscar(identificadorEntidadeCredito);

			long identificadorEntidadeDebito = Integer.parseInt(arrayLinha[5]);
			EntidadeOperadora entidadeDebito = new RepositorioEntidadeOperadora().buscar(identificadorEntidadeDebito);

			Acao acao = null;
			TituloDivida tituloDivida = null;

			if (!arrayLinha[10].equals("null")) {
				int identificadorAcao = Integer.parseInt(arrayLinha[10]);
				acao = new RepositorioAcao().buscar(identificadorAcao);
			}

			if (!arrayLinha[14].equals("null")) {
				int identificadorTituloDivida = Integer.parseInt(arrayLinha[14]);
				tituloDivida =  new RepositorioTituloDivida().buscar(identificadorTituloDivida);
			}

			double valorOperacao = Double.parseDouble(arrayLinha[18]);
			LocalDateTime horaOperacao = LocalDateTime.parse(arrayLinha[19]);

			Transacao transacao = new Transacao(entidadeCredito,entidadeDebito, acao, tituloDivida, valorOperacao, horaOperacao);
		}
		scan.close();
		return listaTransacoes;
	}
}
