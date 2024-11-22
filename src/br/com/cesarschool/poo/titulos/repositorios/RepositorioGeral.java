package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;

// T era coringa, hoje coringa sou eu
public class RepositorioGeral <T extends Entidade>{
    private DAOSerializadorObjetos dao;
    private final Class<T> classeEntidade;

    public RepositorioGeral(Class<T> classeEntidade) {
        this.classeEntidade = classeEntidade;
    }
    public DAOSerializadorObjetos getDao() {
        return dao;
    }

    public Class<T> getClasseEntidade() {
        return classeEntidade;
    }

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
