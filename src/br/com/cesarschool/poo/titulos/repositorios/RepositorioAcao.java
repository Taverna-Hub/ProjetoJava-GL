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

	@Override
	public Class getClasseEntidade() {
		return Acao.class;
	}

	public Acao[] buscarTodos () {
		return null;
	}

}
