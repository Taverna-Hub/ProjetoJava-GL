package br.com.cesarschool.poo.daogenerico;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DAOSerializadorObjetos<T extends Entidade & Serializable> {
    private final String nomeDiretorio;

    public DAOSerializadorObjetos(Class<?> tipoEntidade) {
        this.nomeDiretorio = "BDs/" + tipoEntidade.getSimpleName();
        File diretorio = new File(nomeDiretorio);
        if (!diretorio.exists()) {
            diretorio.mkdirs();
        }
    }

    public boolean incluir(Entidade entidade) {
        File arquivo = new File(getCaminhoArquivo(entidade.getIdUnico()));
        if (arquivo.exists()) {
            return false; // Identificador já existe
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(arquivo))) {
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
            if (arquivo.delete()) {
                return incluir(entidade);
            }
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
                return (T) ois.readObject();
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
                        listaEntidades.add((T) ois.readObject());
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return listaEntidades.toArray((T[]) new Entidade[0]);
    }

    private String getCaminhoArquivo(String idUnico) {
        return nomeDiretorio + "/" + idUnico + ".dat";
    }
}
