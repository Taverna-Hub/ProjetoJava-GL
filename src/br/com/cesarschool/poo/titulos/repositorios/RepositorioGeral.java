package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;

// T era coringa, hoje coringa sou eu
public abstract class RepositorioGeral <T extends Entidade>{
    private final Class<T> classeEntidade;
    private DAOSerializadorObjetos dao;

    public RepositorioGeral(Class<T> classeEntidade) {
        this.classeEntidade = classeEntidade;
        this.dao = new DAOSerializadorObjetos<>(classeEntidade);
    }

    public DAOSerializadorObjetos getDao() {
        return dao;
    }

    public abstract Class<T> getClasseEntidade();


    public boolean incluir(T entidade) {
        return false;
    }

    public boolean alterar(T entidade) {
        return false;
    }

    public boolean excluir(T entidade) {
        return false;
    }

    public T buscar(String id) {
        return null;
    }

    public T[] buscarTodos() {
        return null;
    }
}
