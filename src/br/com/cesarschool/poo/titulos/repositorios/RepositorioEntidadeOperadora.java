package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

public class RepositorioEntidadeOperadora extends RepositorioGeral{

    public RepositorioEntidadeOperadora() {
        super(EntidadeOperadora.class);
    }

    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        return false;
    }

    public boolean alterar(EntidadeOperadora entidadeOperadora){
        return false;
    }

    public boolean excluir(long identificador) {
        return false;
    }

    public EntidadeOperadora buscar(long identificador) {
        return null;
    }


    public Class getClasseEntidade() {
        return EntidadeOperadora.class;
    }

    public EntidadeOperadora[] buscarTodos () {
        return null;
    }
}
