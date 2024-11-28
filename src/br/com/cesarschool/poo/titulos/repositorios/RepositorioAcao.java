package br.com.cesarschool.poo.titulos.repositorios;

import br.gov.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.titulos.entidades.Acao;


public class RepositorioAcao extends RepositorioGeral{
	private final DAOSerializadorObjetos<Acao> dao;
	public RepositorioAcao() {
		super(Acao.class);
		this.dao = new DAOSerializadorObjetos<>(Acao.class);
	}

	public boolean incluir(Acao acao) {
		if (buscar(String.valueOf(acao.getIdentificador())) != null){
			return false;
		}
		return super.incluir(acao);
	}

	public boolean alterar(Acao acao){
		if (buscar(String.valueOf(acao.getIdentificador())) == null) {
			return false;
		}
		return super.alterar(acao);
	}

	public boolean excluir(int identificador) {
		return super.excluir(String.valueOf(identificador));
	}

	public Acao buscar(int identificador) {
		return (Acao) super.buscar(String.valueOf(identificador));
	}

	@Override
	public Class getClasseEntidade() {
		return Acao.class;
	}

	public Acao[] buscarTodos () {
		return (Acao[]) super.buscarTodos();
	}

	public DAOSerializadorObjetos<Acao> getDao() {
		return dao;
	}

}
