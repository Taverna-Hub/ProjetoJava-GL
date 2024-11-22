package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

public class RepositorioTituloDivida extends RepositorioGeral{
	private final DAOSerializadorObjetos<TituloDivida> dao;

	public RepositorioTituloDivida() {
		super(TituloDivida.class);
		this.dao = new DAOSerializadorObjetos<>(TituloDivida.class);
	}

	public boolean incluir(TituloDivida tituloDivida) {
		if (buscar(String.valueOf(tituloDivida.getIdentificador())) != null){
			return false;
		}

		return super.incluir(tituloDivida);
	}

	public boolean alterar(TituloDivida tituloDivida) {
		if (buscar(String.valueOf(tituloDivida.getIdentificador())) == null) {
			return false;
		}
		return super.alterar(tituloDivida);
	}

	public boolean excluir(int identificador) {
		return super.excluir(String.valueOf(identificador));
	}

	public TituloDivida buscar(int identificador) {
		return (TituloDivida) super.buscar(String.valueOf(identificador));
	}

	@Override
	public TituloDivida[] buscarTodos() {
		return (TituloDivida[]) super.buscarTodos();
	}


	public DAOSerializadorObjetos<TituloDivida> getDao() {
		return dao;
	}

	@Override
	public Class getClasseEntidade() {
		return TituloDivida.class;
	}

}
