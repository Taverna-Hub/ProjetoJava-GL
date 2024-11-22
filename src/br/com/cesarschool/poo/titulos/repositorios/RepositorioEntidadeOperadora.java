package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;

public class RepositorioEntidadeOperadora extends RepositorioGeral{
    private final DAOSerializadorObjetos<EntidadeOperadora> dao;

    public RepositorioEntidadeOperadora() {
        super(EntidadeOperadora.class);
        this.dao = new DAOSerializadorObjetos<>(EntidadeOperadora.class);
    }

    public boolean incluir(EntidadeOperadora entidadeOperadora) {
        if (buscar(String.valueOf(entidadeOperadora.getIdentificador())) != null) {
            return false;
        }
        return super.incluir(entidadeOperadora);
    }

    public boolean alterar(EntidadeOperadora entidadeOperadora){
        if (buscar(String.valueOf(entidadeOperadora.getIdentificador())) == null) {
            return false;
        }
        return super.alterar(entidadeOperadora);
    }

    public boolean excluir(long identificador) {
        return super.excluir(String.valueOf(identificador));
    }

    public EntidadeOperadora buscar(long identificador) {
        return (EntidadeOperadora) super.buscar(String.valueOf(identificador));
    }

    @Override
    public Class getClasseEntidade() {
        return EntidadeOperadora.class;
    }

    public EntidadeOperadora[] buscarTodos () {
        return (EntidadeOperadora[]) super.buscarTodos();
    }

    public DAOSerializadorObjetos<EntidadeOperadora> getDao() {
        return dao;
    }
}
