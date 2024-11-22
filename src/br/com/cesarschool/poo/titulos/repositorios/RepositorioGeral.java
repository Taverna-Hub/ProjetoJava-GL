package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;
import br.com.cesarschool.poo.titulos.entidades.EntidadeOperadora;
import br.com.cesarschool.poo.titulos.entidades.TituloDivida;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        return dao.incluir(entidade);
    }

    public boolean alterar(T entidade) {
        return dao.alterar(entidade);
    }

    public boolean excluir(String id) {
        return dao.excluir(id);
    }

    public Entidade buscar(String id) {
        return dao.buscar(id); // todo
    }


    public Entidade[] buscarTodos() {
        return dao.buscarTodos();
    }

}
