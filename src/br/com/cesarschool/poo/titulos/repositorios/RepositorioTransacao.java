package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.gov.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.entidades.Transacao;

import java.util.ArrayList;
import java.util.List;

import static java.lang.String.valueOf;

public class RepositorioTransacao extends RepositorioGeral{
	private final DAOSerializadorObjetos<Transacao> dao;

	public RepositorioTransacao() {
		super(Transacao.class);
		this.dao = new DAOSerializadorObjetos<>(Transacao.class);
	}

	public void incluir(Transacao transacao) {
		if (buscar(String.valueOf(transacao.getIdUnico())) == null) {
			dao.incluir(transacao);
		}
	}

	public Transacao[] buscarPorEntidadeCredora(long identificadorEntidadeCredito) {
		List<Transacao> resultado = new ArrayList<>();
		for (Transacao transacao : buscarTodos()) {
			if (transacao.getEntidadeCredito() != null
			&& transacao.getEntidadeCredito().getIdentificador() == identificadorEntidadeCredito) {
						resultado.add(transacao);
			}
		}
		return resultado.toArray(new Transacao[0]);
	}

	public Transacao[] buscarPorEntidadeDevedora(long identificadorEntidadeDebito) {
		List<Transacao> resultado = new ArrayList<>();
		for (Transacao transacao : buscarTodos()) {
			if (transacao.getEntidadeDebito() != null
			&& transacao.getEntidadeDebito().getIndentificador() == identificadorEntidadeDebito) {
				resultado.add(transacao);
			}
		}
		return resultado.toArray(new Transacao[0]);
	}

	@Override
	public Class<?> getClasseEntidade() {
		return Transacao.class;
	}

	@Override
	public Transacao[] buscarTodos() {
		List<Transacao> todasTransacoes = new ArrayList<>();
		for (Entidade entidade : dao.buscarTodos()) { // O DAO retorna uma lista de Entidade
			if (entidade instanceof Transacao) { // Verifica se a entidade é uma Transacao
				todasTransacoes.add((Transacao) entidade); // Faz o casting seguro
			}
		}
		return todasTransacoes.toArray(new Transacao[0]); // Retorna um array de Transacao
	}
}
