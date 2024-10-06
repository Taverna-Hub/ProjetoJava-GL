package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.Acao;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

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

		FileWriter escreverLinha = new FileWriter(arquivoTransacao, true);

		EntidadeOperadora entidadeCredito = new RepositorioEntidadeOperadora().buscarEntidadeOperadora(transacao.getEntidadeCredito().getIdentificador());
		EntidadeOperadora entidadeDebito = new RepositorioEntidadeOperadora().buscarEntidadeOperadora(transacao.getEntidadeDebito().getIdentificador());
		Acao acao = new RepositorioAcao().buscar(transacao.getAcao().getIdentificador());
		TituloDivida tituloDivida = new RepositorioTituloDivida().buscarTituloDivida(transacao.getTituloDivida().getIdentificador());
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
		String acaoString =
				acao.getIdentificador() + ";"
				+ acao.getNome()  + ";"
				+ acao.getDataDeValidade()  + ";"
				+ acao.getValorUnitario();

		String tituloDividaString =
				tituloDivida.getIdentificador()  + ";"
				+ tituloDivida.getNome()  + ";"
				+ tituloDivida.getDataDeValidade()  + ";"
				+ tituloDivida.getTaxaJuros();


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

		FileReader lerLinha = new FileReader(arquivoTransacao);
		ArrayList<Transacao> transacoesEncontradas = new ArrayList<>();
		String linhaAtual = "";
		int caractere;
		boolean confirmarTransacao = false;

		while ((caractere = lerLinha.read()) != -1) {

			if (caractere == '\n') {
				String[] dados = linhaAtual.split(";");

				int idEntidadeCredito = Integer.parseInt(dados[0]);
				if (idEntidadeCredito == identificadorEntidadeCredito) {
					EntidadeOperadora entidadeCredito = new RepositorioEntidadeOperadora().buscarEntidadeOperadora(idEntidadeCredito);
					EntidadeOperadora entidadeDebito = new RepositorioEntidadeOperadora().buscarEntidadeOperadora(Integer.parseInt(dados[1]));
					Acao acao = new RepositorioAcao().buscar(Integer.parseInt(dados[2]));
					TituloDivida tituloDivida = new RepositorioTituloDivida().buscarTituloDivida(Integer.parseInt(dados[3]));
					double valorOperacao = Double.parseDouble(dados[4]);
					LocalDateTime dataHoraOperacao = LocalDateTime.parse(dados[5]);

					Transacao transacao = new Transacao(entidadeCredito, entidadeDebito, acao,
							  tituloDivida, valorOperacao, dataHoraOperacao);

					transacoesEncontradas.add(transacao);
					confirmarTransacao = true;
				}
			}
			lerLinha.close();

		}

		if (!confirmarTransacao) return null;
		else return transacoesEncontradas.toArray(new Transacao[0]);
	}
}
