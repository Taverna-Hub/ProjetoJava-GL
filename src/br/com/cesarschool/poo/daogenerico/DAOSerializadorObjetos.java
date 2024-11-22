package br.com.cesarschool.poo.daogenerico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*
 * Esta classe representa um DAO genérico, que inclui, exclui, altera, busca por identificador
 * único e busca todos, qualquer objeto(s) cujo tipo é subtipo de Entidade.
 *
 * Sugerimos o uso da API de serialização do JAVA, que grava e lê objetos em arquvos.
 * Lembrar sempre de fechar (em qualquer circunstância) streams JAVA abertas.
 *
 * As nuances mais detalhadas do funcionamento desta classe está especificada na classe de testes
 * automatizados br.gov.cesarschool.poo.testes.TestesDAOSerializador.
 *
 * A classe deve ter a estrutura (métodos e construtores) dada abaixo.
 */
public class DAOSerializadorObjetos <T extends Entidade & Serializable> {
    private final String nomeDiretorio;

    public DAOSerializadorObjetos(Class<?> tipoEntidade) {
        this.nomeDiretorio = "BDs/" + tipoEntidade.getSimpleName();
        File diretorio = new File(nomeDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public boolean incluir(Entidade entidade) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(getCaminhoArquivo(entidade.getIdUnico())))) {
            oos.writeObject(entidade);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean alterar(Entidade entidade) {
        File arquivo = new File(getCaminhoArquivo(entidade.getIdUnico()));
        if (arquivo.exists()) {
            return incluir(entidade);
        }
        return false;
    }

    public boolean excluir(String idUnico) {
        File arquivo = new File(getCaminhoArquivo(idUnico));
        if (arquivo.exists()) {
            return arquivo.delete();
        }
        return false;
    }

    public Entidade buscar(String idUnico) {
        File arquivo = new File(getCaminhoArquivo(idUnico));
        if (arquivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                // @SuppressWarnings("unchecked")
                T entidade = (T) ois.readObject();
                return entidade;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public Entidade[] buscarTodos() {
        File diretorio = new File(nomeDiretorio);
        List<T> listaEntidades = new ArrayList<>();
        if (diretorio.exists() && diretorio.isDirectory()) {
            File[] arquivos = diretorio.listFiles();
            if (arquivos != null) {
                for (File arquivo : arquivos) {
                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
                        // @SuppressWarnings("unchecked")
                        T entidade = (T) ois.readObject();
                        listaEntidades.add(entidade);
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        // @SuppressWarnings("unchecked")
        T[] arrayEntidades = (T[]) listaEntidades.toArray(new Entidade[0]);
        return arrayEntidades;
    }

    private String getCaminhoArquivo(String idUnico) {
        return nomeDiretorio + "/" + idUnico + ".dat";
    }
}
