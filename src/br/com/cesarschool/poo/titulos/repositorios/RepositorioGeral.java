package br.com.cesarschool.poo.titulos.repositorios;

import br.com.cesarschool.poo.daogenerico.DAOSerializadorObjetos;
import br.com.cesarschool.poo.daogenerico.Entidade;
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
    private static final String FILE_NAME = "";
    public RepositorioGeral(Class<T> classeEntidade) {
        this.classeEntidade = classeEntidade;
        this.dao = new DAOSerializadorObjetos<>(classeEntidade);
    }

    public DAOSerializadorObjetos getDao() {
        return dao;
    }

    public abstract boolean incluir(TituloDivida tituloDivida);

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

    private List<T> buscarTodosAsList() throws IOException {
        Path path = Paths.get(FILE_NAME);
        if (!Files.exists(path)) {
            return new ArrayList<>();
        }

        return Files.lines(path)
                .map(this::parseLinha)
                .collect(Collectors.toList());
    }

    protected abstract T parseLinha(String linha);

    protected abstract String formatarTitulo(T t);

    private void salvarTodos(List<T> t) throws IOException {
        List<String> linhas = t.stream()
                .map(this::formatarTitulo)
                .collect(Collectors.toList());
        Files.write(Paths.get(FILE_NAME), linhas, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.CREATE);
    }

}
